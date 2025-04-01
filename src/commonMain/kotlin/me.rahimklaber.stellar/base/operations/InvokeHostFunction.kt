package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeMuxedAccount
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.InvokeHostFunctionOp

data class InvokeHostFunction(val xdr: InvokeHostFunctionOp, override val sourceAccount: String? = null): Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        val source = sourceAccount?.let {
            StrKey.encodeToMuxedAccountXDR(it)
        }

        return me.rahimklaber.stellar.base.xdr.Operation(
            source,
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.InvokeHostFunction(xdr)
        )
    }

    companion object{
        fun fromXdr(xdr: me.rahimklaber.stellar.base.xdr.Operation): InvokeHostFunction {
            require(xdr.body is me.rahimklaber.stellar.base.xdr.Operation.OperationBody.InvokeHostFunction)
            return InvokeHostFunction(
                xdr.body.invokeHostFunctionOp,
                xdr.sourceAccount?.let {
                    StrKey.encodeMuxedAccount(it)
                }
            )
        }
    }
}