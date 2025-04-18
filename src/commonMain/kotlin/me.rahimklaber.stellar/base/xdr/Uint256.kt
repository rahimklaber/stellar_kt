// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * Uint256's original definition in the XDR file is:
 * ```
 * typedef opaque uint256[32];
 * ```
 */
@JvmInline
value class Uint256(val value: ByteArray) : XdrElement {
    init {
        require(value.size == 32) {
            "Invalid length for Uint256"
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeBytes(value)
    }

    companion object : XdrElementDecoder<Uint256> {
        override fun decode(stream: XdrInputStream): Uint256 {
            val valueSize = 32
            val value = stream.readBytes(valueSize)
            return Uint256(value)
        }
    }
}
