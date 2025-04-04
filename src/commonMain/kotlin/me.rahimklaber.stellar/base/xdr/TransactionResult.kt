// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * TransactionResult's original definition in the XDR file is:
 * ```
 * struct TransactionResult
{
int64 feeCharged; // actual fee charged for the transaction

union switch (TransactionResultCode code)
{
case txFEE_BUMP_INNER_SUCCESS:
case txFEE_BUMP_INNER_FAILED:
InnerTransactionResultPair innerResultPair;
case txSUCCESS:
case txFAILED:
OperationResult results<>;
case txTOO_EARLY:
case txTOO_LATE:
case txMISSING_OPERATION:
case txBAD_SEQ:
case txBAD_AUTH:
case txINSUFFICIENT_BALANCE:
case txNO_ACCOUNT:
case txINSUFFICIENT_FEE:
case txBAD_AUTH_EXTRA:
case txINTERNAL_ERROR:
case txNOT_SUPPORTED:
// case txFEE_BUMP_INNER_FAILED: handled above
case txBAD_SPONSORSHIP:
case txBAD_MIN_SEQ_AGE_OR_GAP:
case txMALFORMED:
case txSOROBAN_INVALID:
void;
}
result;

// reserved for future use
union switch (int v)
{
case 0:
void;
}
ext;
};
 * ```
 */
data class TransactionResult(
    val feeCharged: Int64,
    val result: TransactionResultResult,
    val ext: TransactionResultExt,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        feeCharged.encode(stream)
        result.encode(stream)
        ext.encode(stream)
    }

    companion object : XdrElementDecoder<TransactionResult> {
        override fun decode(stream: XdrInputStream): TransactionResult {
            val feeCharged = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val result = TransactionResultResult.decode(stream)
            val ext = TransactionResultExt.decode(stream)
            return TransactionResult(
                feeCharged,
                result,
                ext,
            )
        }
    }

    /**
     * TransactionResultResult's original definition in the XDR file is:
     * ```
     * union switch (TransactionResultCode code)
    {
    case txFEE_BUMP_INNER_SUCCESS:
    case txFEE_BUMP_INNER_FAILED:
    InnerTransactionResultPair innerResultPair;
    case txSUCCESS:
    case txFAILED:
    OperationResult results<>;
    case txTOO_EARLY:
    case txTOO_LATE:
    case txMISSING_OPERATION:
    case txBAD_SEQ:
    case txBAD_AUTH:
    case txINSUFFICIENT_BALANCE:
    case txNO_ACCOUNT:
    case txINSUFFICIENT_FEE:
    case txBAD_AUTH_EXTRA:
    case txINTERNAL_ERROR:
    case txNOT_SUPPORTED:
    // case txFEE_BUMP_INNER_FAILED: handled above
    case txBAD_SPONSORSHIP:
    case txBAD_MIN_SEQ_AGE_OR_GAP:
    case txMALFORMED:
    case txSOROBAN_INVALID:
    void;
    }
     * ```
     */
    sealed class TransactionResultResult(val type: TransactionResultCode) : XdrElement {
        data class TxfeeBumpInnerSuccess(
            val innerResultPair: InnerTransactionResultPair,
        ) : TransactionResultResult(TransactionResultCode.txFEE_BUMP_INNER_SUCCESS) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                innerResultPair.encode(stream)
            }
        }

        data class TxfeeBumpInnerFailed(
            val innerResultPair: InnerTransactionResultPair,
        ) : TransactionResultResult(TransactionResultCode.txFEE_BUMP_INNER_FAILED) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                innerResultPair.encode(stream)
            }
        }

        data class Txsuccess(
            val results: List<OperationResult>,
        ) : TransactionResultResult(TransactionResultCode.txSUCCESS) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                val resultsSize = results.size
                stream.writeInt(resultsSize)
                results.encodeXdrElements(stream)
            }
        }

        data class Txfailed(
            val results: List<OperationResult>,
        ) : TransactionResultResult(TransactionResultCode.txFAILED) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                val resultsSize = results.size
                stream.writeInt(resultsSize)
                results.encodeXdrElements(stream)
            }
        }

        data object TxtooEarly : TransactionResultResult(TransactionResultCode.txTOO_EARLY) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxtooLate : TransactionResultResult(TransactionResultCode.txTOO_LATE) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxmissingOperation : TransactionResultResult(TransactionResultCode.txMISSING_OPERATION) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxbadSeq : TransactionResultResult(TransactionResultCode.txBAD_SEQ) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxbadAuth : TransactionResultResult(TransactionResultCode.txBAD_AUTH) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxinsufficientBalance : TransactionResultResult(TransactionResultCode.txINSUFFICIENT_BALANCE) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxnoAccount : TransactionResultResult(TransactionResultCode.txNO_ACCOUNT) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxinsufficientFee : TransactionResultResult(TransactionResultCode.txINSUFFICIENT_FEE) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxbadAuthExtra : TransactionResultResult(TransactionResultCode.txBAD_AUTH_EXTRA) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxinternalError : TransactionResultResult(TransactionResultCode.txINTERNAL_ERROR) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxnotSupported : TransactionResultResult(TransactionResultCode.txNOT_SUPPORTED) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxbadSponsorship : TransactionResultResult(TransactionResultCode.txBAD_SPONSORSHIP) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxbadMinSeqAgeOrGap : TransactionResultResult(TransactionResultCode.txBAD_MIN_SEQ_AGE_OR_GAP) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object Txmalformed : TransactionResultResult(TransactionResultCode.txMALFORMED) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        data object TxsorobanInvalid : TransactionResultResult(TransactionResultCode.txSOROBAN_INVALID) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        companion object : XdrElementDecoder<TransactionResultResult> {
            override fun decode(stream: XdrInputStream): TransactionResultResult {
                return when (val type = TransactionResultCode.decode(stream)) {
                    TransactionResultCode.txFEE_BUMP_INNER_SUCCESS -> {
                        val innerResultPair = InnerTransactionResultPair.decode(stream)
                        TxfeeBumpInnerSuccess(innerResultPair)
                    }

                    TransactionResultCode.txFEE_BUMP_INNER_FAILED -> {
                        val innerResultPair = InnerTransactionResultPair.decode(stream)
                        TxfeeBumpInnerFailed(innerResultPair)
                    }

                    TransactionResultCode.txSUCCESS -> {
                        val resultsSize = stream.readInt()
                        val results: List<OperationResult> = decodeXdrElementsList(resultsSize, stream, OperationResult.decoder())
                        Txsuccess(results)
                    }

                    TransactionResultCode.txFAILED -> {
                        val resultsSize = stream.readInt()
                        val results: List<OperationResult> = decodeXdrElementsList(resultsSize, stream, OperationResult.decoder())
                        Txfailed(results)
                    }

                    TransactionResultCode.txTOO_EARLY -> TxtooEarly
                    TransactionResultCode.txTOO_LATE -> TxtooLate
                    TransactionResultCode.txMISSING_OPERATION -> TxmissingOperation
                    TransactionResultCode.txBAD_SEQ -> TxbadSeq
                    TransactionResultCode.txBAD_AUTH -> TxbadAuth
                    TransactionResultCode.txINSUFFICIENT_BALANCE -> TxinsufficientBalance
                    TransactionResultCode.txNO_ACCOUNT -> TxnoAccount
                    TransactionResultCode.txINSUFFICIENT_FEE -> TxinsufficientFee
                    TransactionResultCode.txBAD_AUTH_EXTRA -> TxbadAuthExtra
                    TransactionResultCode.txINTERNAL_ERROR -> TxinternalError
                    TransactionResultCode.txNOT_SUPPORTED -> TxnotSupported
                    TransactionResultCode.txBAD_SPONSORSHIP -> TxbadSponsorship
                    TransactionResultCode.txBAD_MIN_SEQ_AGE_OR_GAP -> TxbadMinSeqAgeOrGap
                    TransactionResultCode.txMALFORMED -> Txmalformed
                    TransactionResultCode.txSOROBAN_INVALID -> TxsorobanInvalid
                    else -> throw IllegalArgumentException("unknown type: $type")
                }
            }
        }
    }

    /**
     * TransactionResultExt's original definition in the XDR file is:
     * ```
     * union switch (int v)
    {
    case 0:
    void;
    }
     * ```
     */
    sealed class TransactionResultExt(val type: Int) : XdrElement {
        data object TransactionResultExtV0 : TransactionResultExt(0) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        companion object : XdrElementDecoder<TransactionResultExt> {
            override fun decode(stream: XdrInputStream): TransactionResultExt {
                return when (val type = Int.decode(stream)) {
                    0 -> TransactionResultExtV0
                    else -> throw IllegalArgumentException("unknown type: $type")
                }
            }
        }
    }
}
