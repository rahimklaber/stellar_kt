package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("liquidity_pool_withdraw")
data class LiquidityPoolWithdrawResponse(
    override val id: String, //long?
    @SerialName("paging_token") override val pagingToken: String,
    @SerialName("transaction_hash") override val transactionHash: String,
    @SerialName("transaction_successful") override val transactionSuccessful: Boolean,
    @SerialName("source_account") override val sourceAccount: String,
    @SerialName("created_at") override val createdAt: String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    @SerialName("_links") override val links: Links = Links("","","","",""), //todo this doesn't have a link???
    @SerialName("liquidity_pool_id") val liquidityPoolId: String,
    @SerialName("reserves_min") val reservesMin: List<LiquidityPoolResponseAsset>,
    @SerialName("reserves_received") val reservesReceived: List<LiquidityPoolResponseAsset>,
    @SerialName("shares") val shares: String
) : OperationResponse()