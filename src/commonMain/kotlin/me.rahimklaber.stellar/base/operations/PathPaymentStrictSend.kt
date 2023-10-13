package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.Asset
import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.TokenAmount
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.PathPaymentStrictSendOp

data class PathPaymentStrictSend(
    override val sourceAccount: String?,
    val sendAsset: Asset,
    val sendAmount: TokenAmount,
    val destination: String,
    val destAsset: Asset,
    val destMin: TokenAmount,
    val path: List<Asset>
): Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation.PathPaymentStrictSend(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            PathPaymentStrictSendOp(
                sendAsset = sendAsset.toXdr(),
                sendAmount = sendAmount.value,
                destination = StrKey.encodeToMuxedAccountXDR(destination),
                destAsset = destAsset.toXdr(),
                destMin = destMin.value,
                path = path.map(Asset::toXdr)
            )
        )
    }
}
