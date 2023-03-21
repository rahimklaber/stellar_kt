package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union SignerKey switch (SignerKeyType type)
//{
//case SIGNER_KEY_TYPE_ED25519:
//    uint256 ed25519;
//case SIGNER_KEY_TYPE_PRE_AUTH_TX:
//    /* SHA-256 Hash of TransactionSignaturePayload structure */
//    uint256 preAuthTx;
//case SIGNER_KEY_TYPE_HASH_X:
//    /* Hash of random 256 bit preimage X */
//    uint256 hashX;
//case SIGNER_KEY_TYPE_ED25519_SIGNED_PAYLOAD:
//    struct
//    {
//        /* Public key that must sign the payload. */
//        uint256 ed25519;
//        /* Payload to be raw signed by ed25519. */
//        opaque payload<64>;
//    } ed25519SignedPayload;
//};
///////////////////////////////////////////////////////////////////////////
sealed class SignerKey(
    val type: SignerKeyType
) : XdrElement {
    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    data class Ed25519SignerKey(val ed25519: Uint256) : SignerKey(SignerKeyType.SIGNER_KEY_TYPE_ED25519){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            ed25519.encode(stream)
        }
    }
    data class PreAuthSignerKey(val preAuthTx: Uint256) : SignerKey(SignerKeyType.SIGNER_KEY_TYPE_HASH_X){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            preAuthTx.encode(stream)
        }
    }
    data class HashXSignerKey(val hashX: Uint256) : SignerKey(SignerKeyType.SIGNER_KEY_TYPE_HASH_X){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            hashX.encode(stream)
        }
    }
    data class Ed25519SignedPayloadSignerKey(val ed25519SignedPayload: SignerKeyEd25519SignedPayload) : SignerKey(SignerKeyType.SIGNER_KEY_TYPE_ED25519_SIGNED_PAYLOAD){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            ed25519SignedPayload.encode(stream)
        }
    }

    companion object: XdrElementDecoder<SignerKey>{
        override fun decode(stream: XdrStream): SignerKey {
            return when(SignerKeyType.decode(stream)){
                SignerKeyType.SIGNER_KEY_TYPE_ED25519 ->{
                    val ed25519= Uint256.decode(stream)
                    Ed25519SignerKey(ed25519)
                }
                SignerKeyType.SIGNER_KEY_TYPE_PRE_AUTH_TX -> {
                    val preAuthTx = Uint256.decode(stream)
                    PreAuthSignerKey(preAuthTx)
                }
                SignerKeyType.SIGNER_KEY_TYPE_HASH_X -> {
                    val hashX = Uint256.decode(stream)
                    HashXSignerKey(hashX)
                }
                SignerKeyType.SIGNER_KEY_TYPE_ED25519_SIGNED_PAYLOAD -> {
                    val payload = SignerKeyEd25519SignedPayload.decode(stream)
                    Ed25519SignedPayloadSignerKey(payload)
                }
            }
        }
    }
}

data class SignerKeyEd25519SignedPayload(
    val ed25519: Uint256,
    val payload: ByteArray
) : XdrElement {
    init {
        require(payload.size <= 64)
    }

    override fun encode(stream: XdrStream) {
        ed25519.encode(stream)
        stream.writeInt(payload.size)
        stream.writeBytes(payload)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SignerKeyEd25519SignedPayload

        if (ed25519 != other.ed25519) return false
        if (!payload.contentEquals(other.payload)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ed25519.hashCode()
        result = 31 * result + payload.contentHashCode()
        return result
    }

    companion object: XdrElementDecoder<SignerKeyEd25519SignedPayload>{
        override fun decode(stream: XdrStream): SignerKeyEd25519SignedPayload {
            val ed25519 = Uint256.decode(stream)
            val length = stream.readInt()
            val payload = stream.readBytes(length)
            return SignerKeyEd25519SignedPayload(ed25519, payload)
        }

    }
}