package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// union SCSpecUDTUnionCaseV0 switch (SCSpecUDTUnionCaseV0Kind kind)
//{
//case SC_SPEC_UDT_UNION_CASE_VOID_V0:
//    SCSpecUDTUnionCaseVoidV0 voidCase;
//case SC_SPEC_UDT_UNION_CASE_TUPLE_V0:
//    SCSpecUDTUnionCaseTupleV0 tupleCase;
//};
///////////////////////////////////////////////////////////////////////////
sealed class SCSpecUDTUnionCaseV0(val type: SCSpecUDTUnionCaseV0Kind): XdrElement {

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    data class Void(val void: SCSpecUDTUnionCaseVoidV0): SCSpecUDTUnionCaseV0(SCSpecUDTUnionCaseV0Kind.SC_SPEC_UDT_UNION_CASE_VOID_V0){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            void.encode(stream)
        }
    }
    data class Tuple(val tuple: SCSpecUDTUnionCaseTupleV0): SCSpecUDTUnionCaseV0(SCSpecUDTUnionCaseV0Kind.SC_SPEC_UDT_UNION_CASE_TUPLE_V0){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            tuple.encode(stream)
        }
    }

    companion object: XdrElementDecoder<SCSpecUDTUnionCaseV0> {
        override fun decode(stream: XdrStream): SCSpecUDTUnionCaseV0 {
            return when(SCSpecUDTUnionCaseV0Kind.decode(stream)){
                SCSpecUDTUnionCaseV0Kind.SC_SPEC_UDT_UNION_CASE_VOID_V0 -> Void(SCSpecUDTUnionCaseVoidV0.decode(stream))
                SCSpecUDTUnionCaseV0Kind.SC_SPEC_UDT_UNION_CASE_TUPLE_V0 -> Tuple(SCSpecUDTUnionCaseTupleV0.decode(stream))
            }
        }
    }
}