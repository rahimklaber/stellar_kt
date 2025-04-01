// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * LedgerCloseMetaV1's original definition in the XDR file is:
 * ```
 * struct LedgerCloseMetaV1
{
LedgerCloseMetaExt ext;

LedgerHeaderHistoryEntry ledgerHeader;

GeneralizedTransactionSet txSet;

// NB: transactions are sorted in apply order here
// fees for all transactions are processed first
// followed by applying transactions
TransactionResultMeta txProcessing<>;

// upgrades are applied last
UpgradeEntryMeta upgradesProcessing<>;

// other misc information attached to the ledger close
SCPHistoryEntry scpInfo<>;

// Size in bytes of BucketList, to support downstream
// systems calculating storage fees correctly.
uint64 totalByteSizeOfBucketList;

// Temp keys that are being evicted at this ledger.
LedgerKey evictedTemporaryLedgerKeys<>;

// Archived restorable ledger entries that are being
// evicted at this ledger.
LedgerEntry evictedPersistentLedgerEntries<>;
};
 * ```
 */
data class LedgerCloseMetaV1(
    val ext: LedgerCloseMetaExt,
    val ledgerHeader: LedgerHeaderHistoryEntry,
    val txSet: GeneralizedTransactionSet,
    val txProcessing: List<TransactionResultMeta>,
    val upgradesProcessing: List<UpgradeEntryMeta>,
    val scpInfo: List<SCPHistoryEntry>,
    val totalByteSizeOfBucketList: Uint64,
    val evictedTemporaryLedgerKeys: List<LedgerKey>,
    val evictedPersistentLedgerEntries: List<LedgerEntry>,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        ext.encode(stream)
        ledgerHeader.encode(stream)
        txSet.encode(stream)
        val txProcessingSize = txProcessing.size
        stream.writeInt(txProcessingSize)
        txProcessing.encodeXdrElements(stream)
        val upgradesProcessingSize = upgradesProcessing.size
        stream.writeInt(upgradesProcessingSize)
        upgradesProcessing.encodeXdrElements(stream)
        val scpInfoSize = scpInfo.size
        stream.writeInt(scpInfoSize)
        scpInfo.encodeXdrElements(stream)
        totalByteSizeOfBucketList.encode(stream)
        val evictedTemporaryLedgerKeysSize = evictedTemporaryLedgerKeys.size
        stream.writeInt(evictedTemporaryLedgerKeysSize)
        evictedTemporaryLedgerKeys.encodeXdrElements(stream)
        val evictedPersistentLedgerEntriesSize = evictedPersistentLedgerEntries.size
        stream.writeInt(evictedPersistentLedgerEntriesSize)
        evictedPersistentLedgerEntries.encodeXdrElements(stream)
    }

    companion object : XdrElementDecoder<LedgerCloseMetaV1> {
        override fun decode(stream: XdrInputStream): LedgerCloseMetaV1 {
            val ext = LedgerCloseMetaExt.decode(stream)
            val ledgerHeader = LedgerHeaderHistoryEntry.decode(stream)
            val txSet = GeneralizedTransactionSet.decode(stream)
            val txProcessingSize = stream.readInt()
            val txProcessing: List<TransactionResultMeta> = decodeXdrElementsList(txProcessingSize, stream, TransactionResultMeta.decoder())
            val upgradesProcessingSize = stream.readInt()
            val upgradesProcessing: List<UpgradeEntryMeta> = decodeXdrElementsList(upgradesProcessingSize, stream, UpgradeEntryMeta.decoder())
            val scpInfoSize = stream.readInt()
            val scpInfo: List<SCPHistoryEntry> = decodeXdrElementsList(scpInfoSize, stream, SCPHistoryEntry.decoder())
            val totalByteSizeOfBucketList = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val evictedTemporaryLedgerKeysSize = stream.readInt()
            val evictedTemporaryLedgerKeys: List<LedgerKey> = decodeXdrElementsList(evictedTemporaryLedgerKeysSize, stream, LedgerKey.decoder())
            val evictedPersistentLedgerEntriesSize = stream.readInt()
            val evictedPersistentLedgerEntries: List<LedgerEntry> =
                decodeXdrElementsList(evictedPersistentLedgerEntriesSize, stream, LedgerEntry.decoder())
            return LedgerCloseMetaV1(
                ext,
                ledgerHeader,
                txSet,
                txProcessing,
                upgradesProcessing,
                scpInfo,
                totalByteSizeOfBucketList,
                evictedTemporaryLedgerKeys,
                evictedPersistentLedgerEntries,
            )
        }
    }
}
