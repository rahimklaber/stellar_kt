// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * CryptoKeyType's original definition in the XDR file is:
 * ```
 * enum CryptoKeyType
{
KEY_TYPE_ED25519 = 0,
KEY_TYPE_PRE_AUTH_TX = 1,
KEY_TYPE_HASH_X = 2,
KEY_TYPE_ED25519_SIGNED_PAYLOAD = 3,
// MUXED enum values for supported type are derived from the enum values
// above by ORing them with 0x100
KEY_TYPE_MUXED_ED25519 = 0x100
};
 * ```
 */
enum class CryptoKeyType(val value: Int) : XdrElement {
    KEY_TYPE_ED25519(0),
    KEY_TYPE_PRE_AUTH_TX(1),
    KEY_TYPE_HASH_X(2),
    KEY_TYPE_ED25519_SIGNED_PAYLOAD(3),
    KEY_TYPE_MUXED_ED25519(256);

    companion object : XdrElementDecoder<CryptoKeyType> {
        override fun decode(stream: XdrInputStream): CryptoKeyType {
            return when (val value = stream.readInt()) {
                0 -> KEY_TYPE_ED25519
                1 -> KEY_TYPE_PRE_AUTH_TX
                2 -> KEY_TYPE_HASH_X
                3 -> KEY_TYPE_ED25519_SIGNED_PAYLOAD
                256 -> KEY_TYPE_MUXED_ED25519
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
