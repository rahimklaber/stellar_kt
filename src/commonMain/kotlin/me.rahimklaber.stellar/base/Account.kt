package me.rahimklaber.stellar.base
interface IAccount{
    val accountId: String
    var sequenceNumber: Long

    val incrementedSequenceNumber: Long
        get() = sequenceNumber + 1
    fun incrementSequenceNumber() {
        sequenceNumber+=1
    }
}
data class Account(override val accountId: String, override var sequenceNumber: Long): IAccount

data class MuxedAccount(override val accountId: String, val id: Long, override var sequenceNumber: Long): IAccount
