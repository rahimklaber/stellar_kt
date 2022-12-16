package me.rahimklaber.stellar.base.xdr

enum class AssetType(val value: Int) : XdrElement {
    ASSET_TYPE_NATIVE(0),
    ASSET_TYPE_CREDIT_ALPHANUM4(1),
    ASSET_TYPE_CREDIT_ALPHANUM12(2),
    ASSET_TYPE_POOL_SHARE(3);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object : XdrElementDecoder<AssetType>{
        override fun decode(stream: XdrStream): AssetType {
            return when(val value = stream.readInt()){
                0 -> ASSET_TYPE_NATIVE
                1 -> ASSET_TYPE_CREDIT_ALPHANUM4
                2 -> ASSET_TYPE_CREDIT_ALPHANUM12
                3 -> ASSET_TYPE_POOL_SHARE
                else -> throw IllegalArgumentException("cannot decode $value for AssetType")
            }
        }

    }
}