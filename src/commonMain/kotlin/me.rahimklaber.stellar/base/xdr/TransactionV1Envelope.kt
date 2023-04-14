package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct TransactionV1Envelope
//{
//    Transaction tx;
//    /* Each decorated signature is a signature over the SHA256 hash of
//     * a TransactionSignaturePayload */
//    DecoratedSignature signatures<20>;
//};
///////////////////////////////////////////////////////////////////////////
data class TransactionV1Envelope(
    val tx: Transaction,
    val signatures: List<DecoratedSignature>
): XdrElement{
    init {
        require(signatures.size <= 20){
            "A transaction envelope can only have 20 signatures.\nCurrent amount: ${signatures.size}"
        }
    }

    override fun encode(stream: XdrStream) {
        tx.encode(stream)
        stream.writeInt(signatures.size)
        signatures.forEach {
            it.encode(stream)
        }
    }

    companion object: XdrElementDecoder<TransactionV1Envelope> {
        override fun decode(stream: XdrStream): TransactionV1Envelope {
            val tx = Transaction.decode(stream)
            val size = stream.readInt()
            val signatures = mutableListOf<DecoratedSignature>()
            for (i in 0 until size){
                signatures.add(DecoratedSignature.decode(stream))
            }
            return TransactionV1Envelope(tx, signatures)
        }
    }
}
