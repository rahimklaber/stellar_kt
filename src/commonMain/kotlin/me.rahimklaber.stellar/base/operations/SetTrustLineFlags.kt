package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.Asset
import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToAccountIDXDR
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.SetTrustLineFlagsOp

data class SetTrustLineFlags(
    override val sourceAccount: String? = null,
    val trustor: String,
    val asset: Asset,
    val clearFlags: UInt, //TrustLineFlags
    val setFlags: UInt, //TrustLineFlags
): Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        val source = sourceAccount?.let {
            StrKey.encodeToMuxedAccountXDR(it)
        }
        return me.rahimklaber.stellar.base.xdr.Operation.SetTrustlineFlags(
            sourceAccount = source,
            SetTrustLineFlagsOp(
                trustor = StrKey.encodeToAccountIDXDR(trustor),
                asset = asset.toXdr(),
                clearFlags = clearFlags,
                setFlags = setFlags
            )
        )
    }
}
