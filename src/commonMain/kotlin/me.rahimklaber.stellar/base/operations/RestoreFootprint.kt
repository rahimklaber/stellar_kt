package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.ExtensionPoint
import me.rahimklaber.stellar.base.xdr.RestoreFootprintOp

data class RestoreFootprint(
    override val sourceAccount: String? = null,
): Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.RestoreFootprint(
                RestoreFootprintOp(ExtensionPoint.ExtensionPointV0)
            )
        )
    }
}
