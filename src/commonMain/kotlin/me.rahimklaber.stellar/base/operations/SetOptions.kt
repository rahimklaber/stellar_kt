package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToAccountIDXDR
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.SetOptionsOp
import me.rahimklaber.stellar.base.xdr.Signer
import me.rahimklaber.stellar.base.xdr.String32
import me.rahimklaber.stellar.base.xdr.AccountFlags as AccountFlagsXdr

data class AccountFlags(
    val authRequired: Boolean = false,
    val authRevocable: Boolean = false,
    val authImmutable: Boolean = false,
    val authClawbackEnabled: Boolean = false,
){
    val packedValue by lazy {
        var value = 0u
        if(authRequired)
            value = value or AccountFlagsXdr.AUTH_REQUIRED_FLAG.value.toUInt()
        if (authRevocable)
            value = value or AccountFlagsXdr.AUTH_REVOCABLE_FLAG.value.toUInt()
        if (authImmutable)
            value = value or AccountFlagsXdr.AUTH_IMMUTABLE_FLAG.value.toUInt()
        if (authClawbackEnabled){
            value = value or AccountFlagsXdr.AUTH_CLAWBACK_ENABLED_FLAG.value.toUInt()
        }
        value

    }
}
data class SetOptions(
    override val sourceAccount: String? = null,
    val inflationDest: String? = null,
    val clearFlags: AccountFlags? = null,
    val setFlags: AccountFlags? = null,
    val masterWeight : UInt? = null,
    val lowThreshold : UInt? = null,
    val medThreshold : UInt? = null,
    val highThreshold: UInt? = null,
    val homeDomain : String? = null,
    val signer: Signer? = null
) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation.SetOptions(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            SetOptionsOp(
                inflationDest = inflationDest?.let { StrKey.encodeToAccountIDXDR(it) },
                clearFlags = clearFlags?.packedValue,
                setFlags = setFlags?.packedValue,
                masterWeight = masterWeight,
                lowThreshold = lowThreshold,
                medThreshold = medThreshold,
                highThreshold = highThreshold,
                homeDomain = homeDomain?.let { String32(it.encodeToByteArray()) },
                signer = signer

            )
        )
    }
}