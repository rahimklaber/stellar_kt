package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.*

sealed class ScAddress(val type: ScAddressType): XdrElement {

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    data class Account(val accountId: AccountID): ScAddress(ScAddressType.SC_ADDRESS_TYPE_ACCOUNT){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            accountId.encode(stream)
        }
    }
    data class Contract(val contractId: Hash): ScAddress(ScAddressType.SC_ADDRESS_TYPE_CONTRACT){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            contractId.encode(stream)
        }
    }

    companion object: XdrElementDecoder<ScAddress>{
        override fun decode(stream: XdrStream): ScAddress {
            return when(val type = ScAddressType.decode(stream)){
                ScAddressType.SC_ADDRESS_TYPE_ACCOUNT -> Account(AccountID.decode(stream))
                ScAddressType.SC_ADDRESS_TYPE_CONTRACT -> Contract(Hash.decode(stream))
            }
        }

    }
}