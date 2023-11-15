package me.rahimklaber.stellar.base

import kotlin.jvm.JvmInline


fun tokenAmount(amount: String): TokenAmount{
    val split = amount.split(".")
    val (before) = split
    var stroops = before.toLong() * 1_000_000_0

    if(split.size > 1){
        require(split[1].length <= 7){"Stellar only supports 7 decimals."}
        stroops += split[1].toLong() * 1_000_000
    }

    return tokenAmount(stroops)
}
fun tokenAmount(stroops: Long) = TokenAmount(stroops)
@JvmInline
value class TokenAmount internal constructor(val value: Long)

