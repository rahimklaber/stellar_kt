package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream
import me.rahimklaber.stellar.base.xdr.encodeNullable

data class SCSpecTypeResult(
    val okType: SCSpecTypeDef,
    val errorType: SCSpecTypeDef,
): XdrElement {
    override fun encode(stream: XdrStream) {
        okType.encode(stream)
        errorType.encode(stream)
    }

    companion object: XdrElementDecoder<SCSpecTypeResult> {
        override fun decode(stream: XdrStream): SCSpecTypeResult {
            return SCSpecTypeResult(SCSpecTypeDef.decode(stream), SCSpecTypeDef.decode(stream))
        }
    }
}