package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct PreconditionsV2
//{
//    TimeBounds* timeBounds;
//
//    // Transaction only valid for ledger numbers n such that
//    // minLedger <= n < maxLedger (if maxLedger == 0, then
//    // only minLedger is checked)
//    LedgerBounds* ledgerBounds;
//
//    // If NULL, only valid when sourceAccount's sequence number
//    // is seqNum - 1.  Otherwise, valid when sourceAccount's
//    // sequence number n satisfies minSeqNum <= n < tx.seqNum.
//    // Note that after execution the account's sequence number
//    // is always raised to tx.seqNum, and a transaction is not
//    // valid if tx.seqNum is too high to ensure replay protection.
//    SequenceNumber* minSeqNum;
//
//    // For the transaction to be valid, the current ledger time must
//    // be at least minSeqAge greater than sourceAccount's seqTime.
//    Duration minSeqAge;
//
//    // For the transaction to be valid, the current ledger number
//    // must be at least minSeqLedgerGap greater than sourceAccount's
//    // seqLedger.
//    uint32 minSeqLedgerGap;
//
//    // For the transaction to be valid, there must be a signature
//    // corresponding to every Signer in this array, even if the
//    // signature is not otherwise required by the sourceAccount or
//    // operations.
//    SignerKey extraSigners<2>;
//};
///////////////////////////////////////////////////////////////////////////
data class PreconditionsV2(
    val timeBounds: TimeBounds?,
    val ledgerBounds: LedgerBounds?,
    val minSeqNum: SequenceNumber?,
    val minSeqAge: Duration,
    val minSeqLedgerGap: UInt,
    val extraSigners: List<SignerKey>
) : XdrElement {

    init {
        require(extraSigners.size <= 2){
            "extraSigners has a max size of 2. Size provided is ${extraSigners.size}"
        }
    }
    override fun encode(stream: XdrStream) {
        timeBounds.encodeNullable(stream)
        ledgerBounds.encodeNullable(stream)
        stream.writeLongNullable(minSeqNum)
        stream.writeLong(minSeqAge.toLong())
        stream.writeIntNullable(minSeqLedgerGap.toInt())
        stream.writeInt(extraSigners.size)
        extraSigners.forEach{
            it.encode(stream)
        }
    }

    companion object: XdrElementDecoder<PreconditionsV2> {
        override fun decode(stream: XdrStream): PreconditionsV2 {
            val timeBounds = TimeBounds.decodeNullable(stream)
            val ledgerBounds = LedgerBounds.decodeNullable(stream)
            val minSeqNum = stream.readLongNullable()
            val minSeqAge = stream.readLong().toULong()
            val minSeqLedgerGap = stream.readInt().toUInt()
            val extraSigners = mutableListOf<SignerKey>()
            val extraSignersLen = stream.readInt()
            for (i in 0 until extraSignersLen){
                extraSigners.add(SignerKey.decode(stream))
            }
            return PreconditionsV2(timeBounds, ledgerBounds, minSeqNum, minSeqAge, minSeqLedgerGap, extraSigners)
        }
    }
}
