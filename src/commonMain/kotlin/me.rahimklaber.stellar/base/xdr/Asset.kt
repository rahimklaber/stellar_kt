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

    object Native : Asset(AssetType.ASSET_TYPE_NATIVE)

    sealed class AlphaNum(type: AssetType) :
        Asset(type) {
        abstract val assetCode: AssetCode
        abstract val issuer: AccountID

        override fun encode(stream: XdrStream) {
            super.encode(stream)
            assetCode.encode(stream)
            issuer.encode(stream)
        }

        data class AlphaNum4(
            override val assetCode: AssetCode.AssetCode4,
            override val issuer: AccountID,
        ) : AlphaNum(AssetType.ASSET_TYPE_CREDIT_ALPHANUM4)

        data class AlphaNum12(
            override val assetCode: AssetCode.AssetCode12,
            override val issuer: AccountID,
        ) : AlphaNum(AssetType.ASSET_TYPE_CREDIT_ALPHANUM12)
    }

    companion object : XdrElementDecoder<Asset>{
        override fun decode(stream: XdrStream): Asset {
            return when(AssetType.decode(stream)){
                AssetType.ASSET_TYPE_NATIVE -> Native
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM4 -> {
                    val code = AssetCode.AssetCode4.decode(stream)
                    val issuer = AccountID.decode(stream)
                    AlphaNum.AlphaNum4(code,issuer)
                }
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM12 -> {
                    val code = AssetCode.AssetCode12.decode(stream)
                    val issuer = AccountID.decode(stream)
                    AlphaNum.AlphaNum12(code,issuer)
                }
                // TODO: should I handle pool shares?
                else -> throw IllegalArgumentException("cannot decode bytes as Asset")
            }
        }

    }
}