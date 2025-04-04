// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * AllowTrustOp's original definition in the XDR file is:
 * ```
 * struct AllowTrustOp
{
AccountID trustor;
AssetCode asset;

// One of 0, AUTHORIZED_FLAG, or AUTHORIZED_TO_MAINTAIN_LIABILITIES_FLAG
uint32 authorize;
};
 * ```
 */
data class AllowTrustOp(
    val trustor: AccountID,
    val asset: AssetCode,
    val authorize: Uint32,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        trustor.encode(stream)
        asset.encode(stream)
        authorize.encode(stream)
    }

    companion object : XdrElementDecoder<AllowTrustOp> {
        override fun decode(stream: XdrInputStream): AllowTrustOp {
            val trustor = AccountID.decode(stream)
            val asset = AssetCode.decode(stream)
            val authorize = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            return AllowTrustOp(
                trustor,
                asset,
                authorize,
            )
        }
    }
}
