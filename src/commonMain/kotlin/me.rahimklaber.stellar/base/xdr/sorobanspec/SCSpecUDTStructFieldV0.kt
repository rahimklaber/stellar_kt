package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.*

///////////////////////////////////////////////////////////////////////////
// struct SCSpecUDTStructFieldV0
//{
//    string doc<SC_SPEC_DOC_LIMIT>;
//    string name<30>;
//    SCSpecTypeDef type;
//};
///////////////////////////////////////////////////////////////////////////
data class SCSpecUDTStructFieldV0(
    val doc: String,
    val name: String,
    val type: SCSpecTypeDef,
): XdrElement {
    init {
        require(doc.length <= SC_SPEC_DOC_LIMIT){"Docs have a max size"}
        require(name.length <= 30){"name has a max size"}
    }

    override fun encode(stream: XdrStream) {
        doc.encode(stream)
        name.encode(stream)
        type.encode(stream)
    }

    companion object: XdrElementDecoder<SCSpecUDTStructFieldV0>{
        override fun decode(stream: XdrStream): SCSpecUDTStructFieldV0 {
            val doc = String.decode(stream,)
            val name = String.decode(stream)
            val type = SCSpecTypeDef.decode(stream)

            return SCSpecUDTStructFieldV0(doc, name, type)
        }

    }
}