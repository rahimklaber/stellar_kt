package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream
import me.rahimklaber.stellar.base.xdr.readIntNullable

///////////////////////////////////////////////////////////////////////////
// typedef opaque SCBytes<>;
///////////////////////////////////////////////////////////////////////////
data class SCBytes(val bytes: List<Byte>): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(bytes.size)
        stream.writeBytes(bytes.toByteArray())
    }

    companion object: XdrElementDecoder<SCBytes> {
        override fun decode(stream: XdrStream): SCBytes {
            val size = stream.readInt()
            val bytes = mutableListOf<Byte>()
            repeat(size){
                bytes.add(stream.readByte())
            }
            return SCBytes(bytes)
        }
    }
}
