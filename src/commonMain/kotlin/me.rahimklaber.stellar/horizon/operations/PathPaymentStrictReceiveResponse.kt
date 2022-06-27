package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("path_payment_strict_receive")
data class PathPaymentStrictReceiveResponse(
    override val id: String, //long?
    @SerialName("paging_token") override val pagingToken: String,
    @SerialName("transaction_hash") override val transactionHash: String,
    @SerialName("transaction_successful") override val transactionSuccessful: Boolean,
    @SerialName("source_account") override val sourceAccount: String,
    @SerialName("created_at") override val createdAt: String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    @SerialName("asset_type") val assetType: String,
    @SerialName("asset_code") val assetCode: String? = null,
    @SerialName("asset_issuer") val assetIssuer: String? = null,
    val from: String,
    val to: String,
    val amount: String,
    val path: List<PathAsset>,
    @SerialName("source_amount") val sourceAmount: String,
    @SerialName("source_max") val sourceMax: String,
    @SerialName("source_asset_type") val sourceAssetType: String,
    @SerialName("source_asset_code") val sourceAssetCode: String? = null,
    @SerialName("source_asset_issuer") val sourceAssetIssuer: String? = null,
    @SerialName("_links") override val links : Links,
) : OperationResponse()

//todo maybe replace this by the normal asset?
// right now this just makes it so I don't have to deal with polymorphic serialization
@Serializable
data class PathAsset(
    @SerialName("asset_type") val assetType: String,
    @SerialName("asset_code") val assetCode: String? = null,
    @SerialName("asset_issuer") val assetIssuer: String? = null
) {
    fun isNative() = assetType == "native"
}