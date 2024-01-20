package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

enum class SCSpecUDTUnionCaseV0Kind(val value: Int): XdrElement {
    SC_SPEC_UDT_UNION_CASE_VOID_V0(0),
    SC_SPEC_UDT_UNION_CASE_TUPLE_V0(1);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<SCSpecUDTUnionCaseV0Kind> {
        override fun decode(stream: XdrStream): SCSpecUDTUnionCaseV0Kind {
            return when(val value = stream.readInt()){
                SC_SPEC_UDT_UNION_CASE_VOID_V0.value -> SC_SPEC_UDT_UNION_CASE_VOID_V0
                SC_SPEC_UDT_UNION_CASE_TUPLE_V0.value -> SC_SPEC_UDT_UNION_CASE_TUPLE_V0
                else -> throw IllegalArgumentException("Cannot decode SCSpecUDTUnionCaseV0Kind for value $value")
            }
        }
    }
}