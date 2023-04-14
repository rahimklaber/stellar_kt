package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Creates a claimable balance entry
//    Threshold: med
//    Result: CreateClaimableBalanceResult
//*/
//struct CreateClaimableBalanceOp
//{
//    Asset asset;
//    int64 amount;
//    Claimant claimants<10>;
//};
///////////////////////////////////////////////////////////////////////////
data class CreateClaimableBalanceOp(
    val asset: Asset,
    val amount: Long,
    val claimants: List<Claimant>,
): XdrElement  {
    override fun encode(stream: XdrStream) {
        asset.encode(stream)
        stream.writeLong(amount)
        stream.writeInt(claimants.size)
        claimants.forEach {
            it.encode(stream)
        }
    }

    companion object: XdrElementDecoder<CreateClaimableBalanceOp> {
        override fun decode(stream: XdrStream): CreateClaimableBalanceOp {
            val asset = Asset.decode(stream)
            val amount = stream.readLong()
            val claimantsSize = stream.readInt()
            val claimants = mutableListOf<Claimant>()
            for(i in 0 until claimantsSize){
                claimants.add(Claimant.decode(stream))
            }
            return CreateClaimableBalanceOp(asset, amount, claimants)
        }
    }
}