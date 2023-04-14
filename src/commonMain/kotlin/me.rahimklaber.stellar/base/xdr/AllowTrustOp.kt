package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Updates the "authorized" flag of an existing trust line
//   this is called by the issuer of the related asset.
//   note that authorize can only be set (and not cleared) if
//   the issuer account does not have the AUTH_REVOCABLE_FLAG set
//   Threshold: low
//   Result: AllowTrustResult
//*/
//struct AllowTrustOp
//{
//    AccountID trustor;
//    AssetCode asset;
//
//    // One of 0, AUTHORIZED_FLAG, or AUTHORIZED_TO_MAINTAIN_LIABILITIES_FLAG
//    uint32 authorize;
//};
///////////////////////////////////////////////////////////////////////////
data class AllowTrustOp(
    val trustor: AccountID,
    val asset: AssetCode,
    val authorize: UInt
): XdrElement {
    override fun encode(stream: XdrStream) {
        trustor.encode(stream)
        asset.encode(stream)
        stream.writeInt(authorize.toInt())
    }

    companion object: XdrElementDecoder<AllowTrustOp> {
        override fun decode(stream: XdrStream): AllowTrustOp {
            val trustor = AccountID.decode(stream)
            val asset = AssetCode.decode(stream)
            val authorize = stream.readInt().toUInt()
            return AllowTrustOp(trustor, asset, authorize)
        }
    }
}