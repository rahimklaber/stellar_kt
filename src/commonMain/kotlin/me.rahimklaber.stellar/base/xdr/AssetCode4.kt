package me.rahimklaber.stellar.base.xdr

//typedef opaque AssetCode4[4];
data class AssetCode4(
    val bytes: ByteArray
): XdrElement {
    init {
        require(bytes.size == 4)
    }

    override fun encode(stream: XdrStream) {
        stream.writeBytes(bytes)
    }
    companion object : XdrElementDecoder<AssetCode4>{
        override fun decode(stream: XdrStream): AssetCode4 {
            return AssetCode4(stream.readBytes(4))
        }

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        if (!super.equals(other)) return false

        other as AssetCode4

        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + bytes.contentHashCode()
        return result
    }
}
