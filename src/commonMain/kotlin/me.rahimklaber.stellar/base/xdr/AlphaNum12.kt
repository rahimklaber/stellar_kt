// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * AlphaNum12's original definition in the XDR file is:
 * ```
 * struct AlphaNum12
{
AssetCode12 assetCode;
AccountID issuer;
};
 * ```
 */
data class AlphaNum12(
    val assetCode: AssetCode12,
    val issuer: AccountID,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        assetCode.encode(stream)
        issuer.encode(stream)
    }

    companion object : XdrElementDecoder<AlphaNum12> {
        override fun decode(stream: XdrInputStream): AlphaNum12 {
            val assetCode = AssetCode12.decode(stream)
            val issuer = AccountID.decode(stream)
            return AlphaNum12(
                assetCode,
                issuer,
            )
        }
    }
}
