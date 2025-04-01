package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.Asset
import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.TokenAmount
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.ManageBuyOfferOp
import me.rahimklaber.stellar.base.xdr.Price

data class ManageBuyOffer(
    override val sourceAccount: String?,
    val selling: Asset,
    val buying: Asset,
    val buyAmount: TokenAmount,
    val price: Price,
    val offerID: Long
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.ManageBuyOffer(
                ManageBuyOfferOp(
                    selling = selling.toXdr(),
                    buying = buying.toXdr(),
                    buyAmount = buyAmount.value,
                    price = price,
                    offerID = offerID
                )
            )
        )
    }
}
