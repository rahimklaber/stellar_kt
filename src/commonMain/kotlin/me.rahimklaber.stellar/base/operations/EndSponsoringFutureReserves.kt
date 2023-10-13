package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR

data class EndSponsoringFutureReserves(
    override val sourceAccount: String?
): Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation.EndSponsoringFutureReserves(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) }
        )
    }
}