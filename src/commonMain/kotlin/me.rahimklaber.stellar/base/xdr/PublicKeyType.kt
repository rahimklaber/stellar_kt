package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum PublicKeyType
//{
//    PUBLIC_KEY_TYPE_ED25519 = KEY_TYPE_ED25519
//};
///////////////////////////////////////////////////////////////////////////
enum class PublicKeyType(val value: Int) : XdrElement {
    PUBLIC_KEY_TYPE_ED25519(0);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object : XdrElementDecoder<PublicKeyType>{
        override fun decode(stream: XdrStream): PublicKeyType {
            return when(val value = stream.readInt()){
                PUBLIC_KEY_TYPE_ED25519.value -> PUBLIC_KEY_TYPE_ED25519
                else -> throw IllegalArgumentException("Cannot decode value $value for PublicKeyType")
            }
        }

    }
}