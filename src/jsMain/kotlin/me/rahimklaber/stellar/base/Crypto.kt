package me.rahimklaber.stellar.base

actual object Crypto {
    actual fun sign(data: ByteArray, privateKey: ByteArray): ByteArray =
        error("Crypto.sign not implemented for JS")

    actual fun randomKeyPair(): CryptoKeyPair =
        error("Crypto.randomKeyPair not implemented for JS")

    actual fun keyPairFromPrivate(privateKey: ByteArray): CryptoKeyPair =
        error("Crypto.keyPairFromPrivate not implemented for JS")

    actual fun sha256(data: ByteArray): ByteArray =
        error("Crypto.sha256 not implemented for JS")
}
