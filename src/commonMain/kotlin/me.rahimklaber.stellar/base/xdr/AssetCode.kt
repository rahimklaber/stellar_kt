package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union AssetCode switch (AssetType type)
//{
//case ASSET_TYPE_CREDIT_ALPHANUM4:
//    AssetCode4 assetCode4;
//
//case ASSET_TYPE_CREDIT_ALPHANUM12:
//    AssetCode12 assetCode12;
//
//    // add other asset types here in the future
//};
///////////////////////////////////////////////////////////////////////////
sealed class AssetCode(val type: AssetType) : XdrElement {

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    companion object : XdrElementDecoder<AssetCode> {
        override fun decode(stream: XdrStream): AssetCode {
            return when (val type = AssetType.decode(stream)) {
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM4 -> {
                    val assetCode4 = me.rahimklaber.stellar.base.xdr.AssetCode4.decode(stream)
                    return AssetCode4(assetCode4)
                }
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM12 -> {
                    val assetCode12 = me.rahimklaber.stellar.base.xdr.AssetCode12.decode(stream)
                    return AssetCode12(assetCode12)
                }
                else -> throw IllegalArgumentException("Could not decode AssetCode for type: $type")
            }
        }

    }

    data class AssetCode4(
        val assetCode4: me.rahimklaber.stellar.base.xdr.AssetCode4
    ) : AssetCode(AssetType.ASSET_TYPE_CREDIT_ALPHANUM4) {

        override fun encode(stream: XdrStream) {
            super.encode(stream)
            assetCode4.encode(stream)
        }
    }

    data class AssetCode12(
        val assetCode12: me.rahimklaber.stellar.base.xdr.AssetCode12
    ) : AssetCode(AssetType.ASSET_TYPE_CREDIT_ALPHANUM12) {

        override fun encode(stream: XdrStream) {
            super.encode(stream)
            assetCode12.encode(stream)
        }

    }

    }


