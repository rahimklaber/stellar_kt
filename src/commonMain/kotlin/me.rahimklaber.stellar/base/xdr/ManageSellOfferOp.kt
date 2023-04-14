package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Creates, updates or deletes an offer
//Threshold: med
//Result: ManageSellOfferResult
//*/
//struct ManageSellOfferOp
//{
//    Asset selling;
//    Asset buying;
//    int64 amount; // amount being sold. if set to 0, delete the offer
//    Price price;  // price of thing being sold in terms of what you are buying
//
//    // 0=create a new offer, otherwise edit an existing offer
//    int64 offerID;
//};
///////////////////////////////////////////////////////////////////////////
data class ManageSellOfferOp(
    val selling: Asset,
    val buying: Asset,
    val amount: Long,
    val price: Price,
    val offerID: Long,
): XdrElement {
    override fun encode(stream: XdrStream) {
        selling.encode(stream)
        buying.encode(stream)
        stream.writeLong(amount)
        price.encode(stream)
        stream.writeLong(offerID)
    }

    companion object: XdrElementDecoder<ManageSellOfferOp> {
        override fun decode(stream: XdrStream): ManageSellOfferOp {
            val selling = Asset.decode(stream)
            val buying = Asset.decode(stream)
            val amount = stream.readLong()
            val price = Price.decode(stream)
            val offerID = stream.readLong()
            return ManageSellOfferOp(selling, buying, amount, price, offerID)
        }
    }
}
