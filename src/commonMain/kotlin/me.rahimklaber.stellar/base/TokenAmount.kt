package me.rahimklaber.stellar.base



fun tokenAmount(value: String){
    
}

fun tokenAmount(stroops: Long) = TokenAmount(stroops)
data class TokenAmount internal constructor(val value: Long)

