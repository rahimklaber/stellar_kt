package me.rahimklaber.stellar

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import me.rahimklaber.stellar.base.Account
import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToAccountIDXDR
import me.rahimklaber.stellar.base.xdr.*
import me.rahimklaber.stellar.horizon.AccountResponse

data class LatestLedgerResponse(
    val id: String,
    val protocolVersion: Int,
    val sequence: Long
)

@Serializable
data class Pagination(
    val cursor: String? = null,
    val limit: Int? = null
)

@Serializable
data class GetEventRequest(
    val filters: List<EventFilter>,
    val startLedger: Int? = null,
    val pagination: Pagination? = null
){


    @Serializable
    data class EventFilter(
        val type: String,
        val contractIds: List<String>,
        val topics: List<List<String>>
    )

}

@Serializable
data class GetEventsResponse(
    val latestLedger: Int,
    val events: List<EventResponse>
)

@Serializable
data class EventResponse(
    val type: String, //contract/diagnostic/system
    val ledger: Int,
    val ledgerClosedAt: String,
    val contractId: String,
    val id: String,
    val pagingToken: String,
    val inSuccessfulContractCall: Boolean,
    val topic: List<String>,
    val value: String,
    val txHash: String,
)

fun createLatestLedgerResponse(response: JsonObject): LatestLedgerResponse {
    val result = response["result"]?.jsonObject!!
    return LatestLedgerResponse(
        result["id"]!!.jsonPrimitive.toString(),
        result["protocolVersion"]!!.jsonPrimitive.int,
        result["sequence"]!!.jsonPrimitive.long,
    )
}


class JsonRpcClient(
    val url: String,
    val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                this.ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }
) {

    private fun jsonFromReq(req: JsonRpcRequest): JsonObject {
        val map: MutableMap<String,JsonElement> =
            mutableMapOf(
                "jsonrpc" to JsonPrimitive("2.0"),
                "id" to JsonPrimitive(req.id),
                "method" to JsonPrimitive(req.method),
            )

        req.params?.let { ele ->
            map["params"] = ele
        }

        return JsonObject(map)
    }

    suspend fun executeRequest(req: JsonRpcRequest): JsonObject {

        return client.post(url) {
            setBody(jsonFromReq(req))
            contentType(ContentType.Application.Json)
        }
            .body()
    }

}

data class JsonRpcRequest(
    val method: String,
    val params: JsonElement? = null,
    val id: Long = 1,
)

@Serializable
data class GetLedgesRequest(
    val startLedger: Int?,
    val pagination: Pagination? = null
)

@Serializable
data class GetLedgersResponse(
    val ledgers: List<GetLedgerResult>,
    val latestLedger: Int,
    val latestLedgerCloseTime: String,
    val oldestLedger: Int,
    val oldestLedgerCloseTime: String,
    val cursor: String
) {
    @Serializable
    data class GetLedgerResult(
        val hash: String,
        val sequence: Long,
        val ledgerCloseTime: String,
        val headerXdr: String,
        val metadataXdr: String,
    )
}

interface SorobanClient {
    suspend fun getAccount(account: String): Account
    suspend fun getLatestLedger(): LatestLedgerResponse
    suspend fun simulateTransaction(txXdr: String): SimulateTransactionResponse
    suspend fun sendTransaction(txXdr: String): SendTransactionResponse
    suspend fun getTransaction(hash: String): GetTransactionResponse
    suspend fun getLedgerEntries(keys: List<String>): GetLedgerEntriesResponse
    suspend fun getEvents(request: GetEventRequest): GetEventsResponse
    suspend fun getLedgers(request: GetLedgesRequest): GetLedgersResponse
}

@Serializable
data class LedgerEntryResponse(
    val key: String,
    val xdr: String,
    val lastModifiedLedgerSeq: Long
)

@Serializable
data class GetLedgerEntriesResponse(
    val entries: List<LedgerEntryResponse>
)

@Serializable
data class GetTransactionResponse(
    val status: String,
    val latestLedger: Int,
    val latestLedgerCloseTime: String,
    val oldestLedger: Int,
    val oldestLedgerCloseTime: String,
    val ledger: Int? = null,
    val createdAt: String? = null,
    val applicationOrder: Int? = null,
    val feeBump:Boolean? = null,
    val envelopeXdr: String? = null,
    val resultXdr: String? = null,
    val resultMetaXdr: String? = null
)

@Serializable
data class SendTransactionResponse(
    val hash: String,
    val status: String,
    val latestLedger: Int,
    val latestLedgerCloseTime: String,
    val errorResultXdr: String? = null,
    val diagnosticEventsXdr: List<String>? = null
)

@Serializable
data class SimulateTransactionResponse(
    val latestLedger: Int,
    val transactionData: String? = null,
    val events: List<String>? = null,
    val results: List<Result>? = null,
    val minResourceFee: String? = null,
    val cost: SorobanCost? = null,
    val error: String? = null,
){
    @Serializable
    data class Result(val xdr: String, val auth: List<String>)

    @Serializable
    data class SorobanCost(val cpuInstructions: String? = null, val memBytes: String)
}

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}
internal class SorobanClientImpl(
    val client: JsonRpcClient
) : SorobanClient {
    override suspend fun getAccount(accountId: String): Account {
        val entries = getLedgerEntries(
            listOf(
                LedgerKey.LedgerKeyAccount(StrKey.encodeToAccountIDXDR(accountId)).toXdrString()
            )
        )

        require(entries.entries.size == 1){"Account not found"}

        val account = LedgerEntryData.decodeFromString(entries.entries.first().xdr) as LedgerEntryData.Account

        return Account(accountId, account.account.sequenceNumber)

    }

    override suspend fun getLatestLedger(): LatestLedgerResponse {
        val response = client.executeRequest(JsonRpcRequest("getLatestLedger"))
        return createLatestLedgerResponse(response)
    }

    override suspend fun simulateTransaction(txXdr: String): SimulateTransactionResponse {
        val params = buildJsonObject {
            put("transaction", JsonPrimitive(txXdr))
        }

        val response = client.executeRequest(JsonRpcRequest("simulateTransaction", params))

        return json.decodeFromJsonElement<SimulateTransactionResponse>(response["result"]!!)
    }

    override suspend fun sendTransaction(txXdr: String): SendTransactionResponse {
        val params = buildJsonObject {
            put("transaction", JsonPrimitive(txXdr))
        }


        val response = client.executeRequest(JsonRpcRequest("sendTransaction", params))


        return json.decodeFromJsonElement<SendTransactionResponse>(response["result"]!!)
    }

    override suspend fun getTransaction(hash: String): GetTransactionResponse {
        val params = buildJsonObject {
            put("hash", JsonPrimitive(hash))
        }

        val response = client.executeRequest(JsonRpcRequest("getTransaction", params))

        return json.decodeFromJsonElement<GetTransactionResponse>(response["result"]!!)
    }

    override suspend fun getLedgerEntries(keys: List<String>): GetLedgerEntriesResponse {
        val params = buildJsonObject {
            put("keys", JsonArray(keys.map{ JsonPrimitive(it)}))
        }

        val response = client.executeRequest(JsonRpcRequest("getLedgerEntries", params))

        return json.decodeFromJsonElement(response["result"]!!)
    }

    override suspend fun getEvents(request: GetEventRequest): GetEventsResponse {
        val params =  Json.encodeToJsonElement(request)

        val response = client.executeRequest(JsonRpcRequest("getEvents", params))

        return json.decodeFromJsonElement(response["result"]!!)
    }

    override suspend fun getLedgers(request: GetLedgesRequest): GetLedgersResponse {
        val params =  Json.encodeToJsonElement(request)

        val response = client.executeRequest(JsonRpcRequest("getLedgers", params))
        return json.decodeFromJsonElement(response["result"]!!)
    }

}

fun sorobanClient(url: String): SorobanClient = SorobanClientImpl(JsonRpcClient(url))