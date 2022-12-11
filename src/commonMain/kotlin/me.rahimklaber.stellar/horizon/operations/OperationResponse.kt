package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.JsonClassDiscriminator
import me.rahimklaber.stellar.horizon.HrefSerializer

/**
 * I am currently abusing the polymorphic serialization of kotlinx.serialization.
 * It uses the assigns jsons a type to distinguish the actuall class. I am currently using the
 * operation responses' types to distinguish the various operations.
 *
 * Todo: Fix this.
 */
@JsonClassDiscriminator("type")
@Serializable
sealed interface OperationResponse{
    val links : Links
    val id : String //long?
    val pagingToken : String
    val transactionHash : String
    val transactionSuccessful : Boolean
    val sourceAccount : String
    val createdAt : String
    val typeI : Int
    val type : String
}

