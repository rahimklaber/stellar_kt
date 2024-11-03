package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union PublicKey switch (PublicKeyType type)
//{
//case PUBLIC_KEY_TYPE_ED25519:
//    uint256 ed25519;
//};
///////////////////////////////////////////////////////////////////////////

sealed class PublicKey(val type: PublicKeyType) : XdrElement{

    data class PublicKeyEd25519(
        val ed25519: Uint256
    ) : PublicKey(PublicKeyType.PUBLIC_KEY_TYPE_ED25519) {
        override fun encode(stream: XdrStream) {
            type.encode(stream)
            ed25519.encode(stream)
        }

    }

    companion object : XdrElementDecoder<PublicKeyEd25519>{
        override fun decode(stream: XdrStream): PublicKeyEd25519 {

            return when(PublicKeyType.decode(stream)){
                PublicKeyType.PUBLIC_KEY_TYPE_ED25519 -> {
                    val ed25519 = Uint256.decode(stream)

                    PublicKeyEd25519(ed25519)
                }
            }
        }

    }
}

