package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* PathPaymentStrictReceive
//send an amount to a destination account through a path.
//(up to sendMax, sendAsset)
//(X0, Path[0]) .. (Xn, Path[n])
//(destAmount, destAsset)
//Threshold: med
//Result: PathPaymentStrictReceiveResult
//*/
//struct PathPaymentStrictReceiveOp
//{
//    Asset sendAsset; // asset we pay with
//    int64 sendMax;   // the maximum amount of sendAsset to
//                     // send (excluding fees).
//                     // The operation will fail if can't be met
//
//    MuxedAccount destination; // recipient of the payment
//    Asset destAsset;          // what they end up with
//    int64 destAmount;         // amount they end up with
//
//    Asset path<5>; // additional hops it must go through to get there
//};
///////////////////////////////////////////////////////////////////////////
data class PathPaymentStrictReceiveOp(
    val sendAsset: Asset,
    val sendMax: Long,
    val destination: MuxedAccount,
    val destAsset: Asset,
    val destAmount: Long,
    val path: List<Asset>,
): XdrElement {

    init {
        require(path.size <= 5){
            "path has a max length of 5"
        }
    }
    override fun encode(stream: XdrStream) {
        sendAsset.encode(stream)
        stream.writeLong(sendMax)
        destination.encode(stream)
        destAsset.encode(stream)
        stream.writeLong(destAmount)
        stream.writeInt(path.size)
        path.forEach {
            it.encode(stream)
        }
    }

    companion object: XdrElementDecoder<PathPaymentStrictReceiveOp> {
        override fun decode(stream: XdrStream): PathPaymentStrictReceiveOp {
            val sendAsset = Asset.decode(stream)
            val sendMax = stream.readLong()
            val destination = MuxedAccount.decode(stream)
            val destAsset = Asset.decode(stream)
            val destAmount = stream.readLong()
            val pathSize = stream.readInt()
            val path = mutableListOf<Asset>()
            for (i in 0 until pathSize){
                path.add(Asset.decode(stream))
            }
            return PathPaymentStrictReceiveOp(sendAsset, sendMax, destination, destAsset, destAmount, path)
        }
    }
}
