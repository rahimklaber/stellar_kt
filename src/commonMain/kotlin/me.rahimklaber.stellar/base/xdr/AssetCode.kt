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
sealed class AssetCode : XdrElement{
    abstract val bytes: ByteArray

    override fun encode(stream: XdrStream) {
        stream.writeBytes(bytes)
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AssetCode4

        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }

    //typedef opaque AssetCode4[4];
    data class AssetCode4(
        override val bytes: ByteArray
    ) : AssetCode() {
        init {
            require(bytes.size <= 4)
        }

        companion object : XdrElementDecoder<AssetCode4>{
            override fun decode(stream: XdrStream): AssetCode4 {
                return AssetCode4(stream.readBytes(4))
            }

        }
    }
    //typedef opaque AssetCode12[12];
    data class AssetCode12(
        override val bytes: ByteArray
    ) : AssetCode() {
        init {
            require(bytes.size == 12)
        }

        companion object : XdrElementDecoder<AssetCode12>{
            override fun decode(stream: XdrStream): AssetCode12 {
                return AssetCode12(stream.readBytes(12))
            }

        }

    }

}
