package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

data class SCSpecTypeVec(val elementType: SCSpecTypeDef): XdrElement {
    override fun encode(stream: XdrStream) {
        elementType.encode(stream)
    }

    companion object: XdrElementDecoder<SCSpecTypeVec> {
        override fun decode(stream: XdrStream): SCSpecTypeVec {
            return SCSpecTypeVec(SCSpecTypeDef.decode(stream))
        }
    }
}