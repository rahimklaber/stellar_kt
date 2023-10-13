package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.xdr.PathPaymentStrictReceiveOp

data class PathPaymentStrictReceive(
    override val sourceAccount: String?,
    val sendAsset: Asset,
    val sendMax: TokenAmount,
    val destination: String,
    val destAsset: Asset,
    val destAmount: TokenAmount,
    val path: List<Asset>
): Operation{
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation.PathPaymentStrictReceive(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            pathPaymentStrictReceiveOp = PathPaymentStrictReceiveOp(
                sendAsset = sendAsset.toXdr(),
                sendMax = sendMax.value,
                destination = StrKey.encodeToMuxedAccountXDR(destination),
                destAsset = destAsset.toXdr(),
                destAmount = destAmount.value,
                path = path.map(Asset::toXdr)
            )
        )
    }
}
