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
            return me.rahimklaber.stellar.base.xdr.Memo.Text(text)
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
            return Preconditions.PrecondNone
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
            return Preconditions.PrecondV2(
                PreconditionsV2(
                    timeBounds,
                    ledgerBounds,
                    minSequenceNumber?.let(::SequenceNumber),
                    Duration(minSequenceAge),
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
    var sorobanData: SorobanTransactionData? = null
) {
    private val _signatures: MutableList<DecoratedSignature> = mutableListOf()
    val signatures: List<DecoratedSignature>
        get() = _signatures

    fun toV1Xdr(): Transaction {
        val ext = if (sorobanData == null) {
            Transaction.TransactionExt.TransactionExtV0
        }else{
            Transaction.TransactionExt.TransactionExtV1(sorobanData!!)
        }

        return Transaction(
            sourceAccount = MuxedAccount.KeyTypeEd25519(StrKey.decodeAccountId(sourceAccount).toUint256()),
            fee = fee,
            seqNum = SequenceNumber(sequenceNumber),
            cond = preconditions.toXdr(),
            memo = memo.toXdr(),
            operations = operations.map(Operation::toXdr),
            ext = ext
        )
    }

    fun toEnvelopeXdr(): TransactionEnvelope {
        return TransactionEnvelope.Tx(
            TransactionV1Envelope(
                toV1Xdr(),
                signatures = _signatures.toList() // copy list
            )
        )
    }

    fun withSorobanData(sorobanData: SorobanTransactionData): me.rahimklaber.stellar.base.Transaction =
        copy(sorobanData = sorobanData)

    fun withAuthEntry(authorizationEntry: SorobanAuthorizationEntry): me.rahimklaber.stellar.base.Transaction {
        val op = operations.first() as InvokeHostFunction

        val newOp = op.copy(
            xdr = op.xdr.copy(auth = op.xdr.auth + authorizationEntry)
        )

        return copy(operations = listOf(newOp))
    }

    // data to sign to create a valid signature
    fun hash(): ByteArray {
        val payload = TransactionSignaturePayload(
            Hash(network.networkId),
            TransactionSignaturePayload.TransactionSignaturePayloadTaggedTransaction.Tx(toV1Xdr())
        )
        val payloadBytes = xdrStream().run {
            payload.encode(this)
            readAllBytes()
        }

        return Crypto.sha256(payloadBytes)
    }

    fun sign(keyPair: KeyPair) {
        _signatures.add(keyPair.signDecorated(hash()))
    }

    companion object{
        fun fromEnvelope(envelope: TransactionEnvelope, network: Network): me.rahimklaber.stellar.base.Transaction  {
            return when(envelope){
                is TransactionEnvelope.Tx -> {
                    val tx = envelope.v1.tx
                    Transaction(
                        StrKey.encodeMuxedAccount(tx.sourceAccount),
                        tx.fee,
                        tx.seqNum.value,
                        when(tx.cond){
                            Preconditions.PrecondNone -> TransactionPreconditions.None
                            is Preconditions.PrecondV2 -> {
                                val cond = tx.cond.v2
                                TransactionPreconditions.V2(
                                    cond.minSeqAge.value,
                                    cond.minSeqLedgerGap,
                                    cond.extraSigners,
                                    cond.timeBounds,
                                    cond.ledgerBounds,
                                    cond.minSeqNum?.value,
                                )
                            }
                            is Preconditions.PrecondTime -> TODO()
                        },
                        when(tx.memo){
                            is me.rahimklaber.stellar.base.xdr.Memo.Id -> Memo.Id(tx.memo.id)
                            is me.rahimklaber.stellar.base.xdr.Memo.Text -> Memo.Text(tx.memo.text)
                            me.rahimklaber.stellar.base.xdr.Memo.None -> Memo.None
                            is me.rahimklaber.stellar.base.xdr.Memo.Hash -> TODO()
                            is me.rahimklaber.stellar.base.xdr.Memo.Return -> TODO()
                        },
                        tx.operations.map(Operation::fromXdr),
                        network,
                        if(tx.ext is Transaction.TransactionExt.TransactionExtV1) tx.ext.sorobanData else null

                    ).apply {
                        _signatures.addAll(envelope.v1.signatures)
                    }
                }
                is TransactionEnvelope.TxFeeBump -> TODO()
                else -> TODO()
            }
        }

        fun fromEnvelopeXdr(envelopeBase64: String, network: Network): me.rahimklaber.stellar.base.Transaction {
            val envelope = TransactionEnvelope.fromXdrBase64(envelopeBase64)

            return fromEnvelope(envelope, network)
        }
    }
}