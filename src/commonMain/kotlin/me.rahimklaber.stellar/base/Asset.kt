package me.rahimklaber.stellar.base


sealed interface Asset {
    val code: String
    val issuer: String
    class Native: Asset {
        override val code: String = "XLM"
        override val issuer: String = ""
    }
    sealed class CreditAlphaNum(override val code: String, override val issuer: String): Asset
    class CreditAlphaNum4(override val code: String,override val issuer: String) : CreditAlphaNum(code, issuer)
    class CreditAlphaNum12(override val code: String,override val issuer: String) : CreditAlphaNum(code, issuer)
}