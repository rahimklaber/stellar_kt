package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum CryptoKeyType
//{
//    KEY_TYPE_ED25519 = 0,
//    KEY_TYPE_PRE_AUTH_TX = 1,
//    KEY_TYPE_HASH_X = 2,
//    KEY_TYPE_ED25519_SIGNED_PAYLOAD = 3,
//    // MUXED enum values for supported type are derived from the enum values
//    // above by ORing them with 0x100
//    KEY_TYPE_MUXED_ED25519 = 0x100
//};
///////////////////////////////////////////////////////////////////////////
enum class CryptoKeyType(val value: Int) {
    KEY_TYPE_ED25519(0),
    KEY_TYPE_PRE_AUTH_TX(1),
    KEY_TYPE_HASH_X(2),
    KEY_TYPE_ED25519_SIGNED_PAYLOAD(3),
    KEY_TYPE_MUXED_ED25519(256)
}