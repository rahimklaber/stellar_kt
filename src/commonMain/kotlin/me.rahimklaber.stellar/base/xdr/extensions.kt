package me.rahimklaber.stellar.base.xdr

fun ByteArray.toUint256(): Uint256 {
    return Uint256(this)
}