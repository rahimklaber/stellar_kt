package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Withdraw assets from a liquidity pool
//    Threshold: med
//    Result: LiquidityPoolWithdrawResult
//*/
//struct LiquidityPoolWithdrawOp
//{
//    PoolID liquidityPoolID;
//    int64 amount;     // amount of pool shares to withdraw
//    int64 minAmountA; // minimum amount of first asset to withdraw
//    int64 minAmountB; // minimum amount of second asset to withdraw
//};
///////////////////////////////////////////////////////////////////////////
data class LiquidityPoolWithdrawOp(
    val liquidityPoolID: PoolID,
    val amount: Long,
    val minAmountA: Long,
    val minAmountB: Long,
): XdrElement {
    override fun encode(stream: XdrStream) {
        liquidityPoolID.encode(stream)
        stream.writeLong(amount)
        stream.writeLong(minAmountA)
        stream.writeLong(minAmountB)
    }

    companion object: XdrElementDecoder<LiquidityPoolWithdrawOp> {
        override fun decode(stream: XdrStream): LiquidityPoolWithdrawOp {
            val liquidityPoolID = PoolID.decode(stream)
            val amount = stream.readLong()
            val minAmountA = stream.readLong()
            val minAmountB = stream.readLong()
            return LiquidityPoolWithdrawOp(liquidityPoolID, amount, minAmountA, minAmountB)
        }
    }
}
