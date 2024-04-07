package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct TransactionMetaV3
//{
//    ExtensionPoint ext;
//
//    LedgerEntryChanges txChangesBefore;  // tx level changes before operations
//                                         // are applied if any
//    OperationMeta operations<>;          // meta for each operation
//    LedgerEntryChanges txChangesAfter;   // tx level changes after operations are
//                                         // applied if any
//    SorobanTransactionMeta* sorobanMeta; // Soroban-specific meta (only for
//                                         // Soroban transactions).
//};
///////////////////////////////////////////////////////////////////////////
data class TransactionMetaV3(
    val txChangesBefore: LedgerEntryChanges,
    val operations: List<OperationMeta>,
    val txChangesAfter: LedgerEntryChanges,
    val sorobanMeta: SorobanTransactionMeta?
): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(0) //ext point

        txChangesBefore.encode(stream)
        operations.encode(stream)
        txChangesAfter.encode(stream)

        sorobanMeta.encodeNullable(stream)
    }

    companion object: XdrElementDecoder<TransactionMetaV3> {
        override fun decode(stream: XdrStream): TransactionMetaV3 {
            stream.readInt() // ext point

            val txChangesBefore = decodeXdrElementList(stream, LedgerEntryChange::decode)
            val operations = decodeXdrElementList(stream, OperationMeta::decode)
            val txChangesAfter = decodeXdrElementList(stream, LedgerEntryChange::decode)
            val sorobanMeta = SorobanTransactionMeta.decodeNullable(stream)

            return TransactionMetaV3(txChangesBefore, operations, txChangesAfter, sorobanMeta)
        }
    }
}
