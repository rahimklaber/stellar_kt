package me.rahimklaber.stellar.base

import com.ionspin.kotlin.crypto.hash.Hash
import com.ionspin.kotlin.crypto.hash.Sha256State

data class Network(
    val networkPassphrase: String
){
    val networkId by lazy {
        //todo: The conversions between UBytearray and Bytearray might cost a lot of performance in some cases.
        //not in this case of course, but in general.
        Hash.sha256(networkPassphrase.encodeToByteArray().toUByteArray()).toByteArray()
    }
    companion object{
        val PUBLIC = Network( "Public Global Stellar Network ; September 2015")
        val TESTNET = Network( "Test SDF Network ; September 2015")
    }
}

