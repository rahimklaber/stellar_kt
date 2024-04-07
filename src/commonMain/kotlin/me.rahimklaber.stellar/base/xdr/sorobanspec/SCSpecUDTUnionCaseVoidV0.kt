package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.*

data class SCSpecUDTUnionCaseVoidV0(
    val doc: String,
    val name: String,
): XdrElement{
    init {
        require(doc.length <= SC_SPEC_DOC_LIMIT){"doc has size limit"}
        require(name.length <= 60){"name has size limit"}
    }

    override fun encode(stream: XdrStream) {
        doc.encode(stream)
        name.encode(stream)
    }

    companion object: XdrElementDecoder<SCSpecUDTUnionCaseVoidV0> {
        override fun decode(stream: XdrStream): SCSpecUDTUnionCaseVoidV0 {
            val doc = String.decode(stream)
            val name = String.decode(stream)

            return SCSpecUDTUnionCaseVoidV0(doc, name)
        }
    }
}
