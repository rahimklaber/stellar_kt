package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// typedef opaque uint256[32];
///////////////////////////////////////////////////////////////////////////
data class Uint256(val byteArray: ByteArray) : XdrElement {
    init {
        require(byteArray.size == 32){"Uint256 are 256 bit."}
    }

    override fun encode(stream: XdrStream) {
        stream.writeBytes(byteArray)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Hash

        if (!byteArray.contentEquals(other.byteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        return byteArray.contentHashCode()
    }

    companion object : XdrElementDecoder<Uint256>{
        override fun decode(stream: XdrStream): Uint256 {
            val uintBytes = stream.readBytes(32)
            return Uint256(uintBytes)
        }

    }
}