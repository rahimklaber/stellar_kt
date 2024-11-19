package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct LiquidityPoolEntry
//{
//    PoolID liquidityPoolID;
//
//    union switch (LiquidityPoolType type)
//    {
//    case LIQUIDITY_POOL_CONSTANT_PRODUCT:
//        struct
//        {
//            LiquidityPoolConstantProductParameters params;
//
//            int64 reserveA;        // amount of A in the pool
//            int64 reserveB;        // amount of B in the pool
//            int64 totalPoolShares; // total number of pool shares issued
//            int64 poolSharesTrustLineCount; // number of trust lines for the
//                                            // associated pool shares
//        } constantProduct;
//    }
//    body;
//};
///////////////////////////////////////////////////////////////////////////
sealed class LiquidityPoolEntry(
    val type: LiquidityPoolType,
): XdrElement{
    abstract val liquidityPoolID: PoolID
    data class LiquidityPoolEntryConstantProduct(
        override val liquidityPoolID: PoolID,
        val params: LiquidityPoolConstantProductParameters,
        val reserveA: Long,
        val reserveB: Long,
        val totalPoolShares: Long,
        val poolSharesTrustLineCount: Long
    ) : LiquidityPoolEntry(LiquidityPoolType.LIQUIDITY_POOL_CONSTANT_PRODUCT){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            params.encode(stream)
            stream.writeLong(reserveA)
            stream.writeLong(reserveB)
            stream.writeLong(totalPoolShares)
            stream.writeLong(poolSharesTrustLineCount)
        }
    }

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    companion object: XdrElementDecoder<LiquidityPoolEntry>{
        override fun decode(stream: XdrStream): LiquidityPoolEntry {
            val liquidityPoolID = PoolID.decode(stream)
            return when(val type = LiquidityPoolType.decode(stream)){
                LiquidityPoolType.LIQUIDITY_POOL_CONSTANT_PRODUCT -> {
                    val params = LiquidityPoolConstantProductParameters.decode(stream)
                    val reserveA = stream.readLong()
                    val reserveB = stream.readLong()
                    val totalPoolShares = stream.readLong()
                    val poolSharesTrustLineCount = stream.readLong()
                    LiquidityPoolEntryConstantProduct(liquidityPoolID, params, reserveA, reserveB, totalPoolShares, poolSharesTrustLineCount)
                }
                else -> throw IllegalArgumentException("Could not decode LiquidityPoolEntry for type: $type")
            }
        }

    }
}
