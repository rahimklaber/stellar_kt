package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.Hash
import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

sealed class ContractExecutable(val type: ContractExecutableType): XdrElement {

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    data class Wasm(val wasmHash: Hash): ContractExecutable(ContractExecutableType.CONTRACT_EXECUTABLE_WASM){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            wasmHash.encode(stream)
        }
    }
    data object Token: ContractExecutable(ContractExecutableType.CONTRACT_EXECUTABLE_TOKEN)

    companion object : XdrElementDecoder<ContractExecutable> {
        override fun decode(stream: XdrStream): ContractExecutable {
            val type = ContractExecutableType.decode(stream)
            return when(type){
                ContractExecutableType.CONTRACT_EXECUTABLE_WASM -> Wasm(Hash.decode(stream))
                ContractExecutableType.CONTRACT_EXECUTABLE_TOKEN -> Token
            }
        }
    }
}