// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * EvictionIterator's original definition in the XDR file is:
 * ```
 * struct EvictionIterator {
uint32 bucketListLevel;
bool isCurrBucket;
uint64 bucketFileOffset;
};
 * ```
 */
data class EvictionIterator(
    val bucketListLevel: Uint32,
    val isCurrBucket: Boolean,
    val bucketFileOffset: Uint64,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        bucketListLevel.encode(stream)
        stream.writeBoolean(isCurrBucket)
        bucketFileOffset.encode(stream)
    }

    companion object : XdrElementDecoder<EvictionIterator> {
        override fun decode(stream: XdrInputStream): EvictionIterator {
            val bucketListLevel = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val isCurrBucket = stream.readBoolean()
            val bucketFileOffset = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            return EvictionIterator(
                bucketListLevel,
                isCurrBucket,
                bucketFileOffset,
            )
        }
    }
}
