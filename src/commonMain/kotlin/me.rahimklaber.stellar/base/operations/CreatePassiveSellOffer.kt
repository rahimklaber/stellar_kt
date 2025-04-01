package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.Asset
import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.TokenAmount
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.CreatePassiveSellOfferOp
import me.rahimklaber.stellar.base.xdr.Price

data class CreatePassiveSellOffer(
    override val sourceAccount: String?,
    val selling: Asset,
    val buying: Asset,
    val amount: TokenAmount,
    val price: Price

) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.CreatePassiveSellOffer(
                CreatePassiveSellOfferOp(
                    selling = selling.toXdr(),
                    buying = buying.toXdr(),
                    amount = amount.value,
                    price = price
                )
            )
        )
    }
}
