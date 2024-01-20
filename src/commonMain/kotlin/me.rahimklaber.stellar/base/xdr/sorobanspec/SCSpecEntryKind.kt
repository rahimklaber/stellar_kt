package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// enum SCSpecEntryKind
//{
//    SC_SPEC_ENTRY_FUNCTION_V0 = 0,
//    SC_SPEC_ENTRY_UDT_STRUCT_V0 = 1,
//    SC_SPEC_ENTRY_UDT_UNION_V0 = 2,
//    SC_SPEC_ENTRY_UDT_ENUM_V0 = 3,
//    SC_SPEC_ENTRY_UDT_ERROR_ENUM_V0 = 4
//};
///////////////////////////////////////////////////////////////////////////
enum class SCSpecEntryKind(val value: Int): XdrElement {
    SC_SPEC_ENTRY_FUNCTION_V0(0),
    SC_SPEC_ENTRY_UDT_STRUCT_V0(1),
    SC_SPEC_ENTRY_UDT_UNION_V0(2),
    SC_SPEC_ENTRY_UDT_ENUM_V0(3),
    SC_SPEC_ENTRY_UDT_ERROR_ENUM_V0(4);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<SCSpecEntryKind>{
        override fun decode(stream: XdrStream): SCSpecEntryKind {
            return when(val value = stream.readInt()){
                SC_SPEC_ENTRY_FUNCTION_V0.value -> SC_SPEC_ENTRY_FUNCTION_V0
                SC_SPEC_ENTRY_UDT_STRUCT_V0.value -> SC_SPEC_ENTRY_UDT_STRUCT_V0
                SC_SPEC_ENTRY_UDT_UNION_V0.value -> SC_SPEC_ENTRY_UDT_UNION_V0
                SC_SPEC_ENTRY_UDT_ENUM_V0.value -> SC_SPEC_ENTRY_UDT_ENUM_V0
                SC_SPEC_ENTRY_UDT_ERROR_ENUM_V0.value -> SC_SPEC_ENTRY_UDT_ERROR_ENUM_V0
                else -> throw IllegalArgumentException("could not decode SCSpecEntryKind for value $value")
            }
        }
    }

}