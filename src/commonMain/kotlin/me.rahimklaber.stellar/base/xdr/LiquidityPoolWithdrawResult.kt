// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * LiquidityPoolWithdrawResult's original definition in the XDR file is:
 * ```
 * union LiquidityPoolWithdrawResult switch (LiquidityPoolWithdrawResultCode code)
{
case LIQUIDITY_POOL_WITHDRAW_SUCCESS:
void;
case LIQUIDITY_POOL_WITHDRAW_MALFORMED:
case LIQUIDITY_POOL_WITHDRAW_NO_TRUST:
case LIQUIDITY_POOL_WITHDRAW_UNDERFUNDED:
case LIQUIDITY_POOL_WITHDRAW_LINE_FULL:
case LIQUIDITY_POOL_WITHDRAW_UNDER_MINIMUM:
void;
};
 * ```
 */
sealed class LiquidityPoolWithdrawResult(val type: LiquidityPoolWithdrawResultCode) : XdrElement {
    data object LiquidityPoolWithdrawSuccess : LiquidityPoolWithdrawResult(LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_SUCCESS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolWithdrawMalformed : LiquidityPoolWithdrawResult(LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_MALFORMED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolWithdrawNoTrust : LiquidityPoolWithdrawResult(LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_NO_TRUST) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolWithdrawUnderfunded : LiquidityPoolWithdrawResult(LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_UNDERFUNDED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolWithdrawLineFull : LiquidityPoolWithdrawResult(LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_LINE_FULL) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolWithdrawUnderMinimum :
        LiquidityPoolWithdrawResult(LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_UNDER_MINIMUM) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    companion object : XdrElementDecoder<LiquidityPoolWithdrawResult> {
        override fun decode(stream: XdrInputStream): LiquidityPoolWithdrawResult {
            return when (val type = LiquidityPoolWithdrawResultCode.decode(stream)) {
                LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_SUCCESS -> LiquidityPoolWithdrawSuccess
                LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_MALFORMED -> LiquidityPoolWithdrawMalformed
                LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_NO_TRUST -> LiquidityPoolWithdrawNoTrust
                LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_UNDERFUNDED -> LiquidityPoolWithdrawUnderfunded
                LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_LINE_FULL -> LiquidityPoolWithdrawLineFull
                LiquidityPoolWithdrawResultCode.LIQUIDITY_POOL_WITHDRAW_UNDER_MINIMUM -> LiquidityPoolWithdrawUnderMinimum
                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
