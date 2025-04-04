// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * Hash's original definition in the XDR file is:
 * ```
 * typedef opaque Hash[32];
 * ```
 */
@JvmInline
value class Hash(val value: ByteArray) : XdrElement {
    init {
        require(value.size == 32) {
            "Invalid length for Hash"
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeBytes(value)
    }

    companion object : XdrElementDecoder<Hash> {
        override fun decode(stream: XdrInputStream): Hash {
            val valueSize = 32
            val value = stream.readBytes(valueSize)
            return Hash(value)
        }
    }
}
