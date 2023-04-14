package me.rahimklaber.stellar.base.xdr

//typedef opaque AssetCode12[12];
data class AssetCode12(
    val bytes: ByteArray
) : XdrElement {
    init {
        require(bytes.size == 12)
    }

    override fun encode(stream: XdrStream) {
        stream.writeBytes(bytes)
    }

    companion object : XdrElementDecoder<AssetCode12>{
        override fun decode(stream: XdrStream): AssetCode12 {
            return AssetCode12(stream.readBytes(12))
        }

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        if (!super.equals(other)) return false

        other as AssetCode12

        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + bytes.contentHashCode()
        return result
    }

}