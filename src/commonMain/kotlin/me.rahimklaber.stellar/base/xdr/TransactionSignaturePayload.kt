package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct TransactionSignaturePayload
//{
//    Hash networkId;
//    union switch (EnvelopeType type)
//    {
//    // Backwards Compatibility: Use ENVELOPE_TYPE_TX to sign ENVELOPE_TYPE_TX_V0
//    case ENVELOPE_TYPE_TX:
//        Transaction tx;
//    case ENVELOPE_TYPE_TX_FEE_BUMP:
//        FeeBumpTransaction feeBump;
//    }
//    taggedTransaction;
//};
///////////////////////////////////////////////////////////////////////////
sealed class TransactionSignaturePayload(
    val type: EnvelopeType
): XdrElement{
    abstract val networkId: Hash

    override fun encode(stream: XdrStream) {
        networkId.encode(stream)
        type.encode(stream)
    }

    data class Tx(override val networkId: Hash, val tx: Transaction) : TransactionSignaturePayload(EnvelopeType.ENVELOPE_TYPE_TX){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            tx.encode(stream)
        }
    }

    data class FeeBump(override val networkId: Hash, val feeBump: FeeBumpTransaction) : TransactionSignaturePayload(EnvelopeType.ENVELOPE_TYPE_TX_FEE_BUMP){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            feeBump.encode(stream)
        }
    }

    companion object: XdrElementDecoder<TransactionSignaturePayload> {
        override fun decode(stream: XdrStream): TransactionSignaturePayload {
            val networkId = Hash.decode(stream)
            return when(val type = EnvelopeType.decode(stream)){
                EnvelopeType.ENVELOPE_TYPE_TX -> Tx(networkId,Transaction.decode(stream))
                EnvelopeType.ENVELOPE_TYPE_TX_FEE_BUMP -> FeeBump(networkId,FeeBumpTransaction.decode(stream))
                else -> throw IllegalArgumentException("Could not decode TransactionSignaturePayload for type: $type")
            }
        }
    }
}
