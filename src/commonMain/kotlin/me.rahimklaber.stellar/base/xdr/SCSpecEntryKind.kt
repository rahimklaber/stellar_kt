// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCSpecEntryKind's original definition in the XDR file is:
 * ```
 * enum SCSpecEntryKind
{
SC_SPEC_ENTRY_FUNCTION_V0 = 0,
SC_SPEC_ENTRY_UDT_STRUCT_V0 = 1,
SC_SPEC_ENTRY_UDT_UNION_V0 = 2,
SC_SPEC_ENTRY_UDT_ENUM_V0 = 3,
SC_SPEC_ENTRY_UDT_ERROR_ENUM_V0 = 4
};
 * ```
 */
enum class SCSpecEntryKind(val value: Int) : XdrElement {
    SC_SPEC_ENTRY_FUNCTION_V0(0),
    SC_SPEC_ENTRY_UDT_STRUCT_V0(1),
    SC_SPEC_ENTRY_UDT_UNION_V0(2),
    SC_SPEC_ENTRY_UDT_ENUM_V0(3),
    SC_SPEC_ENTRY_UDT_ERROR_ENUM_V0(4);

    companion object : XdrElementDecoder<SCSpecEntryKind> {
        override fun decode(stream: XdrInputStream): SCSpecEntryKind {
            return when (val value = stream.readInt()) {
                0 -> SC_SPEC_ENTRY_FUNCTION_V0
                1 -> SC_SPEC_ENTRY_UDT_STRUCT_V0
                2 -> SC_SPEC_ENTRY_UDT_UNION_V0
                3 -> SC_SPEC_ENTRY_UDT_ENUM_V0
                4 -> SC_SPEC_ENTRY_UDT_ERROR_ENUM_V0
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
