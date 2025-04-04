// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SetOptionsResultCode's original definition in the XDR file is:
 * ```
 * enum SetOptionsResultCode
{
// codes considered as "success" for the operation
SET_OPTIONS_SUCCESS = 0,
// codes considered as "failure" for the operation
SET_OPTIONS_LOW_RESERVE = -1,      // not enough funds to add a signer
SET_OPTIONS_TOO_MANY_SIGNERS = -2, // max number of signers already reached
SET_OPTIONS_BAD_FLAGS = -3,        // invalid combination of clear/set flags
SET_OPTIONS_INVALID_INFLATION = -4,      // inflation account does not exist
SET_OPTIONS_CANT_CHANGE = -5,            // can no longer change this option
SET_OPTIONS_UNKNOWN_FLAG = -6,           // can't set an unknown flag
SET_OPTIONS_THRESHOLD_OUT_OF_RANGE = -7, // bad value for weight/threshold
SET_OPTIONS_BAD_SIGNER = -8,             // signer cannot be masterkey
SET_OPTIONS_INVALID_HOME_DOMAIN = -9,    // malformed home domain
SET_OPTIONS_AUTH_REVOCABLE_REQUIRED =
-10 // auth revocable is required for clawback
};
 * ```
 */
enum class SetOptionsResultCode(val value: Int) : XdrElement {
    SET_OPTIONS_SUCCESS(0),
    SET_OPTIONS_LOW_RESERVE(-1),
    SET_OPTIONS_TOO_MANY_SIGNERS(-2),
    SET_OPTIONS_BAD_FLAGS(-3),
    SET_OPTIONS_INVALID_INFLATION(-4),
    SET_OPTIONS_CANT_CHANGE(-5),
    SET_OPTIONS_UNKNOWN_FLAG(-6),
    SET_OPTIONS_THRESHOLD_OUT_OF_RANGE(-7),
    SET_OPTIONS_BAD_SIGNER(-8),
    SET_OPTIONS_INVALID_HOME_DOMAIN(-9),
    SET_OPTIONS_AUTH_REVOCABLE_REQUIRED(-10);

    companion object : XdrElementDecoder<SetOptionsResultCode> {
        override fun decode(stream: XdrInputStream): SetOptionsResultCode {
            return when (val value = stream.readInt()) {
                0 -> SET_OPTIONS_SUCCESS
                -1 -> SET_OPTIONS_LOW_RESERVE
                -2 -> SET_OPTIONS_TOO_MANY_SIGNERS
                -3 -> SET_OPTIONS_BAD_FLAGS
                -4 -> SET_OPTIONS_INVALID_INFLATION
                -5 -> SET_OPTIONS_CANT_CHANGE
                -6 -> SET_OPTIONS_UNKNOWN_FLAG
                -7 -> SET_OPTIONS_THRESHOLD_OUT_OF_RANGE
                -8 -> SET_OPTIONS_BAD_SIGNER
                -9 -> SET_OPTIONS_INVALID_HOME_DOMAIN
                -10 -> SET_OPTIONS_AUTH_REVOCABLE_REQUIRED
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
