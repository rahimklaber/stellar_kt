package me.rahimklaber.stellar.base

import me.rahimklaber.stellar.base.operations.InvokeHostFunction
import me.rahimklaber.stellar.base.operations.Operation
import me.rahimklaber.stellar.base.xdr.*
import me.rahimklaber.stellar.base.xdr.MuxedAccount
import me.rahimklaber.stellar.base.xdr.Transaction

sealed interface Memo {
    fun toXdr(): me.rahimklaber.stellar.base.xdr.Memo

    data object None : Memo {
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.Memo {
            return me.rahimklaber.stellar.base.xdr.Memo.None
        }
    }

    data class Text(val text: String) : Memo {
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.Memo {
            return me.rahimklaber.stellar.base.xdr.Memo.Text(String32(text.encodeToByteArray()))
        }

    }

    data class Id(val id: ULong) : Memo {
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.Memo {
            return me.rahimklaber.stellar.base.xdr.Memo.Id(id)
        }
    }

}

sealed interface TransactionPreconditions {
    fun toXdr(): Preconditions

    data object None : TransactionPreconditions {
        override fun toXdr(): Preconditions {
            return Preconditions.None
        }
    }

    //confusing naming
    //todo: Create classess instead of using xdr directly?
    data class V2(
        val minSequenceAge: ULong,
        val minSequenceLedgerGap: UInt,
        val extraSigners: List<SignerKey>,
        val timeBounds: TimeBounds? = null,
        val ledgerBounds: LedgerBounds? = null,
        val minSequenceNumber: Long? = null,
    ) : TransactionPreconditions {
        override fun toXdr(): Preconditions {
            return Preconditions.V2(
                PreconditionsV2(
                    timeBounds,
                    ledgerBounds,
                    minSequenceNumber,
                    minSequenceAge,
                    minSequenceLedgerGap,
                    extraSigners
                )
            )
        }
    }
}

fun preconditions(
    minSequenceAge: ULong,
    minSequenceLedgerGap: UInt,
    extraSigners: List<SignerKey>,
    timeBounds: TimeBounds? = null,
    ledgerBounds: LedgerBounds? = null,
    minSequenceNumber: Long? = null,
) = TransactionPreconditions.V2(
    minSequenceAge,
    minSequenceLedgerGap,
    extraSigners,
    timeBounds,
    ledgerBounds,
    minSequenceNumber,
)

data class Transaction(
    val sourceAccount: String,
    val fee: UInt,
    val sequenceNumber: Long,
    val preconditions: TransactionPreconditions,
    val memo: Memo,
    val operations: List<Operation>,
    val network: Network,
    val sorobanData: SorobanTransactionData? = null
) {
    private val _signatures: MutableList<DecoratedSignature> = mutableListOf()
    val signatures: List<DecoratedSignature>
        get() = _signatures

    fun toV1Xdr(): Transaction =
        Transaction(
            sourceAccount = MuxedAccount.Ed25519(StrKey.decodeAccountId(sourceAccount).toUint256()),
            fee = fee,
            seqNum = sequenceNumber,
            cond = preconditions.toXdr(),
            memo = memo.toXdr(),
            operations = operations.map(Operation::toXdr),
            sorobanData = sorobanData
        )

    fun toEnvelopeXdr(): TransactionEnvelope {
        return TransactionEnvelope.TxV1(
            TransactionV1Envelope(
                toV1Xdr(),
                signatures = _signatures.toList() // copy list
            )
        )
    }

    fun withSorobanData(sorobanData: SorobanTransactionData): me.rahimklaber.stellar.base.Transaction =
        copy(sorobanData = sorobanData)

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

    fun sign(keyPair: KeyPair) {
        _signatures.add(keyPair.signDecorated(hash()))
    }
}