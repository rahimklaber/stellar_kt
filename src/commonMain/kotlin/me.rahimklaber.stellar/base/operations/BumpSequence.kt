package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.BumpSequenceOp
import me.rahimklaber.stellar.base.xdr.SequenceNumber

data class BumpSequence(
    override val sourceAccount: String? = null,
    val bumpTo: Long,
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.BumpSequence(
                BumpSequenceOp(
                    bumpTo = SequenceNumber(bumpTo)
                )
            )
        )
    }
}
