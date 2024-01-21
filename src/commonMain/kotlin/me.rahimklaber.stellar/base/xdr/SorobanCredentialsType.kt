package me.rahimklaber.stellar.base.xdr

enum class SorobanCredentialsType(val value: Int): XdrElement {
    SOROBAN_CREDENTIALS_SOURCE_ACCOUNT(0),
    SOROBAN_CREDENTIALS_ADDRESS(1);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<SorobanCredentialsType> {
        override fun decode(stream: XdrStream): SorobanCredentialsType {
            val value = stream.readInt()

            return entries
                .firstOrNull{
                    it.value == value
                }
                ?: throw IllegalArgumentException("Cannot decode SorobanCredentialsType for value: $value")
        }
    }
}