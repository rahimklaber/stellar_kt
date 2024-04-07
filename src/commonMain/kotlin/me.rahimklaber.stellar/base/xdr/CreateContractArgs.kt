package me.rahimklaber.stellar.base.xdr

import me.rahimklaber.stellar.base.xdr.soroban.ContractExecutable

data class CreateContractArgs(
    val contractIDPreimage: ContractIDPreimage,
    val executable: ContractExecutable
): XdrElement {
    override fun encode(stream: XdrStream) {
        contractIDPreimage.encode(stream)
        executable.encode(stream)
    }

    companion object: XdrElementDecoder<CreateContractArgs> {
        override fun decode(stream: XdrStream): CreateContractArgs {
            return CreateContractArgs(ContractIDPreimage.decode(stream), ContractExecutable.decode(stream))
        }
    }
}
