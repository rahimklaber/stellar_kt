package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

data class SCMapEntry(
    val key: SCVal,
    val value: SCVal,
): XdrElement {
    override fun encode(stream: XdrStream) {
        key.encode(stream)
        value.encode(stream)
    }

    companion object: XdrElementDecoder<SCMapEntry> {
        override fun decode(stream: XdrStream): SCMapEntry {
            val key = SCVal.decode(stream)
            val value = SCVal.decode(stream)
            return SCMapEntry(key, value)
        }
    }

}
