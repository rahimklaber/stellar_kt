package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToAccountIDXDR
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.BeginSponsoringFutureReservesOp

data class BeginSponsoringFutureReserves(
    override val sourceAccount: String?,
    val sponsoredID: String,
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation.BeginSponsoringFutureReserves(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            BeginSponsoringFutureReservesOp(
                sponsoredID = StrKey.encodeToAccountIDXDR(sponsoredID)
            )
        )
    }
}
