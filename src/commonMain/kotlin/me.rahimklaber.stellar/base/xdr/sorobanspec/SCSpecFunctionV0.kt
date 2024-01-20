package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.*
import me.rahimklaber.stellar.base.xdr.soroban.SCSymbol

///////////////////////////////////////////////////////////////////////////
// struct SCSpecFunctionV0
//{
//    string doc<SC_SPEC_DOC_LIMIT>;
//    SCSymbol name;
//    SCSpecFunctionInputV0 inputs<10>;
//    SCSpecTypeDef outputs<1>;
//};
///////////////////////////////////////////////////////////////////////////
data class SCSpecFunctionV0(
    val doc: String,
    val name: SCSymbol,
    val inputs: List<SCSpecFunctionInputV0>,
    val outputs: List<SCSpecTypeDef>
): XdrElement{
    init {
        require(doc.length <= SC_SPEC_DOC_LIMIT){"doc has size limit"}
        require(inputs.size <= 10){"inputs have a max size"}
        require(outputs.size <= 1){"outputs have a max size"}
    }

    override fun encode(stream: XdrStream) {
        doc.encode(stream)
        name.encode(stream)
        inputs.encode(stream)
        outputs.encode(stream)
    }

    companion object: XdrElementDecoder<SCSpecFunctionV0> {
        override fun decode(stream: XdrStream): SCSpecFunctionV0 {
            val doc = String.decode(stream)
            val name = SCSymbol.decode(stream)
            val inputs = decodeXdrElementList(stream){
                SCSpecFunctionInputV0.decode(stream)
            }
            val outputs = decodeXdrElementList(stream){
                SCSpecTypeDef.decode(stream)
            }

            return SCSpecFunctionV0(doc, name, inputs, outputs)
        }
    }
}
