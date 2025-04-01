package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.TokenAmount
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.LiquidityPoolWithdrawOp
import me.rahimklaber.stellar.base.xdr.PoolID
import me.rahimklaber.stellar.base.xdr.fromHex

data class LiquidityPoolWithdraw(
    override val sourceAccount: String? = null,
    val poolID: String, // hex value
    val amount: TokenAmount,
    val minAmountA: TokenAmount,
    val minAmountB: TokenAmount
): Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        val source = sourceAccount?.let {
            StrKey.encodeToMuxedAccountXDR(it)
        }
        return me.rahimklaber.stellar.base.xdr.Operation(
            sourceAccount = source,
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.LiquidityPoolWithdraw(
                LiquidityPoolWithdrawOp(
                    liquidityPoolID = PoolID.fromHex(poolID),
                    amount = amount.value,
                    minAmountA = minAmountA.value,
                    minAmountB = minAmountB.value
                )
            )
        )
    }
}
