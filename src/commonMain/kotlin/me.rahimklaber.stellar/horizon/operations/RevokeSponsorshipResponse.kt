package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("revoke_sponsorship")
data class RevokeSponsorshipResponse(
    override val id: String, //long?
    @SerialName("paging_token") override val pagingToken: String,
    @SerialName("transaction_hash") override val transactionHash: String,
    @SerialName("transaction_successful") override val transactionSuccessful: Boolean,
    @SerialName("source_account") override val sourceAccount: String,
    @SerialName("created_at") override val createdAt: String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    @SerialName("_links") override val links: Links,
    @SerialName("account_id") val accountId : String? = null,
    @SerialName("claimable_balance_id") val claimableBalanceId : String? = null,
    @SerialName("data_account_id") val dataAccountId : String? = null,
    @SerialName("data_name") val dataName : String? = null,
    @SerialName("offer_id") val offerId : String? = null,
    @SerialName("trustline_account_id") val trustlineAccountId : String? = null,
    @SerialName("trustline_asset") val trustlineAsset : String? = null,
    @SerialName("signer_account_id") val signerAccountId : String? = null,
    @SerialName("signer_key") val signerKey : String? = null
) : OperationResponse()