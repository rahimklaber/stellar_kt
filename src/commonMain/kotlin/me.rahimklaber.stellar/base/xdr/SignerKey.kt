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
) {
    data class Ed25519SignerKey(val ed25519: Uint256) : SignerKey(SignerKeyType.SIGNER_KEY_TYPE_ED25519)
    data class PreAuthSignerKey(val preAuthTx: Uint256) : SignerKey(SignerKeyType.SIGNER_KEY_TYPE_HASH_X)
    data class HashXSignerKey(val hashX: Uint256) : SignerKey(SignerKeyType.SIGNER_KEY_TYPE_HASH_X)
    data class Ed25518SignedPayloadSignerKey(val ed25519SignedPayload: SignerKeyEd25519SignedPayload) : SignerKey(SignerKeyType.SIGNER_KEY_TYPE_ED25519_SIGNED_PAYLOAD)
}

data class SignerKeyEd25519SignedPayload(
    val ed25519: Uint256,
    val payload: ByteArray
) {
    init {
        require(payload.size <= 64)
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
}