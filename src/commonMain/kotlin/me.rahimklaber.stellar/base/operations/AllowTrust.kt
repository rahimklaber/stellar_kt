package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToAccountIDXDR
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.*
import me.rahimklaber.stellar.base.xdr.Operation

data class AllowTrust(
    val trustor: String,
    val assetCode: String,
    val authorizeFlag: UInt,/*TrustLineFlags*/
    override val sourceAccount: String? = null,
) : Operation {
    override fun toXdr(): Operation {
        return Operation(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            body = OperationBody.AllowTrust(
                AllowTrustOp(
                    trustor = StrKey.encodeToAccountIDXDR(trustor),
                    asset = when {
                        assetCode.length > 4 -> AssetCode.CreditAlphanum12(AssetCode12(assetCode.encodeToByteArray()))
                        else -> AssetCode.CreditAlphanum4(AssetCode4(assetCode.encodeToByteArray()))
                    },
                    authorize = authorizeFlag
                )
            )
        )
    }
}
