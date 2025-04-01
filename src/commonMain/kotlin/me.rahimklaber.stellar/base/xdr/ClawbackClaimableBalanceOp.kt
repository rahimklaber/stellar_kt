// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * ClawbackClaimableBalanceOp's original definition in the XDR file is:
 * ```
 * struct ClawbackClaimableBalanceOp
{
ClaimableBalanceID balanceID;
};
 * ```
 */
@JvmInline
value class ClawbackClaimableBalanceOp(val balanceID: ClaimableBalanceID) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        balanceID.encode(stream)
    }

    companion object : XdrElementDecoder<ClawbackClaimableBalanceOp> {
        override fun decode(stream: XdrInputStream): ClawbackClaimableBalanceOp {
            val balanceID = ClaimableBalanceID.decode(stream)
            return ClawbackClaimableBalanceOp(
                balanceID,
            )
        }
    }
}
