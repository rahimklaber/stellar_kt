// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * BumpSequenceResult's original definition in the XDR file is:
 * ```
 * union BumpSequenceResult switch (BumpSequenceResultCode code)
{
case BUMP_SEQUENCE_SUCCESS:
void;
case BUMP_SEQUENCE_BAD_SEQ:
void;
};
 * ```
 */
sealed class BumpSequenceResult(val type: BumpSequenceResultCode) : XdrElement {
    data object BumpSequenceSuccess : BumpSequenceResult(BumpSequenceResultCode.BUMP_SEQUENCE_SUCCESS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object BumpSequenceBadSeq : BumpSequenceResult(BumpSequenceResultCode.BUMP_SEQUENCE_BAD_SEQ) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    companion object : XdrElementDecoder<BumpSequenceResult> {
        override fun decode(stream: XdrInputStream): BumpSequenceResult {
            return when (val type = BumpSequenceResultCode.decode(stream)) {
                BumpSequenceResultCode.BUMP_SEQUENCE_SUCCESS -> BumpSequenceSuccess
                BumpSequenceResultCode.BUMP_SEQUENCE_BAD_SEQ -> BumpSequenceBadSeq
                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
