package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

data class SCSpecTypeUDT(val name: String): XdrElement {
    init {
        require(name.length <= 60){
            "Names can be up to 60 chars in length"
        }
    }
    override fun encode(stream: XdrStream) {
        stream.writeInt(name.length)
        stream.writeBytes(name.encodeToByteArray())
    }

    companion object: XdrElementDecoder<SCSpecTypeUDT> {
        override fun decode(stream: XdrStream): SCSpecTypeUDT {
            val size = stream.readInt()
            val string = stream.readBytes(size).decodeToString()

            return SCSpecTypeUDT(string)
        }
    }
}
