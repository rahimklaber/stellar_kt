package me.rahimklaber.stellar.base.xdr

import me.rahimklaber.stellar.base.xdr.soroban.SCVal
import me.rahimklaber.stellar.base.xdr.soroban.ScAddress

data class ContractDataEntry(
    val contract: ScAddress,
    val key: SCVal,
    val durability: ContractDataDurability,
    val value: SCVal
): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(0) //TODO: extension point

        contract.encode(stream)
        key.encode(stream)
        durability.encode(stream)
        value.encode(stream)
    }

    companion object: XdrElementDecoder<ContractDataEntry> {
        override fun decode(stream: XdrStream): ContractDataEntry {
            stream.readInt() // TODO: extension point

            return ContractDataEntry(
                ScAddress.decode(stream),
                SCVal.decode(stream),
                ContractDataDurability.decode(stream),
                SCVal.decode(stream)
            )
        }
    }
}
