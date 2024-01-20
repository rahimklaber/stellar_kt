package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

data class SCVec(val vals: List<SCVal>): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(vals.size)
        vals.forEach { scVal -> scVal.encode(stream) }
    }

    companion object: XdrElementDecoder<SCVec> {
        override fun decode(stream: XdrStream): SCVec {
            val size = stream.readInt()
            val list = mutableListOf<SCVal>()
            repeat(size){
                list.add(SCVal.decode(stream))
            }
            return SCVec(list)
        }
    }
}
