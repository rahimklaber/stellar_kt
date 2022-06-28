package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("end_sponsoring_future_reserves")
data class EndSponsoringFutureReserves(
    override val id: String, //long?
    @SerialName("paging_token") override val pagingToken: String,
    @SerialName("transaction_hash") override val transactionHash: String,
    @SerialName("transaction_successful") override val transactionSuccessful: Boolean,
    @SerialName("source_account") override val sourceAccount: String,
    @SerialName("created_at") override val createdAt: String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    @SerialName("_links") override val links : Links,
    @SerialName("begin_sponsor") val beginSponsor : String,
) : OperationResponse()
