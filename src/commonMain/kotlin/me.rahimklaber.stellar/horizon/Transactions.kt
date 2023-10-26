package me.rahimklaber.stellar.horizon

import com.github.michaelbull.result.runCatching
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.cancel
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class TransactionRequestBuilder(client: HttpClient, horizonUrl: String) :
    RequestBuilder<TransactionResponse>(client, horizonUrl, "transactions") {
    override suspend fun callAsync(): RequestResult<Page<TransactionResponse>> {
        return runCatching {
            client.get(buildUrl()).body()
        }
    }

    suspend fun stream() = inlineStream<TransactionResponse>()

    suspend fun transaction(transactionHash: String): RequestResult<TransactionResponse> {
        addPath(transactionHash)
        return runCatching {
            client.get(buildUrl()).body()
        }
    }

    suspend fun forAccount(accountId: String): RequestResult<Page<TransactionResponse>> {
        addPath("$accountId/transactions")
        return runCatching {
            client.get(buildUrl("accounts")).body() // todo create an enum with all of the endpoints.
        }
    }

    suspend fun forLedger(ledger: Long): RequestResult<Page<TransactionResponse>> {
        addPath("$ledger/transactions")
        return runCatching {
            client.get(buildUrl("ledgers")).body() // todo create an enum with all of the endpoints.
        }
    }

    suspend fun forLiquidityPool(poolId: String): RequestResult<Page<TransactionResponse>> {
        addPath("$poolId/transactions")
        return runCatching {
            client.get(buildUrl("liquidity_pools")).body() // todo create an enum with all of the endpoints.
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
    val created_at: String,
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
): Response


