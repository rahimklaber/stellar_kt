// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ConfigSettingContractComputeV0's original definition in the XDR file is:
 * ```
 * struct ConfigSettingContractComputeV0
{
// Maximum instructions per ledger
int64 ledgerMaxInstructions;
// Maximum instructions per transaction
int64 txMaxInstructions;
// Cost of 10000 instructions
int64 feeRatePerInstructionsIncrement;

// Memory limit per transaction. Unlike instructions, there is no fee
// for memory, just the limit.
uint32 txMemoryLimit;
};
 * ```
 */
data class ConfigSettingContractComputeV0(
    val ledgerMaxInstructions: Int64,
    val txMaxInstructions: Int64,
    val feeRatePerInstructionsIncrement: Int64,
    val txMemoryLimit: Uint32,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        ledgerMaxInstructions.encode(stream)
        txMaxInstructions.encode(stream)
        feeRatePerInstructionsIncrement.encode(stream)
        txMemoryLimit.encode(stream)
    }

    companion object : XdrElementDecoder<ConfigSettingContractComputeV0> {
        override fun decode(stream: XdrInputStream): ConfigSettingContractComputeV0 {
            val ledgerMaxInstructions = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val txMaxInstructions = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val feeRatePerInstructionsIncrement = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val txMemoryLimit = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            return ConfigSettingContractComputeV0(
                ledgerMaxInstructions,
                txMaxInstructions,
                feeRatePerInstructionsIncrement,
                txMemoryLimit,
            )
        }
    }
}
