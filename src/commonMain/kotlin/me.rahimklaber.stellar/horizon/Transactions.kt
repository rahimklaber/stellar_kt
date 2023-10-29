package me.rahimklaber.stellar.horizon

import com.github.michaelbull.result.runCatching
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class TransactionRequestBuilder(
    client: HttpClient,
    horizonUrl: String,
) : RequestBuilder<TransactionResponse>(client, horizonUrl, "transactions") {

    override suspend fun call(): RequestResult<Page<TransactionResponse>> {
        return runCatching {
            client.get(buildUrl()).body()
        }
    }

    fun forAccount(accountId: String) = apply{
        addPath(accountId)
        addPath("transactions")
        urlExtension = "accounts"
    }

    fun forLedger(ledger: ULong) = apply{
        addPath(ledger.toString())
        addPath("transactions")
        urlExtension = "ledgers"
    }

    fun forLiquidityPool(poolId: String) = apply{
        addPath("$poolId/transactions")
        urlExtension = "liquidity_pools"
    }

    suspend fun stream() = inlineStream<TransactionResponse>()

    suspend fun transaction(transactionHash: String): RequestResult<TransactionResponse> {
        addPath(transactionHash)
        return runCatching {
            client.get(buildUrl()).body()
        }
    }

    override fun limit(limit: Int): TransactionRequestBuilder {
        addQueryParam("limit", "$limit")
        return this
    }

    /**
     * Request records from a certain point onwards.
     */
    override fun cursor(cursor: String): TransactionRequestBuilder {
        addQueryParam("cursor", cursor)
        return this
    }

    /**
     * defines the order of the response.
     */
    override fun order(order: Order): TransactionRequestBuilder {
        addQueryParam("order", order.value)
        return this
    }
}

/**
 * @see <a href="https://developers.stellar.org/api/resources/transactions/">Stellar docs</a>
 */
@Serializable
data class TransactionResponse(
    override val id: String,
    @SerialName("paging_token") override val pagingToken: String,
    val successful: Boolean,
    val hash: String,
    val ledger: Long, // is long ok?
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("source_account") val sourceAccount: String,
    @SerialName("source_account_sequence") val sourceAccountSequence: String,
    @SerialName("fee_charged") val feeCharged: Long, // long ok?
    @SerialName("max_fee") val maxFee: Long, // long?
    @SerialName("operation_count") val operationCount: Int,
    @SerialName("envelope_xdr") val envelopeXdr: String,
    @SerialName("result_xdr") val resultXdr: String,
    @SerialName("result_meta_xdr") val resultMetaXdr: String,
    @SerialName("fee_meta_xdr") val feeMetaXdr: String,
    val memo: String? = null,
    @SerialName("memo_type") val memoType: String,
    val signatures: List<String>,
    @SerialName("valid_after") val validAfter: String? = null,
    @SerialName("valid_before") val validBefore: String? = null,
) : Response


