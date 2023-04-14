package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// // price in fractional representation
//struct Price
//{
//    int32 n; // numerator
//    int32 d; // denominator
//};
///////////////////////////////////////////////////////////////////////////
data class Price(
    val numerator: Int,
    val denominator: Int
) : XdrElement{
    override fun encode(stream: XdrStream) {
        stream.writeInt(numerator)
        stream.writeInt(denominator)
    }

    companion object: XdrElementDecoder<Price>{
        override fun decode(stream: XdrStream): Price {
            val n = stream.readInt()
            val d = stream.readInt()

            return Price(n,d)
        }

    }

}