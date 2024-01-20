package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.*

data class SCSpecUDTStructV0(
    val doc: String,
    val lib: String,
    val name: String,
    val fields: List<SCSpecUDTStructFieldV0>
): XdrElement {

    init {
        require(doc.length <= SC_SPEC_DOC_LIMIT){"doc has a max len"}
        require(lib.length <= 80){"lib has a max len"}
        require(name.length <= 60){"name has a max len"}
        require(fields.size <= 40){"fields has a max len"}

    }
    override fun encode(stream: XdrStream) {
        doc.encode(stream)
        lib.encode(stream)
        name.encode(stream)
        fields.encode(stream)
    }

    companion object: XdrElementDecoder<SCSpecUDTStructV0> {
        override fun decode(stream: XdrStream): SCSpecUDTStructV0 {
            val doc = String.decode(stream)
            val lib = String.decode(stream)
            val name = String.decode(stream)
            val fields = decodeXdrElementList(stream){
                SCSpecUDTStructFieldV0.decode(stream)
            }

            return SCSpecUDTStructV0(doc, lib, name, fields)
        }
    }
}
