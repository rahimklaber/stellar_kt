package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct TransactionV0Envelope
//{
//    TransactionV0 tx;
//    /* Each decorated signature is a signature over the SHA256 hash of
//     * a TransactionSignaturePayload */
//    DecoratedSignature signatures<20>;
//};
///////////////////////////////////////////////////////////////////////////
data class TransactionV0Envelope(
    val tx: TransactionV0,
    val signatures: List<DecoratedSignature>
): XdrElement{
    init {
        require(signatures.size <= 20){
            "A transactionEnvelope can only have 20 signatures.\nCurrent amount: ${signatures.size}"
        }
    }

    override fun encode(stream: XdrStream) {
        tx.encode(stream)
        stream.writeInt(signatures.size)
        signatures.forEach { it.encode(stream) }
    }

    companion object: XdrElementDecoder<TransactionV0Envelope> {
        override fun decode(stream: XdrStream): TransactionV0Envelope {
            val tx = TransactionV0.decode(stream)
            val size = stream.readInt()
            val signatures = mutableListOf<DecoratedSignature>()
            for(i in 0 until size){
                signatures.add(DecoratedSignature.decode(stream))
            }
            return TransactionV0Envelope(tx, signatures)
        }
    }
}
