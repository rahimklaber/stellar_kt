package me.rahimklaber.stellar.base

import me.rahimklaber.stellar.base.operations.Operation


class TransactionBuilder(
    val source: IAccount,
    val network: Network
) {
    private val operations = mutableListOf<Operation>()
    private var fee = 100u
    private var memo: Memo = Memo.None
    private var preconditions: Preconditions = Preconditions.None

    fun setPreconditions(preconditions: Preconditions) = apply {
        this.preconditions = preconditions
    }

    fun setMemo(memo: Memo) = apply {
        this.memo = memo
    }

    fun setBaseFee(fee: UInt) = apply {
        this.fee = fee
    }

    fun addOperation(operation: Operation) = apply {
        operations.add(operation)
    }

    fun build(): Transaction {
        source.incrementSequenceNumber()
        return Transaction(
            sourceAccount = source.accountId,
            fee = fee,
            sequenceNumber = source.sequenceNumber,
            preconditions = preconditions,
            memo = memo,
            operations = operations.toList(),
            network = network
        )
    }
}

fun transactionBuilder(source: Account, network: Network, block: TransactionBuilder.() -> Unit): Transaction {
    val builder = TransactionBuilder(source, network)
    builder.block()
    return builder.build()
}