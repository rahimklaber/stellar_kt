package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SerialName("set_trust_line_flags")
@Serializable
data class SetTrustLineFlags(
    override val id : String, //long?
    @SerialName("paging_token") override val pagingToken : String,
    @SerialName("transaction_hash") override val transactionHash : String,
    @SerialName("transaction_successful") override val transactionSuccessful : Boolean,
    @SerialName("source_account") override val sourceAccount : String,
    @SerialName("created_at") override val createdAt : String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    @SerialName("_links") override val links : Links,
    val trustor: String,
    @SerialName("set_flags") val setFlags: List<Int> = listOf(),
    @SerialName("set_flags_s") val setFlagsS: List<String> = listOf(),
    @SerialName("clear_flags") val clearFlags: List<Int> = listOf(),
    @SerialName("clear_flags_s") val clearFlagsS: List<String> = listOf(),
    @SerialName("asset_type") val assetType: String,
    @SerialName("asset_code") val assetCode: String? = null,
    @SerialName("asset_issuer") val assetIssuer: String? = null,
) : OperationResponse