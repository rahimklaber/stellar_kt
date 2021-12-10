package me.rahimklaber.stellar.horizon

import io.ktor.client.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
class TransactionRequestBuilder(client: HttpClient, horizonUrl: String) :
    RequestBuilder<TransactionResponse>(client, horizonUrl, "transactions") {
    override suspend fun callAsync(): RequestResult<Page<TransactionResponse>> {
        TODO("Not yet implemented")
    }
}

/**
 * @see <a href="https://developers.stellar.org/api/resources/transactions/">Stellar docs</a>
 */
@Serializable
data class TransactionResponse(
    val id : String,
    @SerialName("paging_token") val pagingToken : Long, // not sure if this should be long or int
    val successful : Boolean,
    val hash: String,
    val ledger: Long, // is long ok?
    val created_at : String,
    @SerialName("source_account") val sourceAccount : String,
    @SerialName("source_account_sequence") val sourceAccountSequence: String,
    @SerialName("fee_charged") val feeCharged : Long, // long ok?
    @SerialName("max_fee") val maxFee : Long, // long?
    @SerialName("operation_count") val operationCount : Int,
    @SerialName("envelope_xdr") val envelopeXdr : String,
    @SerialName("result_xdr") val resultXdr : String,
    @SerialName("result_meta_xdr") val resultMetaXdr : String,
    @SerialName("fee_meta_xdr") val feeMetaXdr : String,
    val memo : String,
    @SerialName("memo_type") val memoType : String,
    val signatures : List<String>,
    @SerialName("valid_after") val validAfter : String,
    @SerialName("valid_before") val validBefore : String,
)