package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToAccountIDXDR
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.*

data class AllowTrust(
    override val sourceAccount: String? = null,
    val trustor: String,
    val assetCode: String,
    val authorizeFlag: UInt/*TrustLineFlags*/
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation.AllowTrust(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            AllowTrustOp(
                trustor = StrKey.encodeToAccountIDXDR(trustor),
                asset = when{
                    assetCode.length > 4 -> AssetCode.AssetCode12(AssetCode12(assetCode.encodeToByteArray()))
                    else -> AssetCode.AssetCode4(AssetCode4(assetCode.encodeToByteArray()))
                },
                authorize = authorizeFlag
            )
        )
    }
}
