package me.rahimklaber.stellar.base.xdr

enum class ContractDataDurability(val value: Int) : XdrElement {
    TEMPORARY(0),
    PERSISTENT(1);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object : XdrElementDecoder<ContractDataDurability> {
        override fun decode(stream: XdrStream): ContractDataDurability {
            val value = stream.readInt()
            return entries.find { it.value == value }
                ?: throw IllegalArgumentException("Could not decode ContractDataDurability for $value")
        }
    }
}