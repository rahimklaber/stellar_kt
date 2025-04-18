// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCSpecUDTUnionCaseV0Kind's original definition in the XDR file is:
 * ```
 * enum SCSpecUDTUnionCaseV0Kind
{
SC_SPEC_UDT_UNION_CASE_VOID_V0 = 0,
SC_SPEC_UDT_UNION_CASE_TUPLE_V0 = 1
};
 * ```
 */
enum class SCSpecUDTUnionCaseV0Kind(val value: Int) : XdrElement {
    SC_SPEC_UDT_UNION_CASE_VOID_V0(0),
    SC_SPEC_UDT_UNION_CASE_TUPLE_V0(1);

    companion object : XdrElementDecoder<SCSpecUDTUnionCaseV0Kind> {
        override fun decode(stream: XdrInputStream): SCSpecUDTUnionCaseV0Kind {
            return when (val value = stream.readInt()) {
                0 -> SC_SPEC_UDT_UNION_CASE_VOID_V0
                1 -> SC_SPEC_UDT_UNION_CASE_TUPLE_V0
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
