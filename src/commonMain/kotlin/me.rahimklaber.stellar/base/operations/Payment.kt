package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.Asset
import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.decodeAccountId
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.MuxedAccount
import me.rahimklaber.stellar.base.xdr.PaymentOp
import me.rahimklaber.stellar.base.xdr.toUint256

data class Payment(
    override val sourceAccount: String? = null,
    val destination: String,
    val asset: Asset,
    val amount: Long,
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
                amount = amount
            )
        )

    }
}