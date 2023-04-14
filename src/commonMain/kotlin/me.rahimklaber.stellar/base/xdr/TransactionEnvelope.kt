package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* A TransactionEnvelope wraps a transaction with signatures. */
//union TransactionEnvelope switch (EnvelopeType type)
//{
//case ENVELOPE_TYPE_TX_V0:
//    TransactionV0Envelope v0;
//case ENVELOPE_TYPE_TX:
//    TransactionV1Envelope v1;
//case ENVELOPE_TYPE_TX_FEE_BUMP:
//    FeeBumpTransactionEnvelope feeBump;
//};
///////////////////////////////////////////////////////////////////////////
sealed class TransactionEnvelope(val type: EnvelopeType) : XdrElement {

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    data class TxV0(val v0: TransactionV0Envelope) : TransactionEnvelope(EnvelopeType.ENVELOPE_TYPE_TX_V0){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            v0.encode(stream)
        }
    }
    data class TxV1(val v1: TransactionV1Envelope) : TransactionEnvelope(EnvelopeType.ENVELOPE_TYPE_TX){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            v1.encode(stream)
        }
    }
    data class FeeBump(val feeBump: FeeBumpTransactionEnvelope): TransactionEnvelope(EnvelopeType.ENVELOPE_TYPE_TX_FEE_BUMP){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            feeBump.encode(stream)
        }
    }

    companion object: XdrElementDecoder<TransactionEnvelope> {
        override fun decode(stream: XdrStream): TransactionEnvelope {
            return when(val type = EnvelopeType.decode(stream)){
                EnvelopeType.ENVELOPE_TYPE_TX_V0 -> TxV0(TransactionV0Envelope.decode(stream))
                EnvelopeType.ENVELOPE_TYPE_TX -> TxV1(TransactionV1Envelope.decode(stream))
                EnvelopeType.ENVELOPE_TYPE_TX_FEE_BUMP -> FeeBump(FeeBumpTransactionEnvelope.decode(stream))
                else -> throw IllegalArgumentException("Could not decode TransactionEnvelope for type: $type")
            }
        }
    }
}
