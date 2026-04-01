package me.rahimklaber.stellar.base

import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters
import org.bouncycastle.crypto.signers.Ed25519Signer
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.MessageDigest
import java.security.SecureRandom
import java.security.Security

actual object Crypto {
    init {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(BouncyCastleProvider())
        }
    }

    actual fun sign(data: ByteArray, privateKey: ByteArray): ByteArray {
        val privateKeyParams = Ed25519PrivateKeyParameters(privateKey, 0)
        val signer = Ed25519Signer()
        signer.init(true, privateKeyParams)
        signer.update(data, 0, data.size)
        return signer.generateSignature()
    }

    actual fun randomKeyPair(): CryptoKeyPair {
        val privateKey = Ed25519PrivateKeyParameters(SecureRandom())
        val publicKey = privateKey.generatePublicKey()
        return CryptoKeyPair(publicKey.encoded, privateKey.encoded)
    }

    actual fun keyPairFromPrivate(privateKey: ByteArray): CryptoKeyPair {
        val privateKeyParams = Ed25519PrivateKeyParameters(privateKey, 0)
        val publicKey = privateKeyParams.generatePublicKey()
        return CryptoKeyPair(publicKey.encoded, privateKey)
    }

    actual fun sha256(data: ByteArray): ByteArray {
        return MessageDigest.getInstance("SHA-256").digest(data)
    }
}
