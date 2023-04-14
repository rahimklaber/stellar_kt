package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct LiquidityPoolConstantProductParameters
//{
//    Asset assetA; // assetA < assetB
//    Asset assetB;
//    int32 fee; // Fee is in basis points, so the actual rate is (fee/100)%
//};
///////////////////////////////////////////////////////////////////////////
data class LiquidityPoolConstantProductParameters(
    val assetA: Asset,
    val assetB: Asset,
    val fee: Int,
): XdrElement{
    override fun encode(stream: XdrStream) {
        assetA.encode(stream)
        assetB.encode(stream)
        stream.writeInt(fee)
    }

    companion object: XdrElementDecoder<LiquidityPoolConstantProductParameters>{
        override fun decode(stream: XdrStream): LiquidityPoolConstantProductParameters {
            val assetA = Asset.decode(stream)
            val assetB = Asset.decode(stream)
            val fee = stream.readInt()
            return LiquidityPoolConstantProductParameters(assetA, assetB, fee)
        }

    }
}