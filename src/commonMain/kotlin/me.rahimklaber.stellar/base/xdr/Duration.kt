// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * Duration's original definition in the XDR file is:
 * ```
 * typedef uint64 Duration;
 * ```
 */
@JvmInline
value class Duration(val value: Uint64) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        value.encode(stream)
    }

    companion object : XdrElementDecoder<Duration> {
        override fun decode(stream: XdrInputStream): Duration {
            val value = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            return Duration(value)
        }
    }
}
