package me.rahimklaber.stellar.base


sealed interface Asset {
    val code: String
    val issuer: String

    fun toXdr() : me.rahimklaber.stellar.base.xdr.Asset = TODO()

    object Native: Asset {
        override val code: String = "XLM"
        override val issuer: String = ""

        //todo maybe use extension functions for this?
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.Asset {
            return me.rahimklaber.stellar.base.xdr.Asset.Native
        }
    }
    sealed class CreditAlphaNum(override val code: String, override val issuer: String): Asset
    class CreditAlphaNum4(override val code: String,override val issuer: String) : CreditAlphaNum(code, issuer)
    class CreditAlphaNum12(override val code: String,override val issuer: String) : CreditAlphaNum(code, issuer)
}