package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// typedef opaque Thresholds[4];
///////////////////////////////////////////////////////////////////////////
data class Thresholds(
    val bytes: ByteArray
) {
    init {
        require(bytes.size == 4)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Thresholds

        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }
}