package me.rahimklaber.stellar.base

data class Network(
    val networkPassphrase: String
) {
    val networkId = Crypto.sha256(networkPassphrase.encodeToByteArray())

    companion object {
        val PUBLIC = Network("Public Global Stellar Network ; September 2015")
        val TESTNET = Network("Test SDF Network ; September 2015")
    }
}

