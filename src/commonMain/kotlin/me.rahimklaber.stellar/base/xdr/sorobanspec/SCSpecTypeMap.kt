package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

data class SCSpecTypeMap(val keyType: SCSpecTypeDef, val valueType: SCSpecTypeDef): XdrElement {
    override fun encode(stream: XdrStream) {
        keyType.encode(stream)
        valueType.encode(stream)
    }

    companion object: XdrElementDecoder<SCSpecTypeMap> {
        override fun decode(stream: XdrStream): SCSpecTypeMap {
            return SCSpecTypeMap(SCSpecTypeDef.decode(stream), SCSpecTypeDef.decode(stream))
        }
    }
}