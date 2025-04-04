// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ManageDataResultCode's original definition in the XDR file is:
 * ```
 * enum ManageDataResultCode
{
// codes considered as "success" for the operation
MANAGE_DATA_SUCCESS = 0,
// codes considered as "failure" for the operation
MANAGE_DATA_NOT_SUPPORTED_YET =
-1, // The network hasn't moved to this protocol change yet
MANAGE_DATA_NAME_NOT_FOUND =
-2, // Trying to remove a Data Entry that isn't there
MANAGE_DATA_LOW_RESERVE = -3, // not enough funds to create a new Data Entry
MANAGE_DATA_INVALID_NAME = -4 // Name not a valid string
};
 * ```
 */
enum class ManageDataResultCode(val value: Int) : XdrElement {
    MANAGE_DATA_SUCCESS(0),
    MANAGE_DATA_NOT_SUPPORTED_YET(-1),
    MANAGE_DATA_NAME_NOT_FOUND(-2),
    MANAGE_DATA_LOW_RESERVE(-3),
    MANAGE_DATA_INVALID_NAME(-4);

    companion object : XdrElementDecoder<ManageDataResultCode> {
        override fun decode(stream: XdrInputStream): ManageDataResultCode {
            return when (val value = stream.readInt()) {
                0 -> MANAGE_DATA_SUCCESS
                -1 -> MANAGE_DATA_NOT_SUPPORTED_YET
                -2 -> MANAGE_DATA_NAME_NOT_FOUND
                -3 -> MANAGE_DATA_LOW_RESERVE
                -4 -> MANAGE_DATA_INVALID_NAME
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
