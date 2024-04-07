package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// typedef opaque Hash[32];
///////////////////////////////////////////////////////////////////////////
data class Hash(val byteArray: ByteArray) : XdrElement {
    init {
        require(byteArray.size == 32){"Hashes are 256 bit."}
    }

    override fun encode(stream: XdrStream) {
        stream.writeBytes(byteArray)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Hash

        return byteArray.contentEquals(other.byteArray)
    }

    override fun hashCode(): Int {
        return byteArray.contentHashCode()
    }
    companion object: XdrElementDecoder<Hash>{
        override fun decode(stream: XdrStream): Hash {
            return Hash(stream.readBytes(32))
        }

    }
}