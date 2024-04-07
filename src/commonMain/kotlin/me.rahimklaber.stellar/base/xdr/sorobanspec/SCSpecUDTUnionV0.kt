package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.*

data class SCSpecUDTUnionV0(
    val doc: String,
    val lib: String,
    val name: String,
    val cases: List<SCSpecUDTUnionCaseV0>
): XdrElement{
    init {
        require(doc.length <= SC_SPEC_DOC_LIMIT){"doc has size limit"}
        require(lib.length <= 80){"lib has size limit"}
        require(name.length <= 60){"name has size limit"}
        require(cases.size <= 50){"cases has size limit"}
    }

    override fun encode(stream: XdrStream) {
        doc.encode(stream)
        lib.encode(stream)
        name.encode(stream)
        cases.encode(stream)
    }

    companion object: XdrElementDecoder<SCSpecUDTUnionV0> {
        override fun decode(stream: XdrStream): SCSpecUDTUnionV0 {
            val doc = String.decode(stream)
            val lib = String.decode(stream)
            val name = String.decode(stream)
            val cases = decodeXdrElementList<SCSpecUDTUnionCaseV0>(stream){
                SCSpecUDTUnionCaseV0.decode(stream)
            }

            return SCSpecUDTUnionV0(doc, lib, name, cases)
        }
    }
}
