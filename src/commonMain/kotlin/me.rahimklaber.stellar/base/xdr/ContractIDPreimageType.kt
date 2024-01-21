package me.rahimklaber.stellar.base.xdr

enum class ContractIDPreimageType(val value: Int): XdrElement {
    CONTRACT_ID_PREIMAGE_FROM_ADDRESS(0),
    CONTRACT_ID_PREIMAGE_FROM_ASSET(1);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<ContractIDPreimageType> {
        override fun decode(stream: XdrStream): ContractIDPreimageType {
            val value = stream.readInt()

            return entries
                .firstOrNull{
                    it.value == value
                } ?: throw IllegalArgumentException("Could not decode HostFunctionType for value: $value")
        }
    }
}