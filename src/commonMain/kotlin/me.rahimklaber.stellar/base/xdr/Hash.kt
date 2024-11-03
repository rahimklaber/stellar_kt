package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline

///////////////////////////////////////////////////////////////////////////
// typedef opaque Hash[32];
///////////////////////////////////////////////////////////////////////////
@JvmInline
value class Hash(val byteArray: ByteArray) : XdrElement {
    init {
        require(byteArray.size == 32){"Hashes are 256 bit."}
    }

    override fun encode(stream: XdrStream) {
        stream.writeBytes(byteArray)
    }

    companion object: XdrElementDecoder<Hash>{
        override fun decode(stream: XdrStream): Hash {
            return Hash(stream.readBytes(32))
        }

    }
}