package me.rahimklaber.stellar.base

import me.rahimklaber.stellar.base.operations.Operation
import me.rahimklaber.stellar.base.xdr.*
import me.rahimklaber.stellar.base.xdr.MuxedAccount
import me.rahimklaber.stellar.base.xdr.Transaction

sealed interface Memo {
    fun toXdr(): me.rahimklaber.stellar.base.xdr.Memo

    object None : Memo {
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.Memo {
            return me.rahimklaber.stellar.base.xdr.Memo.None
        }
    }
}

sealed interface Preconditions{
    fun toXdr(): me.rahimklaber.stellar.base.xdr.Preconditions
    object None: Preconditions {
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.Preconditions{
            return me.rahimklaber.stellar.base.xdr.Preconditions.None
        }
    }
}
class Transaction(
    val sourceAccount: String,
    val fee: UInt,
    val sequenceNumber: Long,
    val preconditions: Preconditions,
    val memo: Memo,
    val operations: List<Operation>,
    val network: Network
){
    private val _signatures: MutableList<DecoratedSignature> = mutableListOf()
    val signatures: List<DecoratedSignature>
        get() = _signatures
    fun toV1Xdr(): Transaction {
        return Transaction(
            sourceAccount = MuxedAccount.Ed25519(StrKey.decodeAccountId(sourceAccount).toUint256()),
            fee = fee,
            seqNum = sequenceNumber,
            cond = preconditions.toXdr(),
            memo = memo.toXdr(),
            operations = operations.map(Operation::toXdr)
        )
    }
    fun toEnvelopeXdr() : TransactionEnvelope{
        return TransactionEnvelope.TxV1(
            TransactionV1Envelope(
                toV1Xdr(),
                signatures = _signatures.toList() // copy list
            )
        )
    }

    // data to sign to create a valid signature
    fun hash(): ByteArray {
        val payload = TransactionSignaturePayload.Tx(
            Hash(network.networkId),
            toV1Xdr()
        )
        val stream = XdrStream()
        payload.encode(stream)
        val payloadBytes = stream.readAllBytes()
        //todo need to make sure that libsodium is initialized?
        return com.ionspin.kotlin.crypto.hash.Hash.sha256(payloadBytes.toUByteArray()).toByteArray()
    }

    fun sign(keyPair: KeyPair){
        _signatures.add(keyPair.signDecorated(hash()))
    }
}