// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * EncryptedBody's original definition in the XDR file is:
 * ```
 * typedef opaque EncryptedBody<64000>;
 * ```
 */
@JvmInline
value class EncryptedBody(val value: ByteArray) : XdrElement {
    init {
        require(value.size <= 64000) {
            "Invalid length for EncryptedBody"
        }
    }

    override fun encode(stream: XdrOutputStream) {
        val valueSize = value.size
        stream.writeInt(valueSize)
        stream.writeBytes(value)
    }

    companion object : XdrElementDecoder<EncryptedBody> {
        override fun decode(stream: XdrInputStream): EncryptedBody {
            val valueSize = stream.readInt()
            val value = stream.readBytes(valueSize)
            return EncryptedBody(value)
        }
    }
}
