package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.xdr.MuxedAccount
import me.rahimklaber.stellar.base.xdr.PaymentOp
import me.rahimklaber.stellar.base.xdr.toUint256

data class Payment(
    val destination: String,
    val asset: Asset,
    val amount: TokenAmount,
    override val sourceAccount: String? = null,
) : Operation {

    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        val source = sourceAccount?.let {
            StrKey.encodeToMuxedAccountXDR(it)
        }
        return me.rahimklaber.stellar.base.xdr.Operation.Payment(
            sourceAccount = source,
            PaymentOp(
                destination = StrKey.encodeToMuxedAccountXDR(destination),
                asset = asset.toXdr(),
                amount = amount.value
            )
        )

    }
}