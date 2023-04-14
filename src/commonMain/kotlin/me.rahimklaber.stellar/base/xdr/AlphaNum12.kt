package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct AlphaNum12
//{
//    AssetCode12 assetCode;
//    AccountID issuer;
//};
///////////////////////////////////////////////////////////////////////////
data class AlphaNum12(
    val assetCode12: AssetCode12,
    val issuer: AccountID
): XdrElement {
    override fun encode(stream: XdrStream) {
        assetCode12.encode(stream)
        issuer.encode(stream)
    }

    companion object: XdrElementDecoder<AlphaNum12> {
        override fun decode(stream: XdrStream): AlphaNum12 {
            val assetCode12 = AssetCode12.decode(stream)
            val issuer = AccountID.decode(stream)
            return AlphaNum12(assetCode12, issuer)
        }
    }

}