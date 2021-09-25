package me.rahimklaber.sdk.base


sealed class Asset(val code: String, val issuer: String) {
    class Native: Asset("XLM","")
    sealed class CreditAlphaNum(code: String, issuer: String): Asset(code, issuer)
    class CreditAlphaNum4(code: String,issuer: String) : CreditAlphaNum(code, issuer)
    class CreditAlphaNum12(code: String,issuer: String) : CreditAlphaNum(code, issuer)
}