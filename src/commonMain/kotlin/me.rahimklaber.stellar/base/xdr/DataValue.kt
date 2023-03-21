package me.rahimklaber.stellar.base.xdr

data class DataValue(
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

        other as DataValue

        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }

    companion object: XdrElementDecoder<DataValue> {
        override fun decode(stream: XdrStream): DataValue {
            val len = stream.readInt()
            val bytes = stream.readBytes(len)
            return DataValue(bytes)
        }
    }
}