package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Bump Sequence
//    increases the sequence to a given level
//    Threshold: low
//    Result: BumpSequenceResult
//*/
//struct BumpSequenceOp
//{
//    SequenceNumber bumpTo;
//};
///////////////////////////////////////////////////////////////////////////
data class BumpSequenceOp(
    val bumpTo: SequenceNumber
): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeLong(bumpTo)
    }

    companion object: XdrElementDecoder<BumpSequenceOp> {
        override fun decode(stream: XdrStream): BumpSequenceOp {
            return BumpSequenceOp(stream.readLong())
        }
    }
}