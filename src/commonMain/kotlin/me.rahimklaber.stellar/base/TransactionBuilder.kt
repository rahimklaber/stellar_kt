package me.rahimklaber.stellar.base

import me.rahimklaber.stellar.base.operations.Operation
import me.rahimklaber.stellar.base.xdr.Preconditions


class TransactionBuilder(
    val source: IAccount,
    val network: Network
) {
    private val _operations = mutableListOf<Operation>()

    val operations : List<Operation>
        get () = _operations.toList()
    var fee = 100u
        private set
    var memo: Memo = Memo.None
        private set
    var preconditions: TransactionPreconditions = TransactionPreconditions.None
        private set

    fun setPreconditions(preconditions: TransactionPreconditions) = apply {
        this.preconditions = preconditions
    }

    fun setMemo(memo: Memo) = apply {
        this.memo = memo
    }

    fun setBaseFee(fee: UInt) = apply {
        this.fee = fee
    }

    fun addOperation(operation: Operation) = apply {
        _operations.add(operation)
    }

    fun setFee(fee: UInt) = apply {
        this.fee = fee
    }

    fun build(): Transaction {
        source.incrementSequenceNumber()
        return Transaction(
            sourceAccount = source.accountId,
            fee = fee,
            sequenceNumber = source.sequenceNumber,
            preconditions = preconditions,
            memo = memo,
            operations = _operations.toList(),
            network = network
        )
    }
}

fun transactionBuilder(source: Account, network: Network, block: TransactionBuilder.() -> Unit): Transaction {
    val builder = TransactionBuilder(source, network)
    builder.block()
    return builder.build()
}

fun transactionOfOne(source: Account, network: Network, fee: UInt, operation: Operation): Transaction {
    val builder = TransactionBuilder(source, network)
    builder.addOperation(operation)
    builder.setFee(fee)
    return builder.build()
}

