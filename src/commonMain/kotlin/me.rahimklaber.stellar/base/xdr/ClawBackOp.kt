package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Claws back an amount of an asset from an account
//    Threshold: med
//    Result: ClawbackResult
//*/
//struct ClawbackOp
//{
//    Asset asset;
//    MuxedAccount from;
//    int64 amount;
//};
///////////////////////////////////////////////////////////////////////////
data class ClawBackOp(
    val asset: Asset,
    val from: MuxedAccount,
    val amount: Long,
): XdrElement {
    override fun encode(stream: XdrStream) {
        asset.encode(stream)
        from.encode(stream)
        stream.writeLong(amount)
    }

    companion object: XdrElementDecoder<ClawBackOp> {
        override fun decode(stream: XdrStream): ClawBackOp {
            val asset = Asset.decode(stream)
            val from = MuxedAccount.decode(stream)
            val amount = stream.readLong()
            return ClawBackOp(asset, from, amount)
        }
    }
}