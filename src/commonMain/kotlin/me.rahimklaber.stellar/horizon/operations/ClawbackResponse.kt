package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SerialName("clawback")
@Serializable
data class ClawbackResponse(
    override val id : String, //long?
    @SerialName("paging_token") override val pagingToken : String,
    @SerialName("transaction_hash") override val transactionHash : String,
    @SerialName("transaction_successful") override val transactionSuccessful : Boolean,
    @SerialName("source_account") override val sourceAccount : String,
    @SerialName("created_at") override val createdAt : String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    @SerialName("_links") override val links : Links,
    @SerialName("asset_type") val assetType: String,
    @SerialName("asset_code") val assetCode: String? = null,
    @SerialName("asset_issuer") val assetIssuer: String? = null,
    val from: String,
    @SerialName("from_muxed") val fromMuxed: String? = null,
    @SerialName("from_muxed_id") val fromMuxedId: ULong? = null,
    val amount: String
) : OperationResponse