// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * Price's original definition in the XDR file is:
 * ```
 * struct Price
{
int32 n; // numerator
int32 d; // denominator
};
 * ```
 */
data class Price(
    val n: Int32,
    val d: Int32,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        n.encode(stream)
        d.encode(stream)
    }

    companion object : XdrElementDecoder<Price> {
        override fun decode(stream: XdrInputStream): Price {
            val n = me.rahimklaber.stellar.base.xdr.Int32.decode(stream)
            val d = me.rahimklaber.stellar.base.xdr.Int32.decode(stream)
            return Price(
                n,
                d,
            )
        }
    }
}
