// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * ContractCostParams's original definition in the XDR file is:
 * ```
 * typedef ContractCostParamEntry ContractCostParams<CONTRACT_COST_COUNT_LIMIT>;
 * ```
 */
@JvmInline
value class ContractCostParams(val value: List<ContractCostParamEntry>) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        val valueSize = value.size
        stream.writeInt(valueSize)
        value.encodeXdrElements(stream)
    }

    companion object : XdrElementDecoder<ContractCostParams> {
        override fun decode(stream: XdrInputStream): ContractCostParams {
            val valueSize = stream.readInt()
            val value: List<ContractCostParamEntry> = decodeXdrElementsList(valueSize, stream, ContractCostParamEntry.decoder())
            return ContractCostParams(value)
        }
    }
}
