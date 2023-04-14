package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* PathPaymentStrictSend
//send an amount to a destination account through a path.
//(sendMax, sendAsset)
//(X0, Path[0]) .. (Xn, Path[n])
//(at least destAmount, destAsset)
//Threshold: med
//Result: PathPaymentStrictSendResult
//*/
//struct PathPaymentStrictSendOp
//{
//    Asset sendAsset;  // asset we pay with
//    int64 sendAmount; // amount of sendAsset to send (excluding fees)
//
//    MuxedAccount destination; // recipient of the payment
//    Asset destAsset;          // what they end up with
//    int64 destMin;            // the minimum amount of dest asset to
//                              // be received
//                              // The operation will fail if it can't be met
//
//    Asset path<5>; // additional hops it must go through to get there
//};
///////////////////////////////////////////////////////////////////////////
data class PathPaymentStrictSendOp(
    val sendAsset: Asset,
    val sendAmount: Long,
    val destination: MuxedAccount,
    val destAsset: Asset,
    val destMin: Long,
    val path: List<Asset>,
): XdrElement{
    override fun encode(stream: XdrStream) {
        sendAsset.encode(stream)
        stream.writeLong(sendAmount)
        destination.encode(stream)
        destAsset.encode(stream)
        stream.writeLong(destMin)
        stream.writeInt(path.size)
        path.forEach {
            it.encode(stream)
        }
    }

    companion object: XdrElementDecoder<PathPaymentStrictSendOp>{
        override fun decode(stream: XdrStream): PathPaymentStrictSendOp {
            val sendAsset = Asset.decode(stream)
            val sendAmount = stream.readLong()
            val destination = MuxedAccount.decode(stream)
            val destAsset = Asset.decode(stream)
            val destMin = stream.readLong()
            val pathSize = stream.readInt()
            val path = mutableListOf<Asset>()
            for(i in 0 until pathSize){
                path.add(Asset.decode(stream))
            }
            return PathPaymentStrictSendOp(sendAsset, sendAmount, destination, destAsset, destMin, path)
        }

    }

}
