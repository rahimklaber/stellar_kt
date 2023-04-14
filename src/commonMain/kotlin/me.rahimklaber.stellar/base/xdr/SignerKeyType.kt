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
enum class SignerKeyType(val value: Int): XdrElement {
    SIGNER_KEY_TYPE_ED25519(0),
    SIGNER_KEY_TYPE_PRE_AUTH_TX(1),
    SIGNER_KEY_TYPE_HASH_X(2),
    SIGNER_KEY_TYPE_ED25519_SIGNED_PAYLOAD(3),;

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<SignerKeyType>{
        override fun decode(stream: XdrStream): SignerKeyType {
            return when(val value = stream.readInt()){
                0 -> SIGNER_KEY_TYPE_ED25519
                1 -> SIGNER_KEY_TYPE_PRE_AUTH_TX
                2 -> SIGNER_KEY_TYPE_HASH_X
                3 -> SIGNER_KEY_TYPE_ED25519_SIGNED_PAYLOAD
                else -> throw IllegalArgumentException("cannot decode $value for SignerKeyType")
            }
        }

    }
}