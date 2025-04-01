package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.xdr.CreateAccountOp

data class CreateAccount(
    val destination: String,
    val startingBalance : TokenAmount,
    override val sourceAccount: String? = null,
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation(
            sourceAccount =  sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.CreateAccount(
                CreateAccountOp(
                    destination = StrKey.encodeToAccountIDXDR(destination),
                    startingBalance = startingBalance.value
                )
            )
        )
    }
}
