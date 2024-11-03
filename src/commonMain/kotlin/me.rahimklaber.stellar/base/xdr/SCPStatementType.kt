package me.rahimklaber.stellar.base.xdr

/**
 * enum SCPStatementType
 * {
 *     SCP_ST_PREPARE = 0,
 *     SCP_ST_CONFIRM = 1,
 *     SCP_ST_EXTERNALIZE = 2,
 *     SCP_ST_NOMINATE = 3
 * };
 */
enum class SCPStatementType(
    val `value`: Int,
) : XdrElement {
    SCP_ST_PREPARE(0),
    SCP_ST_CONFIRM(1),
    SCP_ST_EXTERNALIZE(2),
    SCP_ST_NOMINATE(3),
    ;

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object : XdrElementDecoder<SCPStatementType> {
        override fun decode(stream: XdrStream): SCPStatementType {
            val value = stream.readInt()
            return entries.find { it.value == value } ?: xdrDecodeError("Could not find enum case")
        }
    }
}