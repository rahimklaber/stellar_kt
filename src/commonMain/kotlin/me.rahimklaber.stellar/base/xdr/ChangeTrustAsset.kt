package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union ChangeTrustAsset switch (AssetType type)
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
//    LiquidityPoolParameters liquidityPool;
//
//    // add other asset types here in the future
//};
///////////////////////////////////////////////////////////////////////////
sealed class ChangeTrustAsset(val type: AssetType): XdrElement{

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    data class AlphaNum4(val alphaNum4: me.rahimklaber.stellar.base.xdr.AlphaNum4): ChangeTrustAsset(AssetType.ASSET_TYPE_CREDIT_ALPHANUM4){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            alphaNum4.encode(stream)
        }
    }
    data class AlphaNum12(val alphaNum12: me.rahimklaber.stellar.base.xdr.AlphaNum12): ChangeTrustAsset(AssetType.ASSET_TYPE_CREDIT_ALPHANUM12){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            alphaNum12.encode(stream)
        }
    }
    data class PoolShare(val liquidityPool: LiquidityPoolParameters): ChangeTrustAsset(AssetType.ASSET_TYPE_POOL_SHARE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            liquidityPool.encode(stream)
        }
    }

    companion object: XdrElementDecoder<ChangeTrustAsset> {
        override fun decode(stream: XdrStream): ChangeTrustAsset {
            return when(val type = AssetType.decode(stream)){
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM4 -> {
                    AlphaNum4(me.rahimklaber.stellar.base.xdr.AlphaNum4.decode(stream))
                }
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM12 -> {
                    AlphaNum12(me.rahimklaber.stellar.base.xdr.AlphaNum12.decode(stream))
                }
                AssetType.ASSET_TYPE_POOL_SHARE -> {
                    PoolShare(LiquidityPoolParameters.decode(stream))
                }
                else -> throw IllegalArgumentException("Could not decode ChangeTrustAsset for type: $type")
            }
        }
    }
}
