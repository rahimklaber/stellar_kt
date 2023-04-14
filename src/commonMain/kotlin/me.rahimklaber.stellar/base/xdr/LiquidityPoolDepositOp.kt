package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Deposit assets into a liquidity pool
//    Threshold: med
//    Result: LiquidityPoolDepositResult
//*/
//struct LiquidityPoolDepositOp
//{
//    PoolID liquidityPoolID;
//    int64 maxAmountA; // maximum amount of first asset to deposit
//    int64 maxAmountB; // maximum amount of second asset to deposit
//    Price minPrice;   // minimum depositA/depositB
//    Price maxPrice;   // maximum depositA/depositB
//};
///////////////////////////////////////////////////////////////////////////
data class LiquidityPoolDepositOp(
    val liquidityPoolID: PoolID,
    val maxAmountA: Long,
    val maxAmountB: Long,
    val minPrice: Price,
    val maxPrice: Price
): XdrElement{
    override fun encode(stream: XdrStream) {
        liquidityPoolID.encode(stream)
        stream.writeLong(maxAmountA)
        stream.writeLong(maxAmountB)
        minPrice.encode(stream)
        maxPrice.encode(stream)
    }

    companion object: XdrElementDecoder<LiquidityPoolDepositOp> {
        override fun decode(stream: XdrStream): LiquidityPoolDepositOp {
            val liquidityPoolID = PoolID.decode(stream)
            val maxAmountA = stream.readLong()
            val maxAmountB = stream.readLong()
            val minPrice = Price.decode(stream)
            val maxPrice = Price.decode(stream)
            return LiquidityPoolDepositOp(liquidityPoolID, maxAmountA, maxAmountB, minPrice, maxPrice)
        }
    }
}