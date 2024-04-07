package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct TransactionResult
//{
//    int64 feeCharged; // actual fee charged for the transaction
//
//    union switch (TransactionResultCode code)
//    {
//    case txFEE_BUMP_INNER_SUCCESS:
//    case txFEE_BUMP_INNER_FAILED:
//        InnerTransactionResultPair innerResultPair;
//    case txSUCCESS:
//    case txFAILED:
//        OperationResult results<>;
//    case txTOO_EARLY:
//    case txTOO_LATE:
//    case txMISSING_OPERATION:
//    case txBAD_SEQ:
//    case txBAD_AUTH:
//    case txINSUFFICIENT_BALANCE:
//    case txNO_ACCOUNT:
//    case txINSUFFICIENT_FEE:
//    case txBAD_AUTH_EXTRA:
//    case txINTERNAL_ERROR:
//    case txNOT_SUPPORTED:
//    // case txFEE_BUMP_INNER_FAILED: handled above
//    case txBAD_SPONSORSHIP:
//    case txBAD_MIN_SEQ_AGE_OR_GAP:
//    case txMALFORMED:
//    case txSOROBAN_INVALID:
//        void;
//    }
//    result;
//
//    // reserved for future use
//    union switch (int v)
//    {
//    case 0:
//        void;
//    }
//    ext;
//};
//}
///////////////////////////////////////////////////////////////////////////
sealed class TransactionResult(
    val code: TransactionResultCode,
): XdrElement {
    abstract val feeCharged: Long

    val discriminant = 0

    override fun encode(stream: XdrStream) {
        stream.writeLong(feeCharged)
        code.encode(stream)
    }
    data class Success(val results: List<OperationResult>, override val feeCharged: Long,): TransactionResult(TransactionResultCode.txSUCCESS){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            results.encode(stream)

            stream.writeInt(discriminant)
        }
    }

    companion object: XdrElementDecoder<TransactionResult> {
        override fun decode(stream: XdrStream): TransactionResult {
            val feeCharged = stream.readLong()
            val code = TransactionResultCode.decode(stream)

            return when(code){
                TransactionResultCode.txSUCCESS -> Success(decodeXdrElementList(stream){OperationResult.decode(stream)}, feeCharged)
                else -> TODO()
            }
        }
    }

}
