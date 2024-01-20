package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

data class SCSpecTypeTuple(val valueTypes: List<SCSpecTypeDef>): XdrElement {

    init {
        require(valueTypes.size <= 12){"Tuples can have a max size of 12"}
    }
    override fun encode(stream: XdrStream) {
        stream.writeInt(valueTypes.size)
        valueTypes.forEach { type ->
            type.encode(stream)
        }
    }

    companion object: XdrElementDecoder<SCSpecTypeTuple> {
        override fun decode(stream: XdrStream): SCSpecTypeTuple {
            val size = stream.readInt()
            val types = mutableListOf<SCSpecTypeDef>()
            repeat(size){
                types.add(SCSpecTypeDef.decode(stream))
            }
            return SCSpecTypeTuple(types)
        }
    }
}
