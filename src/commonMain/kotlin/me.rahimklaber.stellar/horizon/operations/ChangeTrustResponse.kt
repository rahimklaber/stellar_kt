package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("change_trust")
data class ChangeTrustResponse(
    override val id: String, //long?
    @SerialName("paging_token") override val pagingToken: String,
    @SerialName("transaction_hash") override val transactionHash: String,
    @SerialName("transaction_successful") override val transactionSuccessful: Boolean,
    @SerialName("source_account") override val sourceAccount: String,
    @SerialName("created_at") override val createdAt: String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    @SerialName("asset_type") val assetType: String,
    @SerialName("asset_code") val assetCode: String,
    @SerialName("asset_issuer") val assetIssuer: String,
    @SerialName("_links") override val links : Links,
    val limit: String,
    val trustee: String? = null,
    val trustor: String,
    @SerialName("liquidity_pool_id") val liquidityPoolId: String? = null,
) : OperationResponse()