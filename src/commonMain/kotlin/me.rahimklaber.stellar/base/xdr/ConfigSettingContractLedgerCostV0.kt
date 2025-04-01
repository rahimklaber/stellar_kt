// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ConfigSettingContractLedgerCostV0's original definition in the XDR file is:
 * ```
 * struct ConfigSettingContractLedgerCostV0
{
// Maximum number of ledger entry read operations per ledger
uint32 ledgerMaxReadLedgerEntries;
// Maximum number of bytes that can be read per ledger
uint32 ledgerMaxReadBytes;
// Maximum number of ledger entry write operations per ledger
uint32 ledgerMaxWriteLedgerEntries;
// Maximum number of bytes that can be written per ledger
uint32 ledgerMaxWriteBytes;

// Maximum number of ledger entry read operations per transaction
uint32 txMaxReadLedgerEntries;
// Maximum number of bytes that can be read per transaction
uint32 txMaxReadBytes;
// Maximum number of ledger entry write operations per transaction
uint32 txMaxWriteLedgerEntries;
// Maximum number of bytes that can be written per transaction
uint32 txMaxWriteBytes;

int64 feeReadLedgerEntry;  // Fee per ledger entry read
int64 feeWriteLedgerEntry; // Fee per ledger entry write

int64 feeRead1KB;  // Fee for reading 1KB

// The following parameters determine the write fee per 1KB.
// Write fee grows linearly until bucket list reaches this size
int64 bucketListTargetSizeBytes;
// Fee per 1KB write when the bucket list is empty
int64 writeFee1KBBucketListLow;
// Fee per 1KB write when the bucket list has reached `bucketListTargetSizeBytes`
int64 writeFee1KBBucketListHigh;
// Write fee multiplier for any additional data past the first `bucketListTargetSizeBytes`
uint32 bucketListWriteFeeGrowthFactor;
};
 * ```
 */
data class ConfigSettingContractLedgerCostV0(
    val ledgerMaxReadLedgerEntries: Uint32,
    val ledgerMaxReadBytes: Uint32,
    val ledgerMaxWriteLedgerEntries: Uint32,
    val ledgerMaxWriteBytes: Uint32,
    val txMaxReadLedgerEntries: Uint32,
    val txMaxReadBytes: Uint32,
    val txMaxWriteLedgerEntries: Uint32,
    val txMaxWriteBytes: Uint32,
    val feeReadLedgerEntry: Int64,
    val feeWriteLedgerEntry: Int64,
    val feeRead1KB: Int64,
    val bucketListTargetSizeBytes: Int64,
    val writeFee1KBBucketListLow: Int64,
    val writeFee1KBBucketListHigh: Int64,
    val bucketListWriteFeeGrowthFactor: Uint32,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        ledgerMaxReadLedgerEntries.encode(stream)
        ledgerMaxReadBytes.encode(stream)
        ledgerMaxWriteLedgerEntries.encode(stream)
        ledgerMaxWriteBytes.encode(stream)
        txMaxReadLedgerEntries.encode(stream)
        txMaxReadBytes.encode(stream)
        txMaxWriteLedgerEntries.encode(stream)
        txMaxWriteBytes.encode(stream)
        feeReadLedgerEntry.encode(stream)
        feeWriteLedgerEntry.encode(stream)
        feeRead1KB.encode(stream)
        bucketListTargetSizeBytes.encode(stream)
        writeFee1KBBucketListLow.encode(stream)
        writeFee1KBBucketListHigh.encode(stream)
        bucketListWriteFeeGrowthFactor.encode(stream)
    }

    companion object : XdrElementDecoder<ConfigSettingContractLedgerCostV0> {
        override fun decode(stream: XdrInputStream): ConfigSettingContractLedgerCostV0 {
            val ledgerMaxReadLedgerEntries = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val ledgerMaxReadBytes = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val ledgerMaxWriteLedgerEntries = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val ledgerMaxWriteBytes = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val txMaxReadLedgerEntries = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val txMaxReadBytes = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val txMaxWriteLedgerEntries = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val txMaxWriteBytes = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val feeReadLedgerEntry = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val feeWriteLedgerEntry = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val feeRead1KB = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val bucketListTargetSizeBytes = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val writeFee1KBBucketListLow = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val writeFee1KBBucketListHigh = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val bucketListWriteFeeGrowthFactor = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            return ConfigSettingContractLedgerCostV0(
                ledgerMaxReadLedgerEntries,
                ledgerMaxReadBytes,
                ledgerMaxWriteLedgerEntries,
                ledgerMaxWriteBytes,
                txMaxReadLedgerEntries,
                txMaxReadBytes,
                txMaxWriteLedgerEntries,
                txMaxWriteBytes,
                feeReadLedgerEntry,
                feeWriteLedgerEntry,
                feeRead1KB,
                bucketListTargetSizeBytes,
                writeFee1KBBucketListLow,
                writeFee1KBBucketListHigh,
                bucketListWriteFeeGrowthFactor,
            )
        }
    }
}
