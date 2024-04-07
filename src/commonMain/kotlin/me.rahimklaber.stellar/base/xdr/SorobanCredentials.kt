package me.rahimklaber.stellar.base.xdr

sealed class SorobanCredentials(val type: SorobanCredentialsType): XdrElement{

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    data object SourceAccount: SorobanCredentials(SorobanCredentialsType.SOROBAN_CREDENTIALS_SOURCE_ACCOUNT)
    data class Address(val address: SorobanAddressCredentials): SorobanCredentials(SorobanCredentialsType.SOROBAN_CREDENTIALS_ADDRESS){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            address.encode(stream)
        }
    }

    companion object: XdrElementDecoder<SorobanCredentials> {
        override fun decode(stream: XdrStream): SorobanCredentials {
            return when(val type = SorobanCredentialsType.decode(stream)){
                SorobanCredentialsType.SOROBAN_CREDENTIALS_SOURCE_ACCOUNT -> SourceAccount
                SorobanCredentialsType.SOROBAN_CREDENTIALS_ADDRESS -> Address(SorobanAddressCredentials.decode(stream))
            }
        }
    }
}
