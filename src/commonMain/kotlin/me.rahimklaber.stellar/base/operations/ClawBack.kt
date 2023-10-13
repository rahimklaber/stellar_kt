package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.xdr.ClawBackOp

data class ClawBack(
    override val sourceAccount: String? = null,
    val asset: Asset,
    val from: String,
    val amount: TokenAmount
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        val source = sourceAccount?.let {
            StrKey.encodeToMuxedAccountXDR(it)
        }
        return me.rahimklaber.stellar.base.xdr.Operation.Clawback(
            sourceAccount = source,
            ClawBackOp(
                asset = asset.toXdr(),
                from = StrKey.encodeToMuxedAccountXDR(from),
                amount = amount.value
            )
        )
    }
}
