package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import me.rahimklaber.stellar.horizon.Response

@OptIn(ExperimentalSerializationApi::class)
@JsonClassDiscriminator("type")
@Serializable
sealed interface OperationResponse : Response {
    val links: Links
    override val id: String //long?
    override val pagingToken: String
    val transactionHash: String
    val transactionSuccessful: Boolean
    val sourceAccount: String
    val createdAt: String
    val typeI: Int
    val type: String
}

