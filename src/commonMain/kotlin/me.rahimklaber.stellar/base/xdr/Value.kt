package me.rahimklaber.stellar.base.xdr

import me.rahimklaber.stellar.base.xdr.XdrStream
import kotlin.jvm.JvmInline

@JvmInline
value class Value(
    val `value`: ByteArray,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeBytes(value)
    }

    companion object : XdrElementDecoder<Value> {
        override fun decode(stream: XdrStream): Value {
            val value = stream.readBytes(stream.readInt())
            return Value(value)
        }
    }
}
