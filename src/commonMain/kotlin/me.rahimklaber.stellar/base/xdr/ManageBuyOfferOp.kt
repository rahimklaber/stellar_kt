// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ManageBuyOfferOp's original definition in the XDR file is:
 * ```
 * struct ManageBuyOfferOp
{
Asset selling;
Asset buying;
int64 buyAmount; // amount being bought. if set to 0, delete the offer
Price price;     // price of thing being bought in terms of what you are
// selling

// 0=create a new offer, otherwise edit an existing offer
int64 offerID;
};
 * ```
 */
data class ManageBuyOfferOp(
    val selling: Asset,
    val buying: Asset,
    val buyAmount: Int64,
    val price: Price,
    val offerID: Int64,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        selling.encode(stream)
        buying.encode(stream)
        buyAmount.encode(stream)
        price.encode(stream)
        offerID.encode(stream)
    }

    companion object : XdrElementDecoder<ManageBuyOfferOp> {
        override fun decode(stream: XdrInputStream): ManageBuyOfferOp {
            val selling = Asset.decode(stream)
            val buying = Asset.decode(stream)
            val buyAmount = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val price = Price.decode(stream)
            val offerID = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            return ManageBuyOfferOp(
                selling,
                buying,
                buyAmount,
                price,
                offerID,
            )
        }
    }
}
