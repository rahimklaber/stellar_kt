@file:OptIn(ExperimentalUnsignedTypes::class)

package me.rahimklaber.stellar.base

import com.ionspin.kotlin.crypto.LibsodiumInitializer
import com.ionspin.kotlin.crypto.hash.Hash
import com.ionspin.kotlin.crypto.signature.Signature

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

@OptIn(ExperimentalUnsignedTypes::class)
object Crypto {
    init {
        LibsodiumInitializer.initializeWithCallback {}
    }

    fun sign(data: ByteArray, privateKey: ByteArray): ByteArray{
        return Signature.sign(data.asUByteArray(), privateKey.asUByteArray()).asByteArray()
    }

    fun randomKeyPair(): CryptoKeyPair{
        val generated = Signature.keypair()

        return CryptoKeyPair(generated.publicKey.asByteArray(), generated.secretKey.asByteArray())
    }

    fun keyPairFromPrivate(privateKey: ByteArray): CryptoKeyPair{
        val seeded = Signature.seedKeypair(privateKey.asUByteArray())

        return CryptoKeyPair(seeded.publicKey.asByteArray(), seeded.secretKey.asByteArray())
    }

    fun sha256(data: ByteArray): ByteArray{
        return Hash.sha256(data.asUByteArray()).asByteArray()
    }


}