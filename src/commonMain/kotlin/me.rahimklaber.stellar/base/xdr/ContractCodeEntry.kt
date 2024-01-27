package me.rahimklaber.stellar.base.xdr

data class ContractCodeEntry(
    val hash: Hash,
    val code: ByteArray
): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(0) //TODO: Extension point
        hash.encode(stream)
        stream.writeInt(code.size)
        stream.writeBytes(code)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ContractCodeEntry

        if (hash != other.hash) return false
        if (!code.contentEquals(other.code)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = hash.hashCode()
        result = 31 * result + code.contentHashCode()
        return result
    }

    companion object: XdrElementDecoder<ContractCodeEntry> {
        override fun decode(stream: XdrStream): ContractCodeEntry {
            stream.readInt() //TODO: extension point

            return ContractCodeEntry(
                Hash.decode(stream),
                stream.readBytes(stream.readInt())
            )
        }
    }
}
