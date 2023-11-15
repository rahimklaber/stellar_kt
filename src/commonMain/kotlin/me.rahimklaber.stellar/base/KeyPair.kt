package me.rahimklaber.stellar.base

import com.ionspin.kotlin.crypto.LibsodiumInitializer
import com.ionspin.kotlin.crypto.signature.Signature
import me.rahimklaber.stellar.base.xdr.*

class KeyPair internal constructor(
    private val publicKey: UByteArray,
    private val privateKey: UByteArray?
){

    val accountId by lazy {
        StrKey.encodeAccountId(publicKey.toByteArray())
    }

    fun sign(data: ByteArray): ByteArray {
        return Signature.sign(data.toUByteArray(),privateKey?: error("Private key not present"))
            .copyOfRange(0,64).toByteArray()
    }

    val signatureHint by lazy {
        val pubkeyXdr = PublicKey.PublicKeyEd25519(publicKey.toByteArray().toUint256())

        val stream = XdrStream()
        pubkeyXdr.encode(stream)

        val pubkeyXdrBytes = stream.readAllBytes()
        SignatureHint(pubkeyXdrBytes.copyOfRange(pubkeyXdrBytes.size -4, pubkeyXdrBytes.size))
    }

    fun signDecorated(data: ByteArray) : DecoratedSignature{
        val signatureBytes = sign(data)

        return DecoratedSignature(
            signatureHint,
            Signature(signatureBytes)
        )
    }
    companion object{
        init {
            LibsodiumInitializer.initializeWithCallback {}
        }

        val isInit: Boolean
            get() =  LibsodiumInitializer.isInitialized()
    }

}

fun KeyPair.Companion.random(): KeyPair {
    val (pub, priv)  = Signature.keypair()
    return KeyPair(pub,priv)
}
fun KeyPair.Companion.fromFromPrivateKey(privateKey: UByteArray): KeyPair {
    val (pub, priv) = Signature.seedKeypair(privateKey)
    return KeyPair(pub,priv)
}

fun KeyPair.Companion.fromPublicKey(publicKey: UByteArray): KeyPair {
    return KeyPair(publicKey, null)
}

fun KeyPair.Companion.fromSecretSeed(secretSeed: String): KeyPair {
    return KeyPair.fromFromPrivateKey(StrKey.decodeSecretSeed(secretSeed).toUByteArray())
}