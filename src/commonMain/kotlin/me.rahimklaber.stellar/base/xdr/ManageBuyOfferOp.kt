package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Creates, updates or deletes an offer with amount in terms of buying asset
//Threshold: med
//Result: ManageBuyOfferResult
//*/
//struct ManageBuyOfferOp
//{
//    Asset selling;
//    Asset buying;
//    int64 buyAmount; // amount being bought. if set to 0, delete the offer
//    Price price;     // price of thing being bought in terms of what you are
//                     // selling
//
//    // 0=create a new offer, otherwise edit an existing offer
//    int64 offerID;
//};
///////////////////////////////////////////////////////////////////////////
data class ManageBuyOfferOp(
    val selling: Asset,
    val buying: Asset,
    val buyAmount: Long,
    val price: Price,
    val offerID: Long,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        selling.encode(stream)
        buying.encode(stream)
        stream.writeLong(buyAmount)
        price.encode(stream)
        stream.writeLong(offerID)
    }

    companion object : XdrElementDecoder<ManageBuyOfferOp> {
        override fun decode(stream: XdrStream): ManageBuyOfferOp {
            val selling = Asset.decode(stream)
            val buying = Asset.decode(stream)
            val buyAmount = stream.readLong()
            val price = Price.decode(stream)
            val offerID = stream.readLong()
            return ManageBuyOfferOp(selling, buying, buyAmount, price, offerID)
        }
    }
}
