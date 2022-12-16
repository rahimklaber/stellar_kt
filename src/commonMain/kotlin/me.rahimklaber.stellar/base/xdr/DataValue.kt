package me.rahimklaber.stellar.base.xdr

data class DataValue(
    val bytes: ByteArray
) {
    init {
        require(bytes.size <= 64)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DataValue

        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }
}