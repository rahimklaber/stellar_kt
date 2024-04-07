package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

data class SCContractInstance(
    val executable: ContractExecutable,
    val storage: SCMap,
): XdrElement {
    override fun encode(stream: XdrStream) {
        executable.encode(stream)
        storage.encode(stream)
    }


    companion object: XdrElementDecoder<SCContractInstance> {
        override fun decode(stream: XdrStream): SCContractInstance {
            val executable = ContractExecutable.decode(stream)
            val storage = SCMap.decode(stream)

            return SCContractInstance(executable, storage)
        }
    }
}
