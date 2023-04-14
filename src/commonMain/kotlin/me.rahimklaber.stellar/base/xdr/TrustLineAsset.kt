package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union TrustLineAsset switch (AssetType type)
//{
//case ASSET_TYPE_NATIVE: // Not credit
//    void;
//
//case ASSET_TYPE_CREDIT_ALPHANUM4:
//    AlphaNum4 alphaNum4;
//
//case ASSET_TYPE_CREDIT_ALPHANUM12:
//    AlphaNum12 alphaNum12;
//
//case ASSET_TYPE_POOL_SHARE:
//    PoolID liquidityPoolID;
//
//    // add other asset types here in the future
//};
///////////////////////////////////////////////////////////////////////////
sealed class TrustLineAsset(val assetType: AssetType) : XdrElement{
    data class AlphaNum4(val alphaNum4: me.rahimklaber.stellar.base.xdr.AlphaNum4): TrustLineAsset(AssetType.ASSET_TYPE_CREDIT_ALPHANUM4){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            alphaNum4.encode(stream)
        }
    }
    data class AlphaNum12(val alphaNum12: me.rahimklaber.stellar.base.xdr.AlphaNum12) : TrustLineAsset(AssetType.ASSET_TYPE_CREDIT_ALPHANUM12){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            alphaNum12.encode(stream)
        }
    }
    data class LiquidityPool(val poolID: PoolID): TrustLineAsset(AssetType.ASSET_TYPE_POOL_SHARE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            poolID.encode(stream)
        }
    }

    override fun encode(stream: XdrStream) {
        assetType.encode(stream)
    }

    companion object: XdrElementDecoder<TrustLineAsset>{
        override fun decode(stream: XdrStream): TrustLineAsset {
            return when(val type = AssetType.decode(stream)){
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM4 -> {
                    val asset = me.rahimklaber.stellar.base.xdr.AlphaNum4.decode(stream)
                    AlphaNum4(asset)
                }
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM12 -> {
                    val asset = me.rahimklaber.stellar.base.xdr.AlphaNum12.decode(stream)
                    AlphaNum12(asset)
                }
                AssetType.ASSET_TYPE_POOL_SHARE -> {
                    LiquidityPool(PoolID.decode(stream))
                }
                else -> throw IllegalArgumentException("Could not decode TrustLineAsset for type $type")
            }
        }

    }
}
