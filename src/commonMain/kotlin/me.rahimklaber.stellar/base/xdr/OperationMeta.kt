package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct OperationMeta
//{
//    LedgerEntryChanges changes;
//};
///////////////////////////////////////////////////////////////////////////
data class OperationMeta(
    val changes: LedgerEntryChanges
) : XdrElement {
    override fun encode(stream: XdrStream) {
        changes.encode(stream)
    }

    companion object : XdrElementDecoder<OperationMeta> {
        override fun decode(stream: XdrStream): OperationMeta {
            return OperationMeta(
                decodeXdrElementList(stream) {
                    LedgerEntryChange.decode(stream)
                }
            )
        }
    }

}
