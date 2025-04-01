package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToAccountIDXDR
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.BeginSponsoringFutureReservesOp

data class BeginSponsoringFutureReserves(
    val sponsoredID: String,
    override val sourceAccount: String? = null,
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.BeginSponsoringFutureReserves(
                BeginSponsoringFutureReservesOp(
                    sponsoredID = StrKey.encodeToAccountIDXDR(sponsoredID)
                )
            )
        )
    }
}
