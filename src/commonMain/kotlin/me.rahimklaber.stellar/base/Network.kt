package me.rahimklaber.stellar.base

import com.ionspin.kotlin.crypto.hash.Hash
import com.ionspin.kotlin.crypto.hash.Sha256State

data class Network(
    val networkPassphrase: String
){
    val networkId = Crypto.sha256(networkPassphrase.encodeToByteArray())
    companion object{
        val PUBLIC = Network( "Public Global Stellar Network ; September 2015")
        val TESTNET = Network( "Test SDF Network ; September 2015")
    }
}

