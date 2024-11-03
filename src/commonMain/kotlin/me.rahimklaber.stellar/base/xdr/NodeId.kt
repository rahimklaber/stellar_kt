package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline

@JvmInline
value class NodeID(val value: PublicKey): XdrElement by value{
    companion object: XdrElementDecoder<NodeID>{
        override fun decode(stream: XdrStream): NodeID {
            return NodeID(PublicKey.decode(stream))
        }

    }
}