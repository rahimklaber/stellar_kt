package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
//Todo make it such that I don't have to duplicate all of the variables that all operatios have
// in common.
@Serializable
@SerialName("payment")
data class PaymentResponse(
    override val id : String, //long?
    @SerialName("paging_token") override val pagingToken : String,
    @SerialName("transaction_hash") override val transactionHash : String,
    @SerialName("transaction_successful") override val transactionSuccessful : Boolean,
    @SerialName("source_account") override val sourceAccount : String,
    @SerialName("created_at") override val createdAt : String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    @SerialName("asset_type") val assetType: String,
    @SerialName("asset_code") val assetCode: String,
    @SerialName("asset_issuer") val assetIssuer: String,
    val from: String,
    val to: String,
    val amount: String,
    override val links: Links,
) : OperationResponse

