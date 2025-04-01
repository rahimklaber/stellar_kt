package me.rahimklaber.stellar.base.xdr

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

interface XdrElement {
    fun encode(stream: XdrOutputStream)
}

fun <T : XdrElement> List<T>.encode(stream: XdrOutputStream) {
    stream.writeInt(size)
    forEach {
        it.encode(stream)
    }
}

interface XdrElementDecoder<T : XdrElement> {
    fun decode(stream: XdrInputStream): T
}

fun ByteArray.toUint256() = Uint256(this)

fun <T : XdrElement> XdrElementDecoder<T>.fromXdrBytes(bytes: ByteArray): T {
    return xdrStream().run {
        writeBytes(bytes)
        decode(this)
    }
}

@OptIn(ExperimentalEncodingApi::class)
fun <T : XdrElement> XdrElementDecoder<T>.fromXdrBase64(base64: String): T {
    return fromXdrBytes(Base64.decode(base64))
}

fun XdrElement.toXdrBytes() = xdrStream().let { stream ->
    encode(stream)
    stream.readAllBytes()
}

@OptIn(ExperimentalEncodingApi::class)
fun XdrElement.toXdrBase64() = xdrStream().let { stream ->
    encode(stream)
    Base64.encode(stream.readAllBytes())
}