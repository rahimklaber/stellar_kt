package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.Asset
import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.decodeAccountId
import me.rahimklaber.stellar.base.xdr.MuxedAccount
import me.rahimklaber.stellar.base.xdr.PaymentOp
import me.rahimklaber.stellar.base.xdr.toUint256

data class Payment(
    val sourceAccount: String? = null,
    val destination: String,
    val asset: Asset,
    val amount: Long,
) : Operation {

    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        val source = if (sourceAccount != null) { // def better way to do this
            MuxedAccount.Ed25519(StrKey.decodeAccountId(sourceAccount).toUint256())
        } else {
            null
        }
        return me.rahimklaber.stellar.base.xdr.Operation.Payment(
            sourceAccount = source,
            PaymentOp(
                destination = MuxedAccount.Ed25519(StrKey.decodeAccountId(destination).toUint256()),
                asset = asset.toXdr(),
                amount = amount
            )
        )

    }
}