package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.ClaimClaimableBalanceOp
import me.rahimklaber.stellar.base.xdr.ClaimableBalanceID
import me.rahimklaber.stellar.base.xdr.fromHex

data class ClaimClaimableBalance(
    override val sourceAccount: String?,
    val balanceId: String, //hex string of claimablebalanceIdXdr
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.ClaimClaimableBalance(
                ClaimClaimableBalanceOp(
                    balanceID = ClaimableBalanceID.fromHex(balanceId)
                )
            )
        )
    }
}
