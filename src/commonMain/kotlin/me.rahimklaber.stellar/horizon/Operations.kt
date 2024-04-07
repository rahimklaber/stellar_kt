package me.rahimklaber.stellar.horizon


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import me.rahimklaber.stellar.horizon.operations.OperationResponse

//import me.rahimklaber.stellar.horizon.operations.PaymentResponse


class OperationsRequestBuilder(client: HttpClient, horizonUrl: String) :
    RequestBuilder<OperationResponse>(client, horizonUrl, "operations") {
    private var forAccount = false
    private var forLedger = false
    private var forTransaction = false


    fun forAccount(accountId: String): OperationsRequestBuilder {
        forAccount = true
        addPath(accountId)
        addPath("operations")
        return this
    }

    fun forLedger(ledger: ULong): OperationsRequestBuilder {
        forLedger = true
        addPath(ledger.toString())
        addPath("operations")
        return this
    }

    fun forTransaction(transactionId: String): OperationsRequestBuilder {
        forTransaction = true
        addPath(transactionId)
        addPath("operations")
        return this
    }

    fun includeFailed(): OperationsRequestBuilder {
        addQueryParam("include_failed", "true")
        return this
    }


    suspend fun operation(operationId: ULong): OperationResponse {
        addPath(operationId.toString())
        return client.get(buildUrl()).body()
    }

    override fun limit(limit: Int): OperationsRequestBuilder {
        super.limit(limit)
        return this
    }

    override fun cursor(cursor: String): OperationsRequestBuilder {
        super.cursor(cursor)
        return this
    }

    override fun order(order: Order): OperationsRequestBuilder {
        super.order(order)
        return this
    }

    suspend fun stream() = inlineStream<OperationResponse>()
    override suspend fun call(): Page<OperationResponse> {
        return run {
            val extension = if (forAccount) {
                "accounts"
            } else if (forLedger) {
                "ledgers"
            } else if (forTransaction) {
                "transactions"
            } else {
                urlExtension
            }
            val url = buildUrl(extension)
            println(url)
            client.get(url).body()
        }
    }

}