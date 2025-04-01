// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * NodeID's original definition in the XDR file is:
 * ```
 * typedef PublicKey NodeID;
 * ```
 */
@JvmInline
value class NodeID(val value: PublicKey) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        value.encode(stream)
    }

    companion object : XdrElementDecoder<NodeID> {
        override fun decode(stream: XdrInputStream): NodeID {
            val value = PublicKey.decode(stream)
            return NodeID(value)
        }
    }
}
