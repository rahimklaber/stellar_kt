package me.rahimklaber.stellar.base.xdr

enum class SorobanAuthorizedFunctionType(val value: Int): XdrElement {
    SOROBAN_AUTHORIZED_FUNCTION_TYPE_CONTRACT_FN(0),
    SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_HOST_FN(1);


    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<SorobanAuthorizedFunctionType> {
        override fun decode(stream: XdrStream): SorobanAuthorizedFunctionType {
            val value = stream.readInt()
            return entries
                .firstOrNull{
                    it.value == value
                } ?: throw IllegalArgumentException("Could not decode SorobanAuthorizedFunctionType for value: $value")

        }
    }
}