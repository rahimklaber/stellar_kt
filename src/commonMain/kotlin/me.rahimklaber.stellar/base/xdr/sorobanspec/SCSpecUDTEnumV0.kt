package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.*

///////////////////////////////////////////////////////////////////////////
// struct SCSpecUDTEnumV0
//{
//    string doc<SC_SPEC_DOC_LIMIT>;
//    string lib<80>;
//    string name<60>;
//    SCSpecUDTEnumCaseV0 cases<50>;
//};
///////////////////////////////////////////////////////////////////////////
data class SCSpecUDTEnumV0(
    val doc: String,
    val lib: String,
    val name: String,
    val cases: List<SCSpecUDTEnumCaseV0>
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

    companion object: XdrElementDecoder<SCSpecUDTEnumV0> {
        override fun decode(stream: XdrStream): SCSpecUDTEnumV0 {
            val doc = String.decode(stream)
            val lib = String.decode(stream)
            val name = String.decode(stream)
            val cases = decodeXdrElementList<SCSpecUDTEnumCaseV0>(stream){
                SCSpecUDTEnumCaseV0.decode(stream)
            }

            return SCSpecUDTEnumV0(doc, lib, name, cases)
        }
    }
}
