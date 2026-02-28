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

val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

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
        result["id"]!!.jsonPrimitive.content,
        result["protocolVersion"]!!.jsonPrimitive.int,
        result["sequence"]!!.jsonPrimitive.long,
    )
}

@Serializable
data class JsonRpcError(
    val code: Int,
    val message: String,
    val data: JsonElement? = null,
)

class JsonRpcException(val error: JsonRpcError) : RuntimeException("RPC error ${'$'}{error.code}: ${'$'}{error.message}")

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
        val response: JsonObject = client.post(url) {
            setBody(jsonFromReq(req))
            contentType(ContentType.Application.Json)
        }.body()

        response["error"]?.let { errorElement ->
            val error = json.decodeFromJsonElement<JsonRpcError>(errorElement)
            throw JsonRpcException(error)
        }

        return response
    }

}

data class JsonRpcRequest(
    val method: String,
    val params: JsonElement? = null,
    val id: Long = 1,
)

@Deprecated("Use GetLedgersRequest")
typealias GetLedgesRequest = GetLedgersRequest

@Serializable
data class GetLedgersRequest(
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

@Serializable
data class GetHealthResponse(
    val status: String
)

@Serializable
data class GetNetworkResponse(
    val passphrase: String? = null,
    val protocolVersion: Int? = null,
    val friendbotUrl: String? = null
)

@Serializable
data class GetTransactionsRequest(
    val startLedger: Int?,
    val pagination: Pagination? = null
)

@Serializable
data class GetTransactionsResponse(
    val transactions: List<TransactionResult>,
    val latestLedger: Int,
    val latestLedgerCloseTime: String,
    val oldestLedger: Int,
    val oldestLedgerCloseTime: String,
    val cursor: String
) {
    @Serializable
    data class TransactionResult(
        val status: String,
        val hash: String,
        val ledger: Int? = null,
        val createdAt: String? = null,
        val applicationOrder: Int? = null,
        val feeBump: Boolean? = null,
        val envelopeXdr: String? = null,
        val resultXdr: String? = null,
        val resultMetaXdr: String? = null
    )
}

interface SorobanClient {
    suspend fun getAccount(account: String): Account
    suspend fun getAccounts(accounts: List<String>): List<Account>
    suspend fun getLatestLedger(): LatestLedgerResponse
    suspend fun getHealth(): GetHealthResponse
    suspend fun getNetwork(): GetNetworkResponse
    suspend fun simulateTransaction(txXdr: String): SimulateTransactionResponse
    suspend fun simulateTransaction(request: SimulateTransactionRequest): SimulateTransactionResponse
    suspend fun sendTransaction(txXdr: String): SendTransactionResponse
    suspend fun getTransaction(hash: String): GetTransactionResponse
    suspend fun getTransactions(request: GetTransactionsRequest): GetTransactionsResponse
    suspend fun getLedgerEntries(keys: List<String>): GetLedgerEntriesResponse
    suspend fun getEvents(request: GetEventRequest): GetEventsResponse
    suspend fun getLedgers(request: GetLedgersRequest): GetLedgersResponse
}

@Serializable
data class LedgerEntryResponse(
    val key: String,
    val xdr: String,
    val lastModifiedLedgerSeq: Long,
    val liveUntilLedgerSeq: Long? = null
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
data class SimulateTransactionRequest(
    val transaction: String,
    val resourceConfig: JsonObject? = null
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
    val restorePreamble: RestorePreamble? = null,
) {
    @Serializable
    data class Result(val xdr: String, val auth: List<String>)

    @Serializable
    data class SorobanCost(
        val cpuInstructions: String? = null,
        val memBytes: String? = null,
        val ledgerReadBytes: String? = null,
        val ledgerWriteBytes: String? = null,
        val readBytes: String? = null,
        val writeBytes: String? = null
    )

    @Serializable
    data class RestorePreamble(val minResourceFee: String, val transactionData: String)
}

class SorobanClientImpl(
    val client: JsonRpcClient
) : SorobanClient {
    override suspend fun getAccount(account: String): Account {
        val entries = getLedgerEntries(
            listOf(
                LedgerKey.Account(LedgerKey.LedgerKeyAccount(StrKey.encodeToAccountIDXDR(account))).toXdrBase64()
            )
        )

        require(entries.entries.size == 1){"Account not found"}

        val accountEntry = LedgerEntry.LedgerEntryData.fromXdrBase64(entries.entries.first().xdr) as LedgerEntry.LedgerEntryData.Account

        return Account(account, accountEntry.account.seqNum.value)

    }

    override suspend fun getAccounts(accounts: List<String>): List<Account> {
        val entries = getLedgerEntries(
            accounts.map { LedgerKey.Account(LedgerKey.LedgerKeyAccount(StrKey.encodeToAccountIDXDR(it))).toXdrBase64() }
        )

        return entries.entries.map {
            val accountEntry = LedgerEntry.LedgerEntryData.fromXdrBase64(it.xdr) as LedgerEntry.LedgerEntryData.Account
            Account(it.key, accountEntry.account.seqNum.value)
        }

    }

    override suspend fun getLatestLedger(): LatestLedgerResponse {
        val response = client.executeRequest(JsonRpcRequest("getLatestLedger"))
        return createLatestLedgerResponse(response)
    }

    override suspend fun getHealth(): GetHealthResponse {
        val response = client.executeRequest(JsonRpcRequest("getHealth"))
        return json.decodeFromJsonElement(response["result"]!!)
    }

    override suspend fun getNetwork(): GetNetworkResponse {
        val response = client.executeRequest(JsonRpcRequest("getNetwork"))
        return json.decodeFromJsonElement(response["result"]!!)
    }

    override suspend fun simulateTransaction(txXdr: String): SimulateTransactionResponse {
        return simulateTransaction(SimulateTransactionRequest(txXdr))
    }

    override suspend fun simulateTransaction(request: SimulateTransactionRequest): SimulateTransactionResponse {
        val params = json.encodeToJsonElement(request)
        val response = client.executeRequest(JsonRpcRequest("simulateTransaction", params))
        return json.decodeFromJsonElement(response["result"]!!)
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

    override suspend fun getTransactions(request: GetTransactionsRequest): GetTransactionsResponse {
        val params = json.encodeToJsonElement(request)
        val response = client.executeRequest(JsonRpcRequest("getTransactions", params))
        return json.decodeFromJsonElement(response["result"]!!)
    }

    override suspend fun getLedgerEntries(keys: List<String>): GetLedgerEntriesResponse {
        val params = buildJsonObject {
            put("keys", JsonArray(keys.map { JsonPrimitive(it) }))
        }

        val response = client.executeRequest(JsonRpcRequest("getLedgerEntries", params))

        return json.decodeFromJsonElement(response["result"]!!)
    }

    override suspend fun getEvents(request: GetEventRequest): GetEventsResponse {
        val params = json.encodeToJsonElement(request)

        val response = client.executeRequest(JsonRpcRequest("getEvents", params))

        return json.decodeFromJsonElement(response["result"]!!)
    }

    override suspend fun getLedgers(request: GetLedgersRequest): GetLedgersResponse {
        val params = json.encodeToJsonElement(request)

        val response = client.executeRequest(JsonRpcRequest("getLedgers", params))
        return json.decodeFromJsonElement(response["result"]!!)
    }

}

fun sorobanClient(url: String): SorobanClient = SorobanClientImpl(JsonRpcClient(url))