package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

data class SCMap(val mapEntries: List<SCMapEntry>) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(mapEntries.size)
        mapEntries.forEach { entry ->
            entry.encode(stream)
        }
    }

    companion object : XdrElementDecoder<SCMap> {
        override fun decode(stream: XdrStream): SCMap {
            val size = stream.readInt()
            val list = mutableListOf<SCMapEntry>()
            repeat(size) {
                list.add(SCMapEntry.decode(stream))
            }

            return SCMap(list)
        }

    }
}
