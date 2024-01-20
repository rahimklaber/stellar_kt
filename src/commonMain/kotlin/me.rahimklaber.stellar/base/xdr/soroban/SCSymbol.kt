package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// typedef string SCSymbol<SCSYMBOL_LIMIT>;
///////////////////////////////////////////////////////////////////////////
data class SCSymbol(val string: String): XdrElement {
    override fun encode(stream: XdrStream) {
        require(string.length <= SCSYMBOL_LIMT){
            "Symbols can be up to $SCSYMBOL_LIMT chars in length"
        }
        stream.writeInt(string.length)
        stream.writeBytes(string.encodeToByteArray())
    }

    companion object: XdrElementDecoder<SCSymbol> {
        override fun decode(stream: XdrStream): SCSymbol {
            val size = stream.readInt()
            val string = stream.readBytes(size).decodeToString()
            return SCSymbol(string)
        }
    }
}
