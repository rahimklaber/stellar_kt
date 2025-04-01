// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCPStatementType's original definition in the XDR file is:
 * ```
 * enum SCPStatementType
{
SCP_ST_PREPARE = 0,
SCP_ST_CONFIRM = 1,
SCP_ST_EXTERNALIZE = 2,
SCP_ST_NOMINATE = 3
};
 * ```
 */
enum class SCPStatementType(val value: Int) : XdrElement {
    SCP_ST_PREPARE(0),
    SCP_ST_CONFIRM(1),
    SCP_ST_EXTERNALIZE(2),
    SCP_ST_NOMINATE(3);

    companion object : XdrElementDecoder<SCPStatementType> {
        override fun decode(stream: XdrInputStream): SCPStatementType {
            return when (val value = stream.readInt()) {
                0 -> SCP_ST_PREPARE
                1 -> SCP_ST_CONFIRM
                2 -> SCP_ST_EXTERNALIZE
                3 -> SCP_ST_NOMINATE
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
