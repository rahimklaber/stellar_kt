package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("manage_buy_offer")
data class ManageBuyOfferResponse(
    override val id: String, //long?
    @SerialName("paging_token") override val pagingToken: String,
    @SerialName("transaction_hash") override val transactionHash: String,
    @SerialName("transaction_successful") override val transactionSuccessful: Boolean,
    @SerialName("source_account") override val sourceAccount: String,
    @SerialName("created_at") override val createdAt: String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    val price : String,
    val amount : String,
    @SerialName("price_r") val priceR : PriceR,
    @SerialName("buying_asset_type") val buyingAssetType: String,
    @SerialName("buying_asset_code") val buyingAssetCode: String? = null,
    @SerialName("buying_asset_issuer") val buyingAssetIssuer : String? = null,
    @SerialName("selling_asset_type") val sellingAssetType: String,
    @SerialName("selling_asset_code") val sellingAssetCode : String? = null,
    @SerialName("selling_asset_issuer") val sellingAssetIssuer : String? = null,
    @SerialName("offer_id") val offerId : String? = null
) : OperationResponse()