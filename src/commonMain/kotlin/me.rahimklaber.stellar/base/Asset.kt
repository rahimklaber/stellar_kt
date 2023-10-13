package me.rahimklaber.stellar.base

import me.rahimklaber.stellar.base.xdr.AlphaNum12
import me.rahimklaber.stellar.base.xdr.AlphaNum4
import me.rahimklaber.stellar.base.xdr.AssetCode12
import me.rahimklaber.stellar.base.xdr.AssetCode4


sealed interface Asset {
    val code: String
    val issuer: String

    fun toXdr() : me.rahimklaber.stellar.base.xdr.Asset

    object Native: Asset {
        override val code: String = "XLM"
        override val issuer: String = ""

        //todo maybe use extension functions for this?
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.Asset {
            return me.rahimklaber.stellar.base.xdr.Asset.Native
        }
    }
    sealed class CreditAlphaNum(override val code: String, override val issuer: String): Asset
    data class CreditAlphaNum4(override val code: String,override val issuer: String) : CreditAlphaNum(code, issuer){
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.Asset {
            return me.rahimklaber.stellar.base.xdr.Asset.AlphaNum4(AlphaNum4(
                AssetCode4(code.encodeToByteArray()),
                StrKey.encodeToAccountIDXDR(issuer)
            ))
        }
    }
    data class CreditAlphaNum12(override val code: String,override val issuer: String) : CreditAlphaNum(code, issuer){
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.Asset {
            return me.rahimklaber.stellar.base.xdr.Asset.AlphaNum12(
                AlphaNum12(
                AssetCode12(code.encodeToByteArray()),
                StrKey.encodeToAccountIDXDR(issuer)
            )
            )
        }
    }
}