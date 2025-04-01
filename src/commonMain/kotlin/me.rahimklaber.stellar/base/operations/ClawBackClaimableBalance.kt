package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.ClaimableBalanceID
import me.rahimklaber.stellar.base.xdr.ClawbackClaimableBalanceOp
import me.rahimklaber.stellar.base.xdr.fromHex

data class ClawBackClaimableBalance(
    override val sourceAccount: String? = null,
    val balanceId: String //hex string of claimablebalanceIdXdr
): Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        val source = sourceAccount?.let {
            StrKey.encodeToMuxedAccountXDR(it)
        }
        return me.rahimklaber.stellar.base.xdr.Operation(
            sourceAccount = source,
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.ClawbackClaimableBalance(
                ClawbackClaimableBalanceOp(
                    balanceID = ClaimableBalanceID.fromHex(balanceId)
                )
            )
        )
    }
}
