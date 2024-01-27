package me.rahimklaber.stellar

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*

data class LatestLedgerResponse(
    val id: String,
    val protocolVersion: Int,
    val sequence: Long
)

data class getEventsResponse(
    val events: Map<Int, EventResponse>
)

data class EventResponse(
    val type: String,
    val ledger: Int,
    val ledgerClosedAt: String,
    val contractId: String,
    val id: String,
    val pagingToken: String,
    val inSuccessfulContractCall: Boolean,
    val topic: List<String>,
    val value: String,
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

interface SorobanClient {
    suspend fun getLatestLedger(): LatestLedgerResponse
    suspend fun simulateTransaction(): SimulateTransactionResponse
}

data class SimulateTransactionResponse(
    val transactionData: String,
    val minResourceFee: String,
    val events: List<String>,
    val results: List<Any>,
    val cost: SorobanCost,
    val latestLedger: Int,
)

data class SorobanCost(val cpuInstructions: String, val memBytes: String)

internal class SorobanClientImpl(
    val client: JsonRpcClient
) : SorobanClient {
    override suspend fun getLatestLedger(): LatestLedgerResponse {
        val response = client.executeRequest(JsonRpcRequest("getLatestLedger"))
        return createLatestLedgerResponse(response)
    }

    override suspend fun simulateTransaction(): SimulateTransactionResponse {
        TODO("Not yet implemented")
    }

}

fun sorobanClient(url: String): SorobanClient = SorobanClientImpl(JsonRpcClient(url))