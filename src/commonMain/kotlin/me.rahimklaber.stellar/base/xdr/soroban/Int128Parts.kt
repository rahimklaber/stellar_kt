package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// // A signed int128 has a high sign bit and 127 value bits. We break it into a
//// signed high int64 (that carries the sign bit and the high 63 value bits) and
//// a low unsigned uint64 that carries the low 64 bits. This will sort in
//// generated code in the same order the underlying int128 sorts.
//struct Int128Parts {
//    int64 hi;
//    uint64 lo;
//};
///////////////////////////////////////////////////////////////////////////
data class Int128Parts(
    val hi: Long,
    val low: ULong
): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeLong(hi)
        stream.writeULong(low)
    }

    companion object : XdrElementDecoder<Int128Parts> {
        override fun decode(stream: XdrStream): Int128Parts {
            return Int128Parts(stream.readLong(), stream.readULong())
        }
    }
}
