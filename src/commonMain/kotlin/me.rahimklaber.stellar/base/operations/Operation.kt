package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.xdr.Operation

sealed interface Operation {
    val sourceAccount: String?
    fun toXdr(): Operation

    companion object{
        fun fromXdr(xdr: Operation): me.rahimklaber.stellar.base.operations.Operation {
            return when(xdr){
                is Operation.InvokeHostFunction -> InvokeHostFunction.fromXdr(xdr)
                else -> TODO()
            }
        }
    }

}