package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

data class SCSpecTypeBytesN(val n: UInt): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(n.toInt())
    }

    companion object: XdrElementDecoder<SCSpecTypeBytesN> {
        override fun decode(stream: XdrStream): SCSpecTypeBytesN {
            val n = stream.readInt().toUInt()
            return SCSpecTypeBytesN(n)
        }
    }
}
