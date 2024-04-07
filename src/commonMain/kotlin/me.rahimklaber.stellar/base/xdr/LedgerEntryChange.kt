package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union LedgerEntryChange switch (LedgerEntryChangeType type)
//{
//case LEDGER_ENTRY_CREATED:
//    LedgerEntry created;
//case LEDGER_ENTRY_UPDATED:
//    LedgerEntry updated;
//case LEDGER_ENTRY_REMOVED:
//    LedgerKey removed;
//case LEDGER_ENTRY_STATE:
//    LedgerEntry state;
//};
///////////////////////////////////////////////////////////////////////////
sealed class LedgerEntryChange(val type: LedgerEntryChangeType): XdrElement {

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    data class EntryCreated(val created: LedgerEntry): LedgerEntryChange(LedgerEntryChangeType.LEDGER_ENTRY_CREATED){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            created.encode(stream)
        }
    }
    data class EntryUpdated(val updated: LedgerEntry): LedgerEntryChange(LedgerEntryChangeType.LEDGER_ENTRY_UPDATED){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            updated.encode(stream)
        }
    }
    data class EntryRemoved(val removed: LedgerEntry): LedgerEntryChange(LedgerEntryChangeType.LEDGER_ENTRY_REMOVED){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            removed.encode(stream)
        }
    }
    data class EntryState(val state: LedgerEntry): LedgerEntryChange(LedgerEntryChangeType.LEDGER_ENTRY_STATE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            state.encode(stream)
        }
    }

    companion object: XdrElementDecoder<LedgerEntryChange> {
        override fun decode(stream: XdrStream): LedgerEntryChange {
            val type = LedgerEntryChangeType.decode(stream)

            return when(type){
                LedgerEntryChangeType.LEDGER_ENTRY_CREATED -> EntryCreated(LedgerEntry.decode(stream))
                LedgerEntryChangeType.LEDGER_ENTRY_UPDATED -> EntryUpdated(LedgerEntry.decode(stream))
                LedgerEntryChangeType.LEDGER_ENTRY_REMOVED -> EntryRemoved(LedgerEntry.decode(stream))
                LedgerEntryChangeType.LEDGER_ENTRY_STATE -> EntryState(LedgerEntry.decode(stream))
            }
        }
    }
}
