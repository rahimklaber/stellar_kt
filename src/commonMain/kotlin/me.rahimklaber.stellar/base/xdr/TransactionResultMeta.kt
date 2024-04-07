package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// // This struct groups together changes on a per transaction basis
//// note however that fees and transaction application are done in separate
//// phases
//struct TransactionResultMeta
//{
//    TransactionResultPair result;
//    LedgerEntryChanges feeProcessing;
//    TransactionMeta txApplyProcessing;
//};
///////////////////////////////////////////////////////////////////////////
data class TransactionResultMeta(
    val result: TransactionResultPair,
    val feeProcessing: LedgerEntryChanges,
    val txApplyProcessing: TransactionMeta
): XdrElement {
    override fun encode(stream: XdrStream) {
        result.encode(stream)
        feeProcessing.encode(stream)
        txApplyProcessing.encode(stream)
    }

    companion object: XdrElementDecoder<TransactionResultMeta> {
        override fun decode(stream: XdrStream): TransactionResultMeta {
            return TransactionResultMeta(
                TransactionResultPair.decode(stream),
                decodeXdrElementList(stream,LedgerEntryChange::decode),
                TransactionMeta.decode(stream)
            )
        }
    }
}