package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToAccountIDXDR
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.*

data class AllowTrust(
    val trustor: String,
    val assetCode: String,
    val authorizeFlag: UInt,/*TrustLineFlags*/
    override val sourceAccount: String? = null,
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return Operation(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.AllowTrust(
                AllowTrustOp(
                    trustor = StrKey.encodeToAccountIDXDR(trustor),
                    asset = when{
                        assetCode.length > 4 -> AssetCode.CreditAlphanum12(AssetCode12(assetCode.encodeToByteArray()))
                        else -> AssetCode.CreditAlphanum4(AssetCode4(assetCode.encodeToByteArray()))
                    },
                    authorize = authorizeFlag
                )
            )
        )
    }
}
