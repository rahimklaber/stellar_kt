// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * AssetCode4's original definition in the XDR file is:
 * ```
 * typedef opaque AssetCode4[4];
 * ```
 */
@JvmInline
value class AssetCode4(val value: ByteArray) : XdrElement {
    init {
        require(value.size == 4) {
            "Invalid length for AssetCode4"
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeBytes(value)
    }

    companion object : XdrElementDecoder<AssetCode4> {
        override fun decode(stream: XdrInputStream): AssetCode4 {
            val valueSize = 4
            val value = stream.readBytes(valueSize)
            return AssetCode4(value)
        }
    }
}
