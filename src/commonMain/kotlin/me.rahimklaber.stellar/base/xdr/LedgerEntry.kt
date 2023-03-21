package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct LedgerEntry
//{
//    uint32 lastModifiedLedgerSeq; // ledger the LedgerEntry was last changed
//
//    union switch (LedgerEntryType type)
//    {
//    case ACCOUNT:
//        AccountEntry account;
//    case TRUSTLINE:
//        TrustLineEntry trustLine;
//    case OFFER:
//        OfferEntry offer;
//    case DATA:
//        DataEntry data;
//    case CLAIMABLE_BALANCE:
//        ClaimableBalanceEntry claimableBalance;
//    case LIQUIDITY_POOL:
//        LiquidityPoolEntry liquidityPool;
//    }
//    data;
//
//    // reserved for future use
//    union switch (int v)
//    {
//    case 0:
//        void;
//    case 1:
//        LedgerEntryExtensionV1 v1;
//    }
//    ext;
//};
///////////////////////////////////////////////////////////////////////////
sealed class LedgerEntry(val type: LedgerEntryType): XdrElement {
    abstract val lastModifiedLedgerSeq: UInt

    // the entries would come here
    abstract val discriminant: Int
    abstract val extensionV1: LedgerEntryExtensionV1?

    data class LedgerEntryAccount(
        override val lastModifiedLedgerSeq: UInt, val account: AccountEntry,
        override val discriminant: Int, override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.ACCOUNT)

    data class LedgerEntryTrustline(
        override val lastModifiedLedgerSeq: UInt,
        val trustLine: TrustLineEntry,
        override val discriminant: Int,
        override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.TRUSTLINE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            trustLine.encode(stream)
            stream.writeInt(discriminant)
            extensionV1?.encode(stream)
        }
    }

    data class LedgerEntryOffer(
        override val lastModifiedLedgerSeq: UInt,
        val offer: OfferEntry,
        override val discriminant: Int,
        override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.OFFER){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            offer.encode(stream)
            stream.writeInt(discriminant)
            extensionV1?.encode(stream)
        }
    }

    data class LedgerEntryData(
        override val lastModifiedLedgerSeq: UInt,
        val data: DataEntry,
        override val discriminant: Int,
        override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.DATA){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            data.encode(stream)
            stream.writeInt(discriminant)
            extensionV1?.encode(stream)
        }
    }

    data class LedgerEntryClaimableBalance(
        override val lastModifiedLedgerSeq: UInt,
        val claimableBalance: ClaimableBalanceEntry,
        override val discriminant: Int,
        override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.CLAIMABLE_BALANCE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            claimableBalance.encode(stream)
            stream.writeInt(discriminant)
            extensionV1?.encode(stream)
        }
    }

    data class LedgerEntryLiquidityPool(
        override val lastModifiedLedgerSeq: UInt,
        val liquidityPool: LiquidityPoolEntry,
        override val discriminant: Int,
        override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.LIQUIDITY_POOL){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            liquidityPool.encode(stream)
            stream.writeInt(discriminant)
            extensionV1?.encode(stream)
        }
    }

    override fun encode(stream: XdrStream) {
        stream.writeInt(lastModifiedLedgerSeq.toInt())
        type.encode(stream)
    }

    companion object: XdrElementDecoder<LedgerEntry> {
        override fun decode(stream: XdrStream): LedgerEntry {
            val lastModifiedLedgerSeq= stream.readInt().toUInt()
            return when(val type= LedgerEntryType.decode(stream)){
                LedgerEntryType.ACCOUNT -> {
                    val account = AccountEntry.decode(stream)
                    val discriminant = stream.readInt()
                    val extensionV1 = if (discriminant == 1){
                        LedgerEntryExtensionV1.decode(stream)
                    }else{
                        null
                    }
                    LedgerEntryAccount(lastModifiedLedgerSeq, account, discriminant, extensionV1)
                }
                LedgerEntryType.TRUSTLINE -> {
                    val trustline = TrustLineEntry.decode(stream)
                    val discriminant = stream.readInt()
                    val extensionV1 = if (discriminant == 1){
                        LedgerEntryExtensionV1.decode(stream)
                    }else{
                        null
                    }
                    LedgerEntryTrustline(lastModifiedLedgerSeq, trustline, discriminant, extensionV1)
                }
                LedgerEntryType.OFFER -> {
                    val offer = OfferEntry.decode(stream)
                    val discriminant = stream.readInt()
                    val extensionV1 = if (discriminant == 1){
                        LedgerEntryExtensionV1.decode(stream)
                    }else{
                        null
                    }
                    LedgerEntryOffer(lastModifiedLedgerSeq, offer, discriminant, extensionV1)
                }
                LedgerEntryType.DATA ->  {
                    val data = DataEntry.decode(stream)
                    val discriminant = stream.readInt()
                    val extensionV1 = if (discriminant == 1){
                        LedgerEntryExtensionV1.decode(stream)
                    }else{
                        null
                    }
                    LedgerEntryData(lastModifiedLedgerSeq, data, discriminant, extensionV1)
                }
                LedgerEntryType.CLAIMABLE_BALANCE -> {
                    val claimableBalance = ClaimableBalanceEntry.decode(stream)
                    val discriminant = stream.readInt()
                    val extensionV1 = if (discriminant == 1){
                        LedgerEntryExtensionV1.decode(stream)
                    }else{
                        null
                    }
                    LedgerEntryClaimableBalance(lastModifiedLedgerSeq, claimableBalance, discriminant, extensionV1)
                }
                LedgerEntryType.LIQUIDITY_POOL -> {
                    val liquidityPool = LiquidityPoolEntry.decode(stream)
                    val discriminant = stream.readInt()
                    val extensionV1 = if (discriminant == 1){
                        LedgerEntryExtensionV1.decode(stream)
                    }else{
                        null
                    }
                    LedgerEntryLiquidityPool(lastModifiedLedgerSeq, liquidityPool, discriminant, extensionV1)
                }
                else -> throw  IllegalArgumentException("Could not decode LedgerEntry for type: $type")
            }
        }
    }

}


data class LedgerEntryExtensionV1(
    val sponsoringID: SponsorshipDescriptor,
    val discriminant: Int
) : XdrElement {
    override fun encode(stream: XdrStream) {
        sponsoringID.encode(stream)
        stream.writeInt(discriminant)
    }

    companion object : XdrElementDecoder<LedgerEntryExtensionV1> {
        override fun decode(stream: XdrStream): LedgerEntryExtensionV1 {
            val sponsoringID = SponsorshipDescriptor.decode(stream)
            val discriminant = stream.readInt()
            return LedgerEntryExtensionV1(sponsoringID, discriminant)
        }
    }
}