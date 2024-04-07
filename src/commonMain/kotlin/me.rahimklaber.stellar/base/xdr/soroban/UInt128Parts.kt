package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// struct UInt128Parts {
//    uint64 hi;
//    uint64 lo;
//};
///////////////////////////////////////////////////////////////////////////
data class UInt128Parts(
    val hi: ULong,
    val low: ULong
): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeULong(hi)
        stream.writeULong(low)
    }

    companion object : XdrElementDecoder<UInt128Parts> {
        override fun decode(stream: XdrStream): UInt128Parts {
            return UInt128Parts(stream.readULong(), stream.readULong())
        }
    }
}
