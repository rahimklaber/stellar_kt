package me.rahimklaber.stellar.base

import me.rahimklaber.stellar.base.xdr.*

class KeyPair internal constructor(
    private val publicKey: ByteArray,
    private val privateKey: ByteArray?
){

    val accountId by lazy {
        StrKey.encodeAccountId(publicKey)
    }

    fun sign(data: ByteArray): ByteArray {
        return Crypto.sign(data, privateKey?: error("Cannot sign without private key"))
            .copyOf(64)
    }

    val signatureHint by lazy {
        val pubKeyXdr = PublicKey.Ed25519(publicKey.toUint256())

        val pubKeyXdrBytes = xdrStream().run {
            pubKeyXdr.encode(this)
            readAllBytes()
        }

        SignatureHint(pubKeyXdrBytes.copyOfRange(pubKeyXdrBytes.size -4, pubKeyXdrBytes.size))
    }

    fun signDecorated(data: ByteArray) : DecoratedSignature{
        val signatureBytes = sign(data)

        return DecoratedSignature(
            signatureHint,
            Signature(signatureBytes)
        )
    }
    companion object
}

fun KeyPair.Companion.random(): KeyPair {
    val (pub, priv) = Crypto.randomKeyPair()
    return KeyPair(pub,priv)
}
fun KeyPair.Companion.fromFromPrivateKey(privateKey: ByteArray): KeyPair {
    val (pub, priv) = Crypto.keyPairFromPrivate(privateKey)
    return KeyPair(pub,priv)
}

fun KeyPair.Companion.fromPublicKey(publicKey: ByteArray): KeyPair {
    return KeyPair(publicKey, null)
}

fun KeyPair.Companion.fromSecretSeed(secretSeed: String): KeyPair {
    return KeyPair.fromFromPrivateKey(StrKey.decodeSecretSeed(secretSeed))
}