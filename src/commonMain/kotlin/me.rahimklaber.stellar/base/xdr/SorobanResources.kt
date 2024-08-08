package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// // Resource limits for a Soroban transaction.
//// The transaction will fail if it exceeds any of these limits.
//struct SorobanResources
//{
//    // The ledger footprint of the transaction.
//    LedgerFootprint footprint;
//    // The maximum number of instructions this transaction can use
//    uint32 instructions;
//
//    // The maximum number of bytes this transaction can read from ledger
//    uint32 readBytes;
//    // The maximum number of bytes this transaction can write to ledger
//    uint32 writeBytes;
//};
///////////////////////////////////////////////////////////////////////////
data class SorobanResources(
    val footprint: LedgerFootprint,
    val instructions: UInt,
    var readBytes: UInt,
    val writeBytes: UInt,
): XdrElement {
    override fun encode(stream: XdrStream) {
        footprint.encode(stream)
        stream.writeInt(instructions.toInt())
        stream.writeInt(readBytes.toInt())
        stream.writeInt(writeBytes.toInt())
    }

    companion object: XdrElementDecoder<SorobanResources> {
        override fun decode(stream: XdrStream): SorobanResources {
            return SorobanResources(
                LedgerFootprint.decode(stream),
                stream.readInt().toUInt(),
                stream.readInt().toUInt(),
                stream.readInt().toUInt(),
            )
        }
    }
}
