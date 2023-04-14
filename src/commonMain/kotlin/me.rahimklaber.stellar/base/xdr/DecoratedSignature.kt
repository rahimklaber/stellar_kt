package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct DecoratedSignature
//{
//    SignatureHint hint;  // last 4 bytes of the public key, used as a hint
//    Signature signature; // actual signature
//};
///////////////////////////////////////////////////////////////////////////
data class DecoratedSignature(
    val hint: SignatureHint,
    val signature: Signature
): XdrElement{
    override fun encode(stream: XdrStream) {
        hint.encode(stream)
        signature.encode(stream)
    }

    companion object: XdrElementDecoder<DecoratedSignature> {
        override fun decode(stream: XdrStream): DecoratedSignature {
            val hint = SignatureHint.decode(stream)
            val signature = Signature.decode(stream)

            return DecoratedSignature(hint, signature)
        }
    }
}