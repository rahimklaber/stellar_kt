package me.rahimklaber.stellar.base

data class CryptoKeyPair(
    val pub: ByteArray,
    val priv: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CryptoKeyPair

        if (!pub.contentEquals(other.pub)) return false
        if (!priv.contentEquals(other.priv)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = pub.contentHashCode()
        result = 31 * result + priv.contentHashCode()
        return result
    }
}

expect object Crypto {
    fun sign(data: ByteArray, privateKey: ByteArray): ByteArray
    fun randomKeyPair(): CryptoKeyPair
    fun keyPairFromPrivate(privateKey: ByteArray): CryptoKeyPair
    fun sha256(data: ByteArray): ByteArray
}