package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// // TransactionV0 is a transaction with the AccountID discriminant stripped off,
//// leaving a raw ed25519 public key to identify the source account. This is used
//// for backwards compatibility starting from the protocol 12/13 boundary. If an
//// "old-style" TransactionEnvelope containing a Transaction is parsed with this
//// XDR definition, it will be parsed as a "new-style" TransactionEnvelope
//// containing a TransactionV0.
//struct TransactionV0
//{
//    uint256 sourceAccountEd25519;
//    uint32 fee;
//    SequenceNumber seqNum;
//    TimeBounds* timeBounds;
//    Memo memo;
//    Operation operations<MAX_OPS_PER_TX>;
//    union switch (int v)
//    {
//    case 0:
//        void;
//    }
//    ext;
//};
///////////////////////////////////////////////////////////////////////////
data class TransactionV0(
    val sourceAccountEd25519: Uint256,
    val fee: UInt,
    val seqNum: SequenceNumber,
    val timeBounds: TimeBounds?,
    val memo: Memo,
    val operations: List<Operation>,
    private val discriminant: Int, // todo make this private?
): XdrElement{
    init {
        require(operations.size <= MAX_OPS_PER_TX){
            "A transaction can only contain $MAX_OPS_PER_TX. Current amount is ${operations.size}"
        }
    }

    override fun encode(stream: XdrStream) {
        sourceAccountEd25519.encode(stream)
        stream.writeInt(fee.toInt())
        stream.writeLong(seqNum)
        timeBounds.encodeNullable(stream)
        memo.encode(stream)
        stream.writeInt(operations.size)
        operations.forEach { it.encode(stream) }
        stream.writeInt(discriminant)
    }

    companion object: XdrElementDecoder<TransactionV0> {
        override fun decode(stream: XdrStream): TransactionV0 {
            val sourceAccountEd25519 = Uint256.decode(stream)
            val fee = stream.readInt().toUInt()
            val seqNum = stream.readLong()
            val timeBounds = TimeBounds.decodeNullable(stream)
            val memo = Memo.decode(stream)
            val size = stream.readInt()
            val operations = mutableListOf<Operation>()
            for(i in 0 until size){
                operations.add(Operation.decode(stream))
            }
            val discriminant = stream.readInt()
            return TransactionV0(sourceAccountEd25519, fee, seqNum, timeBounds, memo, operations, discriminant)
        }
    }
}
