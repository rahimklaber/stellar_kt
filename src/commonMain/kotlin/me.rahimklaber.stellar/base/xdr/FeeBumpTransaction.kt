package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct FeeBumpTransaction
//{
//    MuxedAccount feeSource;
//    int64 fee;
//    union switch (EnvelopeType type)
//    {
//    case ENVELOPE_TYPE_TX:
//        TransactionV1Envelope v1;
//    }
//    innerTx;
//    union switch (int v)
//    {
//    case 0:
//        void;
//    }
//    ext;
//};
///////////////////////////////////////////////////////////////////////////
sealed class FeeBumpTransaction(
    val type: EnvelopeType
): XdrElement {
    abstract val feeSource: MuxedAccount
    abstract val fee: Long
    protected abstract val discriminant: Int
    override fun encode(stream: XdrStream) {
        feeSource.encode(stream)
        stream.writeLong(fee)
        type.encode(stream)
    }
    data class TxV1(
        override val feeSource: MuxedAccount,
        override val fee: Long,
        override val discriminant: Int,
        val v1: TransactionV1Envelope
    ) : FeeBumpTransaction(EnvelopeType.ENVELOPE_TYPE_TX){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            v1.encode(stream)
            stream.writeInt(discriminant)
        }
    }

    companion object: XdrElementDecoder<FeeBumpTransaction> {
        override fun decode(stream: XdrStream): FeeBumpTransaction {
            val feeSource = MuxedAccount.decode(stream)
            val fee = stream.readLong()
            return when(val type = EnvelopeType.decode(stream)){
                EnvelopeType.ENVELOPE_TYPE_TX ->{
                    val v1 = TransactionV1Envelope.decode(stream)
                    val discriminant = stream.readInt()
                    TxV1(feeSource, fee, discriminant, v1)
                }
                else -> throw IllegalArgumentException("Could not decode FeeBumpTransaction for type: $type")
            }
        }
    }
}
