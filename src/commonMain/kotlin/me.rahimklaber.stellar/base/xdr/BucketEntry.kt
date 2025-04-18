// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * BucketEntry's original definition in the XDR file is:
 * ```
 * union BucketEntry switch (BucketEntryType type)
{
case LIVEENTRY:
case INITENTRY:
LedgerEntry liveEntry;

case DEADENTRY:
LedgerKey deadEntry;
case METAENTRY:
BucketMetadata metaEntry;
};
 * ```
 */
sealed class BucketEntry(val type: BucketEntryType) : XdrElement {
    data class Liveentry(
        val liveEntry: LedgerEntry,
    ) : BucketEntry(BucketEntryType.LIVEENTRY) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            liveEntry.encode(stream)
        }
    }

    data class Initentry(
        val liveEntry: LedgerEntry,
    ) : BucketEntry(BucketEntryType.INITENTRY) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            liveEntry.encode(stream)
        }
    }

    fun deadEntryOrNull(): Deadentry? = if (this is Deadentry) this else null
    data class Deadentry(
        val deadEntry: LedgerKey,
    ) : BucketEntry(BucketEntryType.DEADENTRY) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            deadEntry.encode(stream)
        }
    }

    fun metaEntryOrNull(): Metaentry? = if (this is Metaentry) this else null
    data class Metaentry(
        val metaEntry: BucketMetadata,
    ) : BucketEntry(BucketEntryType.METAENTRY) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            metaEntry.encode(stream)
        }
    }

    companion object : XdrElementDecoder<BucketEntry> {
        override fun decode(stream: XdrInputStream): BucketEntry {
            return when (val type = BucketEntryType.decode(stream)) {
                BucketEntryType.LIVEENTRY -> {
                    val liveEntry = LedgerEntry.decode(stream)
                    Liveentry(liveEntry)
                }

                BucketEntryType.INITENTRY -> {
                    val liveEntry = LedgerEntry.decode(stream)
                    Initentry(liveEntry)
                }

                BucketEntryType.DEADENTRY -> {
                    val deadEntry = LedgerKey.decode(stream)
                    Deadentry(deadEntry)
                }

                BucketEntryType.METAENTRY -> {
                    val metaEntry = BucketMetadata.decode(stream)
                    Metaentry(metaEntry)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
