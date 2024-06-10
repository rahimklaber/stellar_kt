package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.*

data class SCContractInstance(
    val executable: ContractExecutable,
    val storage: SCMap?,
): XdrElement {
    override fun encode(stream: XdrStream) {
        executable.encode(stream)
        storage.encodeNullable(stream)
    }


    companion object: XdrElementDecoder<SCContractInstance> {
        override fun decode(stream: XdrStream): SCContractInstance {
            val executable = ContractExecutable.decode(stream)
            val storage = SCMap.decodeNullable(stream)

            return SCContractInstance(executable, storage)
        }
    }
}
