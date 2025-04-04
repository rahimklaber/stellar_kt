// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * OfferEntry's original definition in the XDR file is:
 * ```
 * struct OfferEntry
{
AccountID sellerID;
int64 offerID;
Asset selling; // A
Asset buying;  // B
int64 amount;  // amount of A

/* price for this offer:
price of A in terms of B
price=AmountB/AmountA=priceNumerator/priceDenominator
price is after fees
*/
Price price;
uint32 flags; // see OfferEntryFlags

// reserved for future use
union switch (int v)
{
case 0:
void;
}
ext;
};
 * ```
 */
data class OfferEntry(
    val sellerID: AccountID,
    val offerID: Int64,
    val selling: Asset,
    val buying: Asset,
    val amount: Int64,
    val price: Price,
    val flags: Uint32,
    val ext: OfferEntryExt,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        sellerID.encode(stream)
        offerID.encode(stream)
        selling.encode(stream)
        buying.encode(stream)
        amount.encode(stream)
        price.encode(stream)
        flags.encode(stream)
        ext.encode(stream)
    }

    companion object : XdrElementDecoder<OfferEntry> {
        override fun decode(stream: XdrInputStream): OfferEntry {
            val sellerID = AccountID.decode(stream)
            val offerID = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val selling = Asset.decode(stream)
            val buying = Asset.decode(stream)
            val amount = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val price = Price.decode(stream)
            val flags = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val ext = OfferEntryExt.decode(stream)
            return OfferEntry(
                sellerID,
                offerID,
                selling,
                buying,
                amount,
                price,
                flags,
                ext,
            )
        }
    }

    /**
     * OfferEntryExt's original definition in the XDR file is:
     * ```
     * union switch (int v)
    {
    case 0:
    void;
    }
     * ```
     */
    sealed class OfferEntryExt(val type: Int) : XdrElement {
        data object OfferEntryExtV0 : OfferEntryExt(0) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        companion object : XdrElementDecoder<OfferEntryExt> {
            override fun decode(stream: XdrInputStream): OfferEntryExt {
                return when (val type = Int.decode(stream)) {
                    0 -> OfferEntryExtV0
                    else -> throw IllegalArgumentException("unknown type: $type")
                }
            }
        }
    }
}
