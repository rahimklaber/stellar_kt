package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// // represents the meta in the transaction table history
//
//// STATE is emitted every time a ledger entry is modified/deleted
//// and the entry was not already modified in the current ledger
//
//enum LedgerEntryChangeType
//{
//    LEDGER_ENTRY_CREATED = 0, // entry was added to the ledger
//    LEDGER_ENTRY_UPDATED = 1, // entry was modified in the ledger
//    LEDGER_ENTRY_REMOVED = 2, // entry was removed from the ledger
//    LEDGER_ENTRY_STATE = 3    // value of the entry
//};
///////////////////////////////////////////////////////////////////////////
enum class LedgerEntryChangeType(val value: Int): XdrElement {
    LEDGER_ENTRY_CREATED ( 0), // entry was added to the ledger
    LEDGER_ENTRY_UPDATED ( 1), // entry was modified in the ledger
    LEDGER_ENTRY_REMOVED ( 2), // entry was removed from the ledger
    LEDGER_ENTRY_STATE (3);    // value of the entry

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<LedgerEntryChangeType> {
        override fun decode(stream: XdrStream): LedgerEntryChangeType {
            val value = stream.readInt()

            return entries.find{it.value == value} ?: throw IllegalArgumentException("Could not decode LedgerEntryChangeType for value $value")
        }
    }
}
