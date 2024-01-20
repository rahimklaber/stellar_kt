package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// struct SCNonceKey {
//    int64 nonce;
//};
///////////////////////////////////////////////////////////////////////////
data class SCNonceKey(
    val nonce: Long
): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeLong(nonce)
    }

    companion object: XdrElementDecoder<SCNonceKey>{
        override fun decode(stream: XdrStream): SCNonceKey {
            return SCNonceKey(stream.readLong() )
        }

    }
}
