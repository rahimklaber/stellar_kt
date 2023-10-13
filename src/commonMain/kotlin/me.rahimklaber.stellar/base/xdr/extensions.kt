package me.rahimklaber.stellar.base.xdr

import io.matthewnelson.encoding.builders.Base16
import io.matthewnelson.encoding.core.Decoder.Companion.decodeToByteArray

fun ByteArray.toUint256(): Uint256 {
    return Uint256(this)
}

fun <T : XdrElement> XdrElementDecoder<T>.fromHex(hex: String) : T{
    val stream = XdrStream()
    stream.writeBytes(hex.decodeToByteArray(Base16()))
    return decode(stream)
}