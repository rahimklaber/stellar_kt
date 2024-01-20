package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// typedef string SCString<>;
///////////////////////////////////////////////////////////////////////////
data class SCString(val string: String): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(string.length)
        stream.writeBytes(string.encodeToByteArray())
    }

    companion object: XdrElementDecoder<SCString> {
        override fun decode(stream: XdrStream): SCString {
            val size = stream.readInt()
            val string = stream.readBytes(size).decodeToString()
            return SCString(string)
        }
    }
}
