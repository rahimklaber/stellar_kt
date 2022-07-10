package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

/**
 * I am currently abusing the polymorphic serialization of kotlinx.serialization.
 * It uses the assigns jsons a type to distinguish the actuall class. I am currently using the
 * operation responses' types to distinguish the various operations.
 *
 * Todo: Fix this.
 */

@Serializable
@JsonClassDiscriminator("type")
sealed class OperationResponse(){
    abstract val id: String //long?
    @SerialName("paging_token") abstract val pagingToken: String
    @SerialName("transaction_hash") abstract val transactionHash: String
    @SerialName("transaction_successful") abstract val transactionSuccessful: Boolean
    @SerialName("source_account") abstract val sourceAccount: String
    @SerialName("created_at") abstract val createdAt: String
    @SerialName("type_i") abstract val typeI: Int
    @SerialName("type") abstract val type: String
    @SerialName("_links") abstract val links : Links
}