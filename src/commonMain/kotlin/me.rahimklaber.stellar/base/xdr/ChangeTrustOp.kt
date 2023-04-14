package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct ChangeTrustOp
//{
//    ChangeTrustAsset line;
//
//    // if limit is set to 0, deletes the trust line
//    int64 limit;
//};
///////////////////////////////////////////////////////////////////////////
data class ChangeTrustOp(
    val line: ChangeTrustAsset,
    val limit: Long,
): XdrElement {
    override fun encode(stream: XdrStream) {
        line.encode(stream)
        stream.writeLong(limit)
    }

    companion object: XdrElementDecoder<ChangeTrustOp> {
        override fun decode(stream: XdrStream): ChangeTrustOp {
            val line = ChangeTrustAsset.decode(stream)
            val limit = stream.readLong()
            return ChangeTrustOp(line, limit)
        }
    }
}