package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// typedef opaque Thresholds[4];
///////////////////////////////////////////////////////////////////////////
data class Thresholds(
    val bytes: ByteArray
) : XdrElement {
    init {
        require(bytes.size == 4)
    }

    override fun encode(stream: XdrStream) {
        stream.writeBytes(bytes)
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

    companion object : XdrElementDecoder<Thresholds>{
        override fun decode(stream: XdrStream): Thresholds {
            val bytes = stream.readBytes(4)
            return Thresholds(bytes)
        }

    }
}