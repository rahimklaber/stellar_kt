// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * BucketListType's original definition in the XDR file is:
 * ```
 * enum BucketListType
{
LIVE = 0,
HOT_ARCHIVE = 1,
COLD_ARCHIVE = 2
};
 * ```
 */
enum class BucketListType(val value: Int) : XdrElement {
    LIVE(0),
    HOT_ARCHIVE(1),
    COLD_ARCHIVE(2);

    companion object : XdrElementDecoder<BucketListType> {
        override fun decode(stream: XdrInputStream): BucketListType {
            return when (val value = stream.readInt()) {
                0 -> LIVE
                1 -> HOT_ARCHIVE
                2 -> COLD_ARCHIVE
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
