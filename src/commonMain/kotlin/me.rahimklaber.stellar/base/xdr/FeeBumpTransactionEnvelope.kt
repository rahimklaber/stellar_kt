package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct FeeBumpTransactionEnvelope
//{
//    FeeBumpTransaction tx;
//    /* Each decorated signature is a signature over the SHA256 hash of
//     * a TransactionSignaturePayload */
//    DecoratedSignature signatures<20>;
//};
///////////////////////////////////////////////////////////////////////////
data class FeeBumpTransactionEnvelope(
    val tx: FeeBumpTransaction,
    val signatures: List<DecoratedSignature>
): XdrElement{
    init {
        require(signatures.size <= 20){
            "A FeeBumpTransactionEnvelope can only have 20 signatures.\nCurrent amount: ${signatures.size}"
        }
    }

    override fun encode(stream: XdrStream) {
        tx.encode(stream)
        stream.writeInt(signatures.size)
        signatures.forEach {
            it.encode(stream)
        }
    }

    companion object: XdrElementDecoder<FeeBumpTransactionEnvelope> {
        override fun decode(stream: XdrStream): FeeBumpTransactionEnvelope {
            val tx = FeeBumpTransaction.decode(stream)
            val size = stream.readInt()
            val signatures = mutableListOf<DecoratedSignature>()
            for (i in 0 until size){
                signatures.add(DecoratedSignature.decode(stream))
            }
            return FeeBumpTransactionEnvelope(tx, signatures)
        }
    }
}
