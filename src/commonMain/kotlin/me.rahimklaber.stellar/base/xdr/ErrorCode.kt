// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ErrorCode's original definition in the XDR file is:
 * ```
 * enum ErrorCode
{
ERR_MISC = 0, // Unspecific error
ERR_DATA = 1, // Malformed data
ERR_CONF = 2, // Misconfiguration error
ERR_AUTH = 3, // Authentication failure
ERR_LOAD = 4  // System overloaded
};
 * ```
 */
enum class ErrorCode(val value: Int) : XdrElement {
    ERR_MISC(0),
    ERR_DATA(1),
    ERR_CONF(2),
    ERR_AUTH(3),
    ERR_LOAD(4);

    companion object : XdrElementDecoder<ErrorCode> {
        override fun decode(stream: XdrInputStream): ErrorCode {
            return when (val value = stream.readInt()) {
                0 -> ERR_MISC
                1 -> ERR_DATA
                2 -> ERR_CONF
                3 -> ERR_AUTH
                4 -> ERR_LOAD
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
