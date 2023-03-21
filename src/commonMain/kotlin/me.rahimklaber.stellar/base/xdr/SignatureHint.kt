package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// typedef opaque SignatureHint[4];
///////////////////////////////////////////////////////////////////////////
data class SignatureHint(
    val bytes: ByteArray
) : XdrElement{
    init {
        require(bytes.size == 4)
    }

    override fun encode(stream: XdrStream) {
        stream.writeBytes(bytes)
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
    companion object : XdrElementDecoder<SignatureHint> {
        override fun decode(stream: XdrStream): SignatureHint {
            val bytes = stream.readBytes(4)
            return SignatureHint(bytes)
        }
    }
}