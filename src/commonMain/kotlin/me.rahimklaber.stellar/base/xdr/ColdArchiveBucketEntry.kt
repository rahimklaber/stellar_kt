// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ColdArchiveBucketEntry's original definition in the XDR file is:
 * ```
 * union ColdArchiveBucketEntry switch (ColdArchiveBucketEntryType type)
{
case COLD_ARCHIVE_METAENTRY:
BucketMetadata metaEntry;
case COLD_ARCHIVE_ARCHIVED_LEAF:
ColdArchiveArchivedLeaf archivedLeaf;
case COLD_ARCHIVE_DELETED_LEAF:
ColdArchiveDeletedLeaf deletedLeaf;
case COLD_ARCHIVE_BOUNDARY_LEAF:
ColdArchiveBoundaryLeaf boundaryLeaf;
case COLD_ARCHIVE_HASH:
ColdArchiveHashEntry hashEntry;
};
 * ```
 */
sealed class ColdArchiveBucketEntry(val type: ColdArchiveBucketEntryType) : XdrElement {
    fun metaEntryOrNull(): ColdArchiveMetaentry? = if (this is ColdArchiveMetaentry) this else null
    data class ColdArchiveMetaentry(
        val metaEntry: BucketMetadata,
    ) : ColdArchiveBucketEntry(ColdArchiveBucketEntryType.COLD_ARCHIVE_METAENTRY) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            metaEntry.encode(stream)
        }
    }

    fun archivedLeafOrNull(): ColdArchiveArchivedLeaf? = if (this is ColdArchiveArchivedLeaf) this else null
    data class ColdArchiveArchivedLeaf(
        val archivedLeaf: me.rahimklaber.stellar.base.xdr.ColdArchiveArchivedLeaf,
    ) : ColdArchiveBucketEntry(ColdArchiveBucketEntryType.COLD_ARCHIVE_ARCHIVED_LEAF) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            archivedLeaf.encode(stream)
        }
    }

    fun deletedLeafOrNull(): ColdArchiveDeletedLeaf? = if (this is ColdArchiveDeletedLeaf) this else null
    data class ColdArchiveDeletedLeaf(
        val deletedLeaf: me.rahimklaber.stellar.base.xdr.ColdArchiveDeletedLeaf,
    ) : ColdArchiveBucketEntry(ColdArchiveBucketEntryType.COLD_ARCHIVE_DELETED_LEAF) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            deletedLeaf.encode(stream)
        }
    }

    fun boundaryLeafOrNull(): ColdArchiveBoundaryLeaf? = if (this is ColdArchiveBoundaryLeaf) this else null
    data class ColdArchiveBoundaryLeaf(
        val boundaryLeaf: me.rahimklaber.stellar.base.xdr.ColdArchiveBoundaryLeaf,
    ) : ColdArchiveBucketEntry(ColdArchiveBucketEntryType.COLD_ARCHIVE_BOUNDARY_LEAF) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            boundaryLeaf.encode(stream)
        }
    }

    fun hashEntryOrNull(): ColdArchiveHash? = if (this is ColdArchiveHash) this else null
    data class ColdArchiveHash(
        val hashEntry: ColdArchiveHashEntry,
    ) : ColdArchiveBucketEntry(ColdArchiveBucketEntryType.COLD_ARCHIVE_HASH) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            hashEntry.encode(stream)
        }
    }

    companion object : XdrElementDecoder<ColdArchiveBucketEntry> {
        override fun decode(stream: XdrInputStream): ColdArchiveBucketEntry {
            return when (val type = ColdArchiveBucketEntryType.decode(stream)) {
                ColdArchiveBucketEntryType.COLD_ARCHIVE_METAENTRY -> {
                    val metaEntry = BucketMetadata.decode(stream)
                    ColdArchiveMetaentry(metaEntry)
                }

                ColdArchiveBucketEntryType.COLD_ARCHIVE_ARCHIVED_LEAF -> {
                    val archivedLeaf = me.rahimklaber.stellar.base.xdr.ColdArchiveArchivedLeaf.decode(stream)
                    ColdArchiveArchivedLeaf(archivedLeaf)
                }

                ColdArchiveBucketEntryType.COLD_ARCHIVE_DELETED_LEAF -> {
                    val deletedLeaf = me.rahimklaber.stellar.base.xdr.ColdArchiveDeletedLeaf.decode(stream)
                    ColdArchiveDeletedLeaf(deletedLeaf)
                }

                ColdArchiveBucketEntryType.COLD_ARCHIVE_BOUNDARY_LEAF -> {
                    val boundaryLeaf = me.rahimklaber.stellar.base.xdr.ColdArchiveBoundaryLeaf.decode(stream)
                    ColdArchiveBoundaryLeaf(boundaryLeaf)
                }

                ColdArchiveBucketEntryType.COLD_ARCHIVE_HASH -> {
                    val hashEntry = ColdArchiveHashEntry.decode(stream)
                    ColdArchiveHash(hashEntry)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
