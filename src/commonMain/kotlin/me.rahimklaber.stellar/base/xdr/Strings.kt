package me.rahimklaber.stellar.base.xdr
data class String32(val byteArray: ByteArray) : XdrElement {
    init {
        require(byteArray.size <= 32){"String32 are max 32 bytes."}
    }

    override fun encode(stream: XdrStream) {
        stream.writeInt(byteArray.size)
        stream.writeBytes(byteArray)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as String32

        if (!byteArray.contentEquals(other.byteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        return byteArray.contentHashCode()
    }

    companion object : XdrElementDecoder<String32>{
        override fun decode(stream: XdrStream): String32 {
            val len = stream.readInt()
            val stringBytes = stream.readBytes(len)
            return String32(stringBytes)
        }
    }

    override fun toString(): String {
        return byteArray.decodeToString()
    }
}

data class String64(val byteArray: ByteArray) : XdrElement {
    init {
        require(byteArray.size <= 64){"String64 are max 64 bytes."}
    }

    override fun encode(stream: XdrStream) {
        stream.writeInt(byteArray.size)
        stream.writeBytes(byteArray)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as String64

        if (!byteArray.contentEquals(other.byteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        return byteArray.contentHashCode()
    }

    companion object : XdrElementDecoder<String64>{
        override fun decode(stream: XdrStream): String64 {
            val len = stream.readInt()
            val stringBytes = stream.readBytes(len)
            return String64(stringBytes)
        }
    }

    override fun toString(): String {
        return byteArray.decodeToString()
    }
}