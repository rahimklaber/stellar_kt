package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.*

///////////////////////////////////////////////////////////////////////////
// struct SCSpecUDTErrorEnumV0
//{
//    string doc<SC_SPEC_DOC_LIMIT>;
//    string lib<80>;
//    string name<60>;
//    SCSpecUDTErrorEnumCaseV0 cases<50>;
//};
///////////////////////////////////////////////////////////////////////////
data class SCSpecUDTErrorEnumV0(
    val doc: String,
    val lib: String,
    val name: String,
    val cases: List<SCSpecUDTErrorEnumCaseV0>
): XdrElement {
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

    companion object: XdrElementDecoder<SCSpecUDTErrorEnumV0> {
        override fun decode(stream: XdrStream): SCSpecUDTErrorEnumV0 {
            val doc = String.decode(stream)
            val lib = String.decode(stream)
            val name = String.decode(stream)
            val cases = decodeXdrElementList<SCSpecUDTErrorEnumCaseV0>(stream){
                SCSpecUDTErrorEnumCaseV0.decode(stream)
            }

            return SCSpecUDTErrorEnumV0(doc, lib, name, cases)
        }
    }
}
