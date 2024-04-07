package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// // A signed int256 has a high sign bit and 255 value bits. We break it into a
//// signed high int64 (that carries the sign bit and the high 63 value bits) and
//// three low unsigned `uint64`s that carry the lower bits. This will sort in
//// generated code in the same order the underlying int256 sorts.
//struct Int256Parts {
//    int64 hi_hi;
//    uint64 hi_lo;
//    uint64 lo_hi;
//    uint64 lo_lo;
//};
///////////////////////////////////////////////////////////////////////////
data class Int256Parts(
    val hi: Int128Parts,
    val lo: UInt128Parts
): XdrElement {
    override fun encode(stream: XdrStream) {
        hi.encode(stream)
        lo.encode(stream)
    }

    companion object : XdrElementDecoder<Int256Parts > {
        override fun decode(stream: XdrStream): Int256Parts  {
            return Int256Parts (Int128Parts.decode(stream), UInt128Parts.decode(stream))
        }
    }
}
