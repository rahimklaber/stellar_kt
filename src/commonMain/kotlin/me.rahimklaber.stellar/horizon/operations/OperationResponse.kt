package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.Serializable

/**
 * I am currently abusing the polymorphic serialization of kotlinx.serialization.
 * It uses the assigns jsons a type to distinguish the actuall class. I am currently using the
 * operation responses' types to distinguish the various operations.
 *
 * Todo: Fix this.
 */

@Serializable
sealed class OperationResponse(){
    abstract val id : String //long?
    abstract val pagingToken : String
    abstract val transactionHash : String
    abstract val transactionSuccessful : Boolean
    abstract val sourceAccount : String
    abstract val createdAt : String
    abstract val typeI : Int
    abstract val type : String
}