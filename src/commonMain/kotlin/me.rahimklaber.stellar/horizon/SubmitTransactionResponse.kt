package me.rahimklaber.stellar.horizon

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
//TODO need to check which fields are optional.
// Something will prob go wrong if the submit failed.
@Serializable
data class SubmitTransactionResponse(
    val id: String,
    @SerialName("paging_token")
    val pagingToken: String,
    val successful: Boolean,
    val hash: String,
    val ledger: Long,
    @SerialName("created_at")
    val createdAt: Instant,
    @SerialName("source_account")
    val sourceAccount: String,
    @SerialName("source_account_sequence")
    val sourceAccountSequence: Long,
    @SerialName("fee_account")
    val feeAccount: String,
    @SerialName("fee_account_sequence")
    val feeAccountSequence: Long? = null,
    @SerialName("fee_charged")
    val feeCharged: Long,
    @SerialName("max_fee")
    val maxFee: Long,
    @SerialName("operation_count")
    val operationCount: Int,
    @SerialName("envelope_xdr")
    val envelopeXdr: String,
    @SerialName("result_xdr")
    val resultXdr: String,
    @SerialName("result_meta_xdr")
    val resultMetaXdr: String,
    @SerialName("fee_meta_xdr")
    val feeMetaXdr: String,
    val memo_type: String,
    val signatures: List<String>,
    @SerialName("valid_after")
    val validAfter: Instant? = null,
    @SerialName("valid_before")
    val validBefore: Instant? = null,
    val memo: String? = null,
    @SerialName("memo_bytes")
    val memoBytes: String? = null,
)
