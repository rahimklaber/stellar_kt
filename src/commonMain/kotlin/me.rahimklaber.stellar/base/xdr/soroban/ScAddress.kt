package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.AccountID
import me.rahimklaber.stellar.base.xdr.Hash
import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrStream

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
}