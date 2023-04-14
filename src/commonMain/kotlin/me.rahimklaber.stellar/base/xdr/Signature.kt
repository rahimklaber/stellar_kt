package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// typedef opaque Signature<64>;
///////////////////////////////////////////////////////////////////////////
data class Signature(
    val bytes: ByteArray
) : XdrElement{
    init {
        require(bytes.size <= 64)
    }

    override fun encode(stream: XdrStream) {
        stream.writeInt(bytes.size)
        stream.writeBytes(bytes)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Signature

        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }
    companion object : XdrElementDecoder<Signature>{
        override fun decode(stream: XdrStream): Signature {
            val len = stream.readInt()
            val bytes = stream.readBytes(len)
            return Signature(bytes)
        }

    }
}