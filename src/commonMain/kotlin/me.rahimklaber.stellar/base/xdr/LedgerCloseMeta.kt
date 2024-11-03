package me.rahimklaber.stellar.base.xdr

/**
 * union LedgerCloseMeta switch (int v)
 * {
 * case 0:
 *     LedgerCloseMetaV0 v0;
 * case 1:
 *     LedgerCloseMetaV1 v1;
 * };
 */
sealed class LedgerCloseMeta(val discriminant: Int) : XdrElement {

    override fun encode(stream: XdrStream) {
        stream.writeInt(discriminant)
    }

    data class V1(val v1: LedgerCloseMetaV1): LedgerCloseMeta(1){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            v1.encode(stream)
        }
    }

    companion object: XdrElementDecoder<LedgerCloseMeta> {
        override fun decode(stream: XdrStream): LedgerCloseMeta {
            return when(val type = stream.readInt()){
                1 -> {
                    V1(LedgerCloseMetaV1.decode(stream))
                }
                else -> xdrDecodeError("could not decode LedgerCloseMeta for $type")
            }
        }
    }


}

/**
 * struct LedgerCloseMetaV1
 * {
 *     LedgerCloseMetaExt ext;
 *
 *     LedgerHeaderHistoryEntry ledgerHeader;
 *
 *     GeneralizedTransactionSet txSet;
 *
 *     // NB: transactions are sorted in apply order here
 *     // fees for all transactions are processed first
 *     // followed by applying transactions
 *     TransactionResultMeta txProcessing<>;
 *
 *     // upgrades are applied last
 *     UpgradeEntryMeta upgradesProcessing<>;
 *
 *     // other misc information attached to the ledger close
 *     SCPHistoryEntry scpInfo<>;
 *
 *     // Size in bytes of BucketList, to support downstream
 *     // systems calculating storage fees correctly.
 *     uint64 totalByteSizeOfBucketList;
 *
 *     // Temp keys that are being evicted at this ledger.
 *     LedgerKey evictedTemporaryLedgerKeys<>;
 *
 *     // Archived restorable ledger entries that are being
 *     // evicted at this ledger.
 *     LedgerEntry evictedPersistentLedgerEntries<>;
 * };
 */
data class LedgerCloseMetaV1(
    val ext: LedgerCloseMetaExt,
    val ledgerHeader: LedgerHeaderHistoryEntry,
    val txSet: GeneralizedTransactionSet,
    val txProcessing: List<TransactionResultMeta>,
    val upgradesProcessing: List<UpgradeEntryMeta>,
    val scpInfo: List<SCPHistoryEntry>,
    val totalByteSizeOfBucketList: ULong,
    val evictedTemporaryLedgerKeys: List<LedgerKey>,
    val evictedPersistentLedgerEntries: List<LedgerKey>,

    ) : XdrElement {
    override fun encode(stream: XdrStream) {
        ext.encode(stream)
        ledgerHeader.encode(stream)
        txSet.encode(stream)
        txProcessing.encode(stream)
        upgradesProcessing.encode(stream)
        scpInfo.encode(stream)
        stream.writeLong(totalByteSizeOfBucketList.toLong())
        evictedTemporaryLedgerKeys.encode(stream)
        evictedPersistentLedgerEntries.encode(stream)
    }

    companion object : XdrElementDecoder<LedgerCloseMetaV1> {
        override fun decode(stream: XdrStream): LedgerCloseMetaV1 {
            val ext = LedgerCloseMetaExt.decode(stream)
            val ledgerHeader = LedgerHeaderHistoryEntry.decode(stream)
            val txSet = GeneralizedTransactionSet.decode(stream)
            val txProcessing = decodeXdrElementList(stream, TransactionResultMeta::decode)
            val upgradesProcessing = decodeXdrElementList(stream, UpgradeEntryMeta::decode)
            val scpInfo = decodeXdrElementList(stream, SCPHistoryEntry::decode)
            val totalByteSizeOfBucketList = stream.readLong().toULong()
            val evictedTemporaryLedgerKeys = decodeXdrElementList(stream, LedgerKey::decode)
            val evictedPersistentLedgerEntries = decodeXdrElementList(stream, LedgerKey::decode)

            return LedgerCloseMetaV1(
                ext,
                ledgerHeader,
                txSet,
                txProcessing,
                upgradesProcessing,
                scpInfo,
                totalByteSizeOfBucketList,
                evictedTemporaryLedgerKeys,
                evictedPersistentLedgerEntries
            )
        }
    }
}

/**
 *         union LedgerCloseMetaExt switch (int v)
 *         {
 *         case 0:
 *             void;
 *         case 1:
 *             LedgerCloseMetaExtV1 v1;
 *         };
 */
sealed class LedgerCloseMetaExt(
    val v: Int,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(v)
    }

    data object V0 : LedgerCloseMetaExt(0)

    data class V1(
        val v1: LedgerCloseMetaExtV1,
    ) : LedgerCloseMetaExt(1) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            v1.encode(stream)
        }
    }

    companion object : XdrElementDecoder<LedgerCloseMetaExt> {
        override fun decode(stream: XdrStream): LedgerCloseMetaExt {
            return when (val type = stream.readInt()) {
                0 -> V0
                1 -> {
                    val v1 = LedgerCloseMetaExtV1.decode(stream)
                    V1(v1)
                }
                else -> xdrDecodeError("Could not decode LedgerCloseMetaExt for $type")
            }
        }
    }
}

/**
 * struct LedgerCloseMetaExtV1
 * {
 *     ExtensionPoint ext;
 *     int64 sorobanFeeWrite1KB;
 * };
 */
data class LedgerCloseMetaExtV1(
    val ext: ExtensionPoint,
    val sorobanFeeWrite1KB: Long,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        ext.encode(stream)
        stream.writeLong(sorobanFeeWrite1KB)
    }

    companion object : XdrElementDecoder<LedgerCloseMetaExtV1> {
        override fun decode(stream: XdrStream): LedgerCloseMetaExtV1 {
            val ext = ExtensionPoint.decode(stream)
            val sorobanFeeWrite1KB = stream.readLong()

            return LedgerCloseMetaExtV1(ext, sorobanFeeWrite1KB)
        }
    }
}
