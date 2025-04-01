// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * LiquidityPoolDepositResult's original definition in the XDR file is:
 * ```
 * union LiquidityPoolDepositResult switch (LiquidityPoolDepositResultCode code)
{
case LIQUIDITY_POOL_DEPOSIT_SUCCESS:
void;
case LIQUIDITY_POOL_DEPOSIT_MALFORMED:
case LIQUIDITY_POOL_DEPOSIT_NO_TRUST:
case LIQUIDITY_POOL_DEPOSIT_NOT_AUTHORIZED:
case LIQUIDITY_POOL_DEPOSIT_UNDERFUNDED:
case LIQUIDITY_POOL_DEPOSIT_LINE_FULL:
case LIQUIDITY_POOL_DEPOSIT_BAD_PRICE:
case LIQUIDITY_POOL_DEPOSIT_POOL_FULL:
void;
};
 * ```
 */
sealed class LiquidityPoolDepositResult(val type: LiquidityPoolDepositResultCode) : XdrElement {
    data object LiquidityPoolDepositSuccess : LiquidityPoolDepositResult(LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_SUCCESS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolDepositMalformed : LiquidityPoolDepositResult(LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_MALFORMED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolDepositNoTrust : LiquidityPoolDepositResult(LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_NO_TRUST) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolDepositNotAuthorized : LiquidityPoolDepositResult(LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_NOT_AUTHORIZED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolDepositUnderfunded : LiquidityPoolDepositResult(LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_UNDERFUNDED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolDepositLineFull : LiquidityPoolDepositResult(LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_LINE_FULL) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolDepositBadPrice : LiquidityPoolDepositResult(LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_BAD_PRICE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object LiquidityPoolDepositPoolFull : LiquidityPoolDepositResult(LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_POOL_FULL) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    companion object : XdrElementDecoder<LiquidityPoolDepositResult> {
        override fun decode(stream: XdrInputStream): LiquidityPoolDepositResult {
            return when (val type = LiquidityPoolDepositResultCode.decode(stream)) {
                LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_SUCCESS -> LiquidityPoolDepositSuccess
                LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_MALFORMED -> LiquidityPoolDepositMalformed
                LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_NO_TRUST -> LiquidityPoolDepositNoTrust
                LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_NOT_AUTHORIZED -> LiquidityPoolDepositNotAuthorized
                LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_UNDERFUNDED -> LiquidityPoolDepositUnderfunded
                LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_LINE_FULL -> LiquidityPoolDepositLineFull
                LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_BAD_PRICE -> LiquidityPoolDepositBadPrice
                LiquidityPoolDepositResultCode.LIQUIDITY_POOL_DEPOSIT_POOL_FULL -> LiquidityPoolDepositPoolFull
                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
