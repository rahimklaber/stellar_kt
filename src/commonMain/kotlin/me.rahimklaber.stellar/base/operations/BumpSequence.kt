package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.BumpSequenceOp

data class BumpSequence(
    override val sourceAccount: String? = null,
    val bumpTo: Long,
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation.BumpSequence(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            BumpSequenceOp(
                bumpTo = bumpTo
            )
        )
    }
}
