package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@SerialName("create_claimable_balance")
data class CreateClaimableBalanceResponse(
    override val id: String, //long?
    @SerialName("paging_token") override val pagingToken: String,
    @SerialName("transaction_hash") override val transactionHash: String,
    @SerialName("transaction_successful") override val transactionSuccessful: Boolean,
    @SerialName("source_account") override val sourceAccount: String,
    @SerialName("created_at") override val createdAt: String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    @SerialName("_links") override val links : Links,
    val asset : String,
    val amount : String,
    val claimants : List<ClaimantResponse>,
    val sponsor: String? = null,

) : OperationResponse

@Serializable
data class ClaimantResponse(
    val destination : String,
    val predicate : Predicate
)
//todo make this a sealed class or something
@Serializable
data class Predicate(
    val unconditional : Boolean? = null,
    val and : List<Predicate>? = null,
    val or : List<Predicate>? = null,
    val not : Predicate? = null,
    @SerialName("abs_before") val absBefore : String? = null, //todo should I use a datetime class for this?
    @SerialName("abs_before_epoch") val absBeforeEpoch : String? = null,
    @SerialName("rel_before") val relBefore : String? = null
)