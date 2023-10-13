package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.Asset
import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.TokenAmount
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.*

sealed class ChangeTrustAsset{
    data class AlphaNum(val asset: Asset.CreditAlphaNum): ChangeTrustAsset() {
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.ChangeTrustAsset {
            return when(asset){
                is Asset.CreditAlphaNum4 -> {
                    val alphaNum4 = (asset.toXdr() as me.rahimklaber.stellar.base.xdr.Asset.AlphaNum4).alphaNum4
                    me.rahimklaber.stellar.base.xdr.ChangeTrustAsset.AlphaNum4(
                            alphaNum4
                    )
                }
                is Asset.CreditAlphaNum12 -> {
                    val alphaNum12= (asset.toXdr() as me.rahimklaber.stellar.base.xdr.Asset.AlphaNum12).alphaNum12
                    me.rahimklaber.stellar.base.xdr.ChangeTrustAsset.AlphaNum12(
                        alphaNum12
                    )
                }
            }
        }
    }

    data class LiquidityPoolConstantProduct(val assetA: Asset, val assetB: Asset, val fee : Int) : ChangeTrustAsset() {
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.ChangeTrustAsset {
            return me.rahimklaber.stellar.base.xdr.ChangeTrustAsset.PoolShare(
               LiquidityPoolParameters.ConstantProduct( LiquidityPoolConstantProductParameters(assetA.toXdr(), assetB.toXdr(), fee))
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
        return me.rahimklaber.stellar.base.xdr.Operation.ChangeTrust(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            ChangeTrustOp(
                line = line.toXdr(),
                limit = limit.value
            )
        )
    }
}