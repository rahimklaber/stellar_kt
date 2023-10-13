package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.xdr.ManageSellOfferOp
import me.rahimklaber.stellar.base.xdr.Price

data class ManageSellOffer(
    override val sourceAccount: String?,
    val selling: Asset,
    val buying: Asset,
    val amount: TokenAmount,
    val price: Price, // should I create a new class for Price?
    val offerID: Long
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation.ManageSellOffer(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            ManageSellOfferOp(
                selling = selling.toXdr(),
                buying = buying.toXdr(),
                amount = amount.value,
                price = price,
                offerID = offerID
            )
        )
    }
}
