package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("allow_trust")
data class AllowTrustResponse(
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
    val authorize : Boolean, // todo: the docs show that this should be an int -> https://developers.stellar.org/api/resources/operations/object/allow-trust/
    val trustee: String,
    val trustor: String, override val links: OperationResponse.Links,
) : OperationResponse