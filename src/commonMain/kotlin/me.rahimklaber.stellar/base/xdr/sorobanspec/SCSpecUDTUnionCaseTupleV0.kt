package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.*

data class SCSpecUDTUnionCaseTupleV0(
    val doc: String,
    val name: String,
    val type: List<SCSpecTypeDef>
): XdrElement {
    init {
        require(doc.length <= SC_SPEC_DOC_LIMIT){"doc has size limit"}
        require(name.length <= 60){"name has size limit"}
        require(type.size <= 12){"type has size limit"}
    }

    override fun encode(stream: XdrStream) {
        doc.encode(stream)
        name.encode(stream)
        type.encode(stream)
    }

    companion object: XdrElementDecoder<SCSpecUDTUnionCaseTupleV0> {
        override fun decode(stream: XdrStream): SCSpecUDTUnionCaseTupleV0 {
            val doc = String.decode(stream)
            val name = String.decode(stream)
            val type = decodeXdrElementList(stream){
                SCSpecTypeDef.decode(stream)
            }

            return SCSpecUDTUnionCaseTupleV0(doc, name, type)
        }
    }
}
