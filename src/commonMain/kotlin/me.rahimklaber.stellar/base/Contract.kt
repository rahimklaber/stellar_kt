package me.rahimklaber.stellar.base

import me.rahimklaber.stellar.base.xdr.Operation
import me.rahimklaber.stellar.base.xdr.soroban.SCVal

class Contract(
    private val address: String, // hex or encode address e.g. C....
) {
    fun invoke(fn: String): Operation.InvokeHostFunction{
        TODO()
    }
}