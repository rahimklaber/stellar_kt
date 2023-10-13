package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.xdr.CreateAccountOp

data class CreateAccount(
    override val sourceAccount: String? = null,
    val destination: String,
    val startingBalance : TokenAmount,
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation.CreateAccount(
            sourceAccount =  sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            CreateAccountOp(
                destination = StrKey.encodeToAccountIDXDR(destination),
                startingBalance = startingBalance.value
            )
        )
    }
}
