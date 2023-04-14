package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct AlphaNum4
//{
//    AssetCode4 assetCode;
//    AccountID issuer;
//};
///////////////////////////////////////////////////////////////////////////
data class AlphaNum4(
    val assetCode4: AssetCode4,
    val issuer: AccountID
): XdrElement {
    override fun encode(stream: XdrStream) {
        assetCode4.encode(stream)
        issuer.encode(stream)
    }

    companion object: XdrElementDecoder<AlphaNum4> {
        override fun decode(stream: XdrStream): AlphaNum4 {
            val assetCode4 = AssetCode4.decode(stream)
            val issuer = AccountID.decode(stream)
            return AlphaNum4(assetCode4, issuer)
        }
    }

}