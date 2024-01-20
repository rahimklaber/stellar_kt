package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

sealed class SCSpecEntry(val kind: SCSpecEntryKind): XdrElement{

    override fun encode(stream: XdrStream) {
        kind.encode(stream)
    }
    data class FunctionV0(val functionV0: SCSpecFunctionV0): SCSpecEntry(SCSpecEntryKind.SC_SPEC_ENTRY_FUNCTION_V0){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            functionV0.encode(stream)
        }
    }
    data class UdtStructV0(val udtStructV0: SCSpecUDTStructV0): SCSpecEntry(SCSpecEntryKind.SC_SPEC_ENTRY_UDT_STRUCT_V0){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            udtStructV0.encode(stream)
        }
    }

    data class UdtUnionV0(val udtUnionV0: SCSpecUDTUnionV0): SCSpecEntry(SCSpecEntryKind.SC_SPEC_ENTRY_UDT_UNION_V0){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            udtUnionV0.encode(stream)
        }
    }

    data class UdtEnumV0(val udtEnumV0: SCSpecUDTEnumV0): SCSpecEntry(SCSpecEntryKind.SC_SPEC_ENTRY_UDT_ENUM_V0){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            udtEnumV0.encode(stream)
        }
    }
    data class ErrorEnumV0(val errorEnumV0: SCSpecUDTErrorEnumV0): SCSpecEntry(SCSpecEntryKind.SC_SPEC_ENTRY_UDT_ERROR_ENUM_V0){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            errorEnumV0.encode(stream)
        }
    }

    companion object: XdrElementDecoder<SCSpecEntry> {
        override fun decode(stream: XdrStream): SCSpecEntry {
            return when(val type = SCSpecEntryKind.decode(stream)){
                SCSpecEntryKind.SC_SPEC_ENTRY_FUNCTION_V0 -> FunctionV0(SCSpecFunctionV0.decode(stream))
                SCSpecEntryKind.SC_SPEC_ENTRY_UDT_STRUCT_V0 -> UdtStructV0(SCSpecUDTStructV0.decode(stream))
                SCSpecEntryKind.SC_SPEC_ENTRY_UDT_UNION_V0 -> UdtUnionV0(SCSpecUDTUnionV0.decode(stream))
                SCSpecEntryKind.SC_SPEC_ENTRY_UDT_ENUM_V0 -> UdtEnumV0(SCSpecUDTEnumV0.decode(stream))
                SCSpecEntryKind.SC_SPEC_ENTRY_UDT_ERROR_ENUM_V0 -> ErrorEnumV0(SCSpecUDTErrorEnumV0.decode(stream))
            }

        }
    }
}
