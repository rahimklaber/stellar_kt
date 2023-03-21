package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum OfferEntryFlags
//{
//    // an offer with this flag will not act on and take a reverse offer of equal
//    // price
//    PASSIVE_FLAG = 1
//};
///////////////////////////////////////////////////////////////////////////
enum class OfferEntryFlags(val value: Int): XdrElement {
    PASSIVE_FLAG(1);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<OfferEntryFlags> {
        override fun decode(stream: XdrStream): OfferEntryFlags {
            return when(val flag = stream.readInt()){
                PASSIVE_FLAG.value -> PASSIVE_FLAG
                else -> throw IllegalArgumentException("Invalid flag: $flag")
            }
        }
    }
}