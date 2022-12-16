package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum SignerKeyType
//{
//    SIGNER_KEY_TYPE_ED25519 = KEY_TYPE_ED25519,
//    SIGNER_KEY_TYPE_PRE_AUTH_TX = KEY_TYPE_PRE_AUTH_TX,
//    SIGNER_KEY_TYPE_HASH_X = KEY_TYPE_HASH_X,
//    SIGNER_KEY_TYPE_ED25519_SIGNED_PAYLOAD = KEY_TYPE_ED25519_SIGNED_PAYLOAD
//};
///////////////////////////////////////////////////////////////////////////
enum class SignerKeyType(val value: Int) {
    SIGNER_KEY_TYPE_ED25519(0),
    SIGNER_KEY_TYPE_PRE_AUTH_TX(1),
    SIGNER_KEY_TYPE_HASH_X(2),
    SIGNER_KEY_TYPE_ED25519_SIGNED_PAYLOAD(3),
}