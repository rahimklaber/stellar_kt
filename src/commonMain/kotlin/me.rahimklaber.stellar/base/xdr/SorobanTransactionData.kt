package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// // The transaction extension for Soroban.
//struct SorobanTransactionData
//{
//    ExtensionPoint ext;
//    SorobanResources resources;
//    // Amount of the transaction `fee` allocated to the Soroban resource fees.
//    // The fraction of `resourceFee` corresponding to `resources` specified 
//    // above is *not* refundable (i.e. fees for instructions, ledger I/O), as
//    // well as fees for the transaction size.
//    // The remaining part of the fee is refundable and the charged value is
//    // based on the actual consumption of refundable resources (events, ledger
//    // rent bumps).
//    // The `inclusionFee` used for prioritization of the transaction is defined
//    // as `tx.fee - resourceFee`.
//    int64 resourceFee;
//};
///////////////////////////////////////////////////////////////////////////
data class SorobanTransactionData(
    val resources: SorobanResources,
    val resourceFee: Long
): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(0)
        resources.encode(stream)
        stream.writeLong(resourceFee)
    }

    companion object: XdrElementDecoder<SorobanTransactionData> {
        override fun decode(stream: XdrStream): SorobanTransactionData {
            stream.readInt() // extension point TODO
            return SorobanTransactionData(
                SorobanResources.decode(stream),
                stream.readLong()
            )
        }
    }
}