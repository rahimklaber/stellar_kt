package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* OfferEntry
//    An offer is the building block of the offer book, they are automatically
//    claimed by payments when the price set by the owner is met.
//    For example an Offer is selling 10A where 1A is priced at 1.5B
//*/
//struct OfferEntry
//{
//    AccountID sellerID;
//    int64 offerID;
//    Asset selling; // A
//    Asset buying;  // B
//    int64 amount;  // amount of A
//
//    /* price for this offer:
//        price of A in terms of B
//        price=AmountB/AmountA=priceNumerator/priceDenominator
//        price is after fees
//    */
//    Price price;
//    uint32 flags; // see OfferEntryFlags
//
//    // reserved for future use
//    union switch (int v)
//    {
//    case 0:
//        void;
//    }
//    ext;
//};
///////////////////////////////////////////////////////////////////////////
data class OfferEntry(
    val sellerID: AccountID,
    val offerID: Long,
    val selling: Asset,
    val buying: Asset,
    val amount: Long,
    val price: Price,
    val flags: UInt, // offer entry flags
    val discriminant: Int
) : XdrElement{
    override fun encode(stream: XdrStream) {
        sellerID.encode(stream)
        stream.writeLong(offerID)
        selling.encode(stream)
        buying.encode(stream)
        stream.writeLong(amount)
        price.encode(stream)
        stream.writeInt(flags.toInt())
        stream.writeInt(discriminant)
    }

    companion object: XdrElementDecoder<OfferEntry>{
        override fun decode(stream: XdrStream): OfferEntry {
            val sellerID = AccountID.decode(stream)
            val offerID = stream.readLong()
            val selling = Asset.decode(stream)
            val buying = Asset.decode(stream)
            val amount = stream.readLong()
            val price = Price.decode(stream)
            val flags = stream.readInt().toUInt()
            val discriminant = stream.readInt()

            return OfferEntry(sellerID, offerID, selling, buying, amount, price, flags, discriminant)
        }

    }
}