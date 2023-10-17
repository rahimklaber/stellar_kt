package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

enum class ScAddressType(val value: Int): XdrElement {
    SC_ADDRESS_TYPE_ACCOUNT(0),
    SC_ADDRESS_TYPE_CONTRACT(1);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object : XdrElementDecoder<ScAddressType> {
        override fun decode(stream: XdrStream): ScAddressType {
            val value = stream.readInt()
            return ScAddressType.entries.getOrNull(value)
                ?: throw IllegalArgumentException("could not decode $value to ScAddressType")
        }
    }
}