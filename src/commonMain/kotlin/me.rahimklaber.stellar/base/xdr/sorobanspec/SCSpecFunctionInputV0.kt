package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.*

///////////////////////////////////////////////////////////////////////////
// struct SCSpecFunctionInputV0
//{
//    string doc<SC_SPEC_DOC_LIMIT>;
//    string name<30>;
//    SCSpecTypeDef type;
//};
///////////////////////////////////////////////////////////////////////////
data class SCSpecFunctionInputV0(
    val doc: String,
    val name: String,
    val type: SCSpecTypeDef,
): XdrElement{
    init {
        require(doc.length <= SC_SPEC_DOC_LIMIT){"doc has size limit"}
        require(name.length <= 30){"name has size limit"}
    }

    override fun encode(stream: XdrStream) {
        doc.encode(stream)
        name.encode(stream)
        type.encode(stream)
    }

    companion object: XdrElementDecoder<SCSpecFunctionInputV0> {
        override fun decode(stream: XdrStream): SCSpecFunctionInputV0 {
            val doc = String.decode(stream)
            val name = String.decode(stream)
            val type = SCSpecTypeDef.decode(stream)

            return SCSpecFunctionInputV0(doc, name, type)
        }
    }
}
