package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("liquidity_pool_deposit")
data class LiquidityPoolDepositResponse(
    override val id: String, //long?
    @SerialName("paging_token") override val pagingToken: String,
    @SerialName("transaction_hash") override val transactionHash: String,
    @SerialName("transaction_successful") override val transactionSuccessful: Boolean,
    @SerialName("source_account") override val sourceAccount: String,
    @SerialName("created_at") override val createdAt: String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    @SerialName("_links") override val links: Links, //todo this doesn't have a link???
    @SerialName("liquidity_pool_id") val liquidityPoolId: String,
    @SerialName("reserves_max") val reservesMax: List<LiquidityPoolResponseAsset>,
    @SerialName("min_price") val minPrice: String,
    @SerialName("min_price_r") val minPriceR: PriceR,
    @SerialName("max_price") val maxPrice: String,
    @SerialName("max_price_r") val maxPriceR: PriceR,
    @SerialName("reserves_deposited") val reservesDeposited: List<LiquidityPoolResponseAsset>,
    @SerialName("shares_received") val sharesReceived: String
) : OperationResponse

@Serializable
data class LiquidityPoolResponseAsset(
    val asset : String,
    val amount : String,
)