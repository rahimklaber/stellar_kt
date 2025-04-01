// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCErrorCode's original definition in the XDR file is:
 * ```
 * enum SCErrorCode
{
SCEC_ARITH_DOMAIN = 0,      // Some arithmetic was undefined (overflow, divide-by-zero).
SCEC_INDEX_BOUNDS = 1,      // Something was indexed beyond its bounds.
SCEC_INVALID_INPUT = 2,     // User provided some otherwise-bad data.
SCEC_MISSING_VALUE = 3,     // Some value was required but not provided.
SCEC_EXISTING_VALUE = 4,    // Some value was provided where not allowed.
SCEC_EXCEEDED_LIMIT = 5,    // Some arbitrary limit -- gas or otherwise -- was hit.
SCEC_INVALID_ACTION = 6,    // Data was valid but action requested was not.
SCEC_INTERNAL_ERROR = 7,    // The host detected an error in its own logic.
SCEC_UNEXPECTED_TYPE = 8,   // Some type wasn't as expected.
SCEC_UNEXPECTED_SIZE = 9    // Something's size wasn't as expected.
};
 * ```
 */
enum class SCErrorCode(val value: Int) : XdrElement {
    SCEC_ARITH_DOMAIN(0),
    SCEC_INDEX_BOUNDS(1),
    SCEC_INVALID_INPUT(2),
    SCEC_MISSING_VALUE(3),
    SCEC_EXISTING_VALUE(4),
    SCEC_EXCEEDED_LIMIT(5),
    SCEC_INVALID_ACTION(6),
    SCEC_INTERNAL_ERROR(7),
    SCEC_UNEXPECTED_TYPE(8),
    SCEC_UNEXPECTED_SIZE(9);

    companion object : XdrElementDecoder<SCErrorCode> {
        override fun decode(stream: XdrInputStream): SCErrorCode {
            return when (val value = stream.readInt()) {
                0 -> SCEC_ARITH_DOMAIN
                1 -> SCEC_INDEX_BOUNDS
                2 -> SCEC_INVALID_INPUT
                3 -> SCEC_MISSING_VALUE
                4 -> SCEC_EXISTING_VALUE
                5 -> SCEC_EXCEEDED_LIMIT
                6 -> SCEC_INVALID_ACTION
                7 -> SCEC_INTERNAL_ERROR
                8 -> SCEC_UNEXPECTED_TYPE
                9 -> SCEC_UNEXPECTED_SIZE
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
