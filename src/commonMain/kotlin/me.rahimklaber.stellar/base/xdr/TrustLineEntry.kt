package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct TrustLineEntryExtensionV2
//{
//    int32 liquidityPoolUseCount;
//
//    union switch (int v)
//    {
//    case 0:
//        void;
//    }
//    ext;
//};
//
//struct TrustLineEntry
//{
//    AccountID accountID;  // account this trustline belongs to
//    TrustLineAsset asset; // type of asset (with issuer)
//    int64 balance;        // how much of this asset the user has.
//                          // Asset defines the unit for this;
//
//    int64 limit;  // balance cannot be above this
//    uint32 flags; // see TrustLineFlags
//
//    // reserved for future use
//    union switch (int v)
//    {
//    case 0:
//        void;
//    case 1:
//        struct
//        {
//            Liabilities liabilities;
//
//            union switch (int v)
//            {
//            case 0:
//                void;
//            case 2:
//                TrustLineEntryExtensionV2 v2;
//            }
//            ext;
//        } v1;
//    }
//    ext;
//};
///////////////////////////////////////////////////////////////////////////
data class TrustLineEntry(
    val accountID: AccountID,
    val asset: TrustLineAsset,
    val balance: Long,
    val limit: Long,
    val flags: UInt, // xdr does not have this as trustlineflags?
    val discriminant: Int,
    val trustLineExtension: TrustLineExtension?
): XdrElement {
    override fun encode(stream: XdrStream) {
        accountID.encode(stream)
        asset.encode(stream)
        stream.writeLong(balance)
        stream.writeLong(limit)
        stream.writeInt(flags.toInt())
        stream.writeInt(discriminant)
        trustLineExtension?.encode(stream)
    }

    companion object: XdrElementDecoder<TrustLineEntry>{
        override fun decode(stream: XdrStream): TrustLineEntry {
            val accountID = AccountID.decode(stream)
            val asset = TrustLineAsset.decode(stream)
            val balance = stream.readLong()
            val limit = stream.readLong()
            val flags = stream.readInt().toUInt()
            val discriminant = stream.readInt()
            val trustLineExtension = when (discriminant) {
                0 -> {
                    null
                }
                1 -> {
                    TrustLineExtension.decode(stream)
                }

                else -> throw IllegalArgumentException("invalid discriminant: $discriminant")
            }
            return TrustLineEntry(
                accountID, asset, balance, limit, flags, discriminant, trustLineExtension
            )
        }

    }
}

// this is not actually is the xdr, but it makes it easier for me.
data class TrustLineExtension(
    val liabilities: Liabilities,
    val discriminant: Int,
    val trustLineExtensionV2: TrustLineExtensionV2?
) : XdrElement{
    override fun encode(stream: XdrStream) {
        liabilities.encode(stream)
        stream.writeInt(discriminant)
        trustLineExtensionV2.encode(stream)
    }

    companion object: XdrElementDecoder<TrustLineExtension>{
        override fun decode(stream: XdrStream): TrustLineExtension {
            val liabilities = Liabilities.decode(stream)
            val discriminant = stream.readInt()
            val trustLineExtensionV2 = when(discriminant){
                0 -> null
                2 -> TrustLineExtensionV2.decode(stream)
                else -> throw IllegalArgumentException("Invalid discriminant: $discriminant")
            }
            return TrustLineExtension(liabilities, discriminant, trustLineExtensionV2)
        }

    }

}

data class TrustLineExtensionV2(
    val liquidityPoolUseCount: Int,
    val discriminant: Int
): XdrElement{
    override fun encode(stream: XdrStream) {
        stream.writeInt(liquidityPoolUseCount)
        stream.writeInt(discriminant)
    }

    companion object: XdrElementDecoder<TrustLineExtensionV2>{
        override fun decode(stream: XdrStream): TrustLineExtensionV2 {
            val liquidityPoolUseCount = stream.readInt()
            val discriminant = stream.readInt()
            return TrustLineExtensionV2(liquidityPoolUseCount, discriminant)
        }

    }
}