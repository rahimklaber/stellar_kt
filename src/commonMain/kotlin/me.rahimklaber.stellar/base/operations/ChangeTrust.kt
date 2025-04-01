package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.Asset
import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.TokenAmount
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.ChangeTrustOp
import me.rahimklaber.stellar.base.xdr.LiquidityPoolConstantProductParameters
import me.rahimklaber.stellar.base.xdr.LiquidityPoolParameters

sealed class ChangeTrustAsset {
    data class AlphaNum(val asset: Asset.AlphaNum) : ChangeTrustAsset() {
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.ChangeTrustAsset {
            return when (val assetXdr = asset.toXdr()) {
                is me.rahimklaber.stellar.base.xdr.Asset.CreditAlphanum4 -> {
                    me.rahimklaber.stellar.base.xdr.ChangeTrustAsset.CreditAlphanum4(
                        assetXdr.alphaNum4
                    )
                }

                is me.rahimklaber.stellar.base.xdr.Asset.CreditAlphanum12 -> {
                    me.rahimklaber.stellar.base.xdr.ChangeTrustAsset.CreditAlphanum12(
                        assetXdr.alphaNum12
                    )
                }

                me.rahimklaber.stellar.base.xdr.Asset.Native -> me.rahimklaber.stellar.base.xdr.ChangeTrustAsset.Native
            }
        }
    }

    data class LiquidityPoolConstantProduct(val assetA: Asset, val assetB: Asset, val fee: Int) : ChangeTrustAsset() {
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.ChangeTrustAsset {
            return me.rahimklaber.stellar.base.xdr.ChangeTrustAsset.PoolShare(
                LiquidityPoolParameters.ConstantProduct(
                    LiquidityPoolConstantProductParameters(
                        assetA.toXdr(),
                        assetB.toXdr(),
                        fee
                    )
                )
            )
        }

    }

    abstract fun toXdr(): me.rahimklaber.stellar.base.xdr.ChangeTrustAsset
}

data class ChangeTrust(
    override val sourceAccount: String? = null,
    val line: ChangeTrustAsset,
    val limit: TokenAmount
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.ChangeTrust(
                ChangeTrustOp(
                    line = line.toXdr(),
                    limit = limit.value
                )
            )
        )
    }
}