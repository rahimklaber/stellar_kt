package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// typedef opaque SignatureHint[4];
///////////////////////////////////////////////////////////////////////////
data class SignatureHint(
    val bytes: ByteArray
) {
    init {
        require(bytes.size <= 4)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SignatureHint

        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }
}