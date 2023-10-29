package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* a transaction is a container for a set of operations
//    - is executed by an account
//    - fees are collected from the account
//    - operations are executed in order as one ACID transaction
//          either all operations are applied or none are
//          if any returns a failing code
//*/
//struct Transaction
//{
//    // account used to run the transaction
//    MuxedAccount sourceAccount;
//
//    // the fee the sourceAccount will pay
//    uint32 fee;
//
//    // sequence number to consume in the account
//    SequenceNumber seqNum;
//
//    // validity conditions
//    TransactionPreconditions cond;
//
//    Memo memo;
//
//    Operation operations<MAX_OPS_PER_TX>;
//
//    // reserved for future use
//    union switch (int v)
//    {
//    case 0:
//        void;
//    }
//    ext;
//};
///////////////////////////////////////////////////////////////////////////
data class Transaction(
    val sourceAccount: MuxedAccount,
    val fee: UInt,
    val seqNum: SequenceNumber,
    val cond: Preconditions,
    val memo: Memo,
    val operations: List<Operation>,
    private val discriminant: Int = 0
) : XdrElement {
    override fun encode(stream: XdrStream) {
        sourceAccount.encode(stream)
        stream.writeInt(fee.toInt())
        stream.writeLong(seqNum)
        cond.encode(stream)
        memo.encode(stream)
        stream.writeInt(operations.size)
        operations.forEach {
            it.encode(stream)
        }
        stream.writeInt(discriminant)
    }

    companion object: XdrElementDecoder<Transaction> {
        override fun decode(stream: XdrStream): Transaction {
            val sourceAccount = MuxedAccount.decode(stream)
            val fee = stream.readInt().toUInt()
            val seqNum = stream.readLong()
            val cond = Preconditions.decode(stream)
            val memo = Memo.decode(stream)
            val size = stream.readInt()
            val operations = mutableListOf<Operation>()
            for (i in 0 until size){
                operations.add(Operation.decode(stream))
            }
            val discriminant = stream.readInt()
            return Transaction(sourceAccount, fee, seqNum, cond, memo, operations, discriminant)
        }
    }
}
