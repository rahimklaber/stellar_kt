package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

data class SCSpecTypeOption(
    val valueType: SCSpecTypeDef
): XdrElement {
    override fun encode(stream: XdrStream) {
        valueType.encode(stream)
    }

    companion object: XdrElementDecoder<SCSpecTypeOption> {
        override fun decode(stream: XdrStream): SCSpecTypeOption {
            return SCSpecTypeOption(SCSpecTypeDef.decode(stream))
        }
    }
}