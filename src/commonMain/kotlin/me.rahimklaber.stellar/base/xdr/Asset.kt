package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union Asset switch (AssetType type)
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
//    // add other asset types here in the future
//};
///////////////////////////////////////////////////////////////////////////
sealed class Asset(
    val type: AssetType,
) : XdrElement{
    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    data object Native : Asset(AssetType.ASSET_TYPE_NATIVE)

    sealed class AlphaNum(type: AssetType): Asset(type)

    data class AlphaNum4(val alphaNum4: me.rahimklaber.stellar.base.xdr.AlphaNum4) : AlphaNum(AssetType.ASSET_TYPE_CREDIT_ALPHANUM4){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            alphaNum4.encode(stream)
        }
    }

    data class AlphaNum12(val alphaNum12: me.rahimklaber.stellar.base.xdr.AlphaNum12) : AlphaNum(AssetType.ASSET_TYPE_CREDIT_ALPHANUM12){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            alphaNum12.encode(stream)
        }
    }

    companion object : XdrElementDecoder<Asset>{
        override fun decode(stream: XdrStream): Asset {
            return when(AssetType.decode(stream)){
                AssetType.ASSET_TYPE_NATIVE -> Native
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM4 -> {
                    AlphaNum4(me.rahimklaber.stellar.base.xdr.AlphaNum4.decode(stream))
                }
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM12 -> {
                    AlphaNum12(me.rahimklaber.stellar.base.xdr.AlphaNum12.decode(stream))
                }
                // TODO: should I handle pool shares?
                else -> throw IllegalArgumentException("cannot decode bytes as Asset")
            }
        }

    }
}