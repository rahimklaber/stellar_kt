package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

enum class ContractExecutableType(
    val value: Int
) : XdrElement {
    CONTRACT_EXECUTABLE_WASM(0),
    CONTRACT_EXECUTABLE_TOKEN(1);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object : XdrElementDecoder<ContractExecutableType> {
        override fun decode(stream: XdrStream): ContractExecutableType {
            val value = stream.readInt()
            return entries.getOrNull(value)
                ?: throw IllegalArgumentException("could not decode $value to ContractExecutableType")
        }
    }
}