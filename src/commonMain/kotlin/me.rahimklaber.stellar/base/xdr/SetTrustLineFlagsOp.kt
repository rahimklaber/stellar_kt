// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SetTrustLineFlagsOp's original definition in the XDR file is:
 * ```
 * struct SetTrustLineFlagsOp
{
AccountID trustor;
Asset asset;

uint32 clearFlags; // which flags to clear
uint32 setFlags;   // which flags to set
};
 * ```
 */
data class SetTrustLineFlagsOp(
    val trustor: AccountID,
    val asset: Asset,
    val clearFlags: Uint32,
    val setFlags: Uint32,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        trustor.encode(stream)
        asset.encode(stream)
        clearFlags.encode(stream)
        setFlags.encode(stream)
    }

    companion object : XdrElementDecoder<SetTrustLineFlagsOp> {
        override fun decode(stream: XdrInputStream): SetTrustLineFlagsOp {
            val trustor = AccountID.decode(stream)
            val asset = Asset.decode(stream)
            val clearFlags = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val setFlags = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            return SetTrustLineFlagsOp(
                trustor,
                asset,
                clearFlags,
                setFlags,
            )
        }
    }
}
