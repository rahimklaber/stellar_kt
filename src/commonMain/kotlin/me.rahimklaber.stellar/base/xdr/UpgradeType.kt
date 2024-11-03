package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline

/**
 * typedef opaque UpgradeType<128>;
 *
 */
@JvmInline
value class UpgradeType(val value: ByteArray): XdrElement{
    init{
        require(value.size == 128){"upgrade types are 128 bytes"}
    }
    override fun encode(stream: XdrStream) {
        stream.writeBytes(value)
    }

    companion object: XdrElementDecoder<UpgradeType> {
        override fun decode(stream: XdrStream): UpgradeType {
            return UpgradeType(stream.readBytes(128))
        }
    }
}