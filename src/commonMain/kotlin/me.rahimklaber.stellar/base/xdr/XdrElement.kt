package me.rahimklaber.stellar.base.xdr

import io.matthewnelson.encoding.core.Decoder.Companion.decodeToByteArray
import io.matthewnelson.encoding.core.Encoder.Companion.encodeToString
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

interface XdrElement {
    fun encode(stream: XdrStream)
}

//fun XdrElement?.encode(stream: XdrStream){
//    if (this == null){
//        stream.writeInt(0)
//    }else{
//        stream.writeInt(1)
//        this.encode(stream)
//    }
//}

//Note: this is for encoding nullable xdr fields.
//so not for example: `AccountID*`
fun XdrElement?.encodeNullable(stream: XdrStream){
    if (this == null){
        stream.writeInt(0)
    }else{
        stream.writeInt(1)
        this.encode(stream)
    }
}

fun String.encode(stream: XdrStream){
    val bytes = encodeToByteArray()
    stream.writeInt(bytes.size)
    stream.writeBytes(bytes)
}

fun String.Companion.decode(stream: XdrStream): String {
    val size = stream.readInt()

    return stream
        .readBytes(size)
        .decodeToString()
}

fun <T: XdrElement> List<T>.encode(stream: XdrStream){
    stream.writeInt(size)
    forEach {
        it.encode(stream)
    }
}

inline fun <reified T: XdrElement> decodeXdrElementList(stream: XdrStream, elementDecode: (XdrStream) -> T): List<T> {
    val size = stream.readInt()
    val result = mutableListOf<T>()

    repeat(size){
        result.add(elementDecode(stream))
    }

    return result
}

interface XdrElementDecoder<T: XdrElement>{
    fun decode(stream: XdrStream) : T
}

fun <T: XdrElement> XdrElementDecoder<T>.decodeNullable(stream: XdrStream) : T? {
    return if (stream.readInt() == 1){
        decode(stream)
    }else{
        null
    }
}

@OptIn(ExperimentalEncodingApi::class)
fun <T: XdrElement> XdrElementDecoder<T>.decodeFromString(base64String: String) : T {
    val stream = XdrStream()
    stream.writeBytes(Base64.decode(base64String))

    return decode(stream)
}

@OptIn(ExperimentalEncodingApi::class)
fun XdrElement.toXdrString(): String {
    val stream = XdrStream()
    this.encode(stream)

   return Base64.encode(stream.readAllBytes())
}