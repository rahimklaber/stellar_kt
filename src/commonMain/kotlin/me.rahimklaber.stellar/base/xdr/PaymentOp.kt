package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Payment
//    Send an amount in specified asset to a destination account.
//    Threshold: med
//    Result: PaymentResult
//*/
//struct PaymentOp
//{
//    MuxedAccount destination; // recipient of the payment
//    Asset asset;              // what they end up with
//    int64 amount;             // amount they end up with
//};
///////////////////////////////////////////////////////////////////////////
data class PaymentOp(
    val destination: MuxedAccount,
    val asset: Asset,
    val amount: Long,
): XdrElement {
    override fun encode(stream: XdrStream) {
        destination.encode(stream)
        asset.encode(stream)
        stream.writeLong(amount)
    }

    companion object: XdrElementDecoder<PaymentOp>{
        override fun decode(stream: XdrStream): PaymentOp {
            val destination = MuxedAccount.decode(stream)
            val asset = Asset.decode(stream)
            val amount = stream.readLong()
            return PaymentOp(destination, asset, amount)
        }

    }
}