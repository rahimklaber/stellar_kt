package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// struct Int256Parts {
//    int64 hi_hi;
//    uint64 hi_lo;
//    uint64 lo_hi;
//    uint64 lo_lo;
//};
///////////////////////////////////////////////////////////////////////////
data class UInt256Parts(
    val hi: UInt128Parts,
    val lo: UInt128Parts
): XdrElement {
    override fun encode(stream: XdrStream) {
        hi.encode(stream)
        lo.encode(stream)
    }

    companion object : XdrElementDecoder<UInt256Parts > {
        override fun decode(stream: XdrStream): UInt256Parts  {
            return UInt256Parts (UInt128Parts.decode(stream), UInt128Parts.decode(stream))
        }
    }
}
