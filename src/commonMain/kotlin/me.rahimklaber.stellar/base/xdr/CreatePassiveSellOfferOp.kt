package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Creates an offer that doesn't take offers of the same price
//Threshold: med
//Result: CreatePassiveSellOfferResult
//*/
//struct CreatePassiveSellOfferOp
//{
//    Asset selling; // A
//    Asset buying;  // B
//    int64 amount;  // amount taker gets
//    Price price;   // cost of A in terms of B
//};
///////////////////////////////////////////////////////////////////////////
data class CreatePassiveSellOfferOp(
    val selling: Asset,
    val buying: Asset,
    val amount: Long,
    val price: Price,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        selling.encode(stream)
        buying.encode(stream)
        stream.writeLong(amount)
        price.encode(stream)
    }

    companion object : XdrElementDecoder<CreatePassiveSellOfferOp> {
        override fun decode(stream: XdrStream): CreatePassiveSellOfferOp {
            val selling = Asset.decode(stream)
            val buying = Asset.decode(stream)
            val amount = stream.readLong()
            val price = Price.decode(stream)
            return CreatePassiveSellOfferOp(selling, buying, amount, price)
        }
    }
}