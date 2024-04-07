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
data class SCSpecUDTErrorEnumCaseV0(
    val doc: String,
    val name: String,
    val value: UInt,
): XdrElement {
    init {
        require(doc.length <= SC_SPEC_DOC_LIMIT){"doc has size limit"}
        require(name.length <= 60){"name has size limit"}
    }

    override fun encode(stream: XdrStream) {
        doc.encode(stream)
        name.encode(stream)
        stream.writeInt(value.toInt())
    }

    companion object: XdrElementDecoder<SCSpecUDTErrorEnumCaseV0> {
        override fun decode(stream: XdrStream): SCSpecUDTErrorEnumCaseV0 {
            val doc = String.decode(stream)
            val name = String.decode(stream)
            val value = stream.readInt().toUInt()

            return SCSpecUDTErrorEnumCaseV0(doc, name, value)
        }
    }
}
