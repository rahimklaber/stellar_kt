package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.TokenAmount
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.LiquidityPoolDepositOp
import me.rahimklaber.stellar.base.xdr.PoolID
import me.rahimklaber.stellar.base.xdr.Price
import me.rahimklaber.stellar.base.xdr.fromHex

data class LiquidityPoolDeposit(
    override val sourceAccount: String? = null,
    val poolID: String, //hex value
    val maxAmountA: TokenAmount,
    val maxAmountB: TokenAmount,
    val minPrice: Price,
    val maxPrice: Price,
): Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        val source = sourceAccount?.let {
            StrKey.encodeToMuxedAccountXDR(it)
        }
        return me.rahimklaber.stellar.base.xdr.Operation.LiquidityPoolDeposit(
            sourceAccount = source,
            LiquidityPoolDepositOp(
                liquidityPoolID = PoolID.fromHex(poolID),
                maxAmountA = maxAmountA.value,
                maxAmountB = maxAmountB.value,
                minPrice = minPrice,
                maxPrice = maxPrice
            )
        )
    }
}
