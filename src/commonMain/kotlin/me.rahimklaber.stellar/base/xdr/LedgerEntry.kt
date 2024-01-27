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
//    case CONTRACT_DATA:
//        ContractDataEntry contractData;
//    case CONTRACT_CODE:
//        ContractCodeEntry contractCode;
//    case CONFIG_SETTING:
//        ConfigSettingEntry configSetting;
//    case TTL:
//        TTLEntry ttl;
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

    abstract val extensionV1: LedgerEntryExtensionV1?

    data class LedgerEntryAccount(
        override val lastModifiedLedgerSeq: UInt, val account: AccountEntry,
        override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.ACCOUNT)

    data class LedgerEntryTrustline(
        override val lastModifiedLedgerSeq: UInt,
        val trustLine: TrustLineEntry,
        override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.TRUSTLINE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            trustLine.encode(stream)
            extensionV1.encodeNullable(stream)
        }
    }

    data class LedgerEntryOffer(
        override val lastModifiedLedgerSeq: UInt,
        val offer: OfferEntry,
        override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.OFFER){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            offer.encode(stream)
            extensionV1.encodeNullable(stream)
        }
    }

    data class LedgerEntryData(
        override val lastModifiedLedgerSeq: UInt,
        val data: DataEntry,
        override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.DATA){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            data.encode(stream)
            extensionV1.encodeNullable(stream)
        }
    }

    data class LedgerEntryClaimableBalance(
        override val lastModifiedLedgerSeq: UInt,
        val claimableBalance: ClaimableBalanceEntry,
        override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.CLAIMABLE_BALANCE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            claimableBalance.encode(stream)
            extensionV1.encodeNullable(stream)
        }
    }

    data class LedgerEntryLiquidityPool(
        override val lastModifiedLedgerSeq: UInt,
        val liquidityPool: LiquidityPoolEntry,
        override val extensionV1: LedgerEntryExtensionV1?
    ) : LedgerEntry(LedgerEntryType.LIQUIDITY_POOL){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            liquidityPool.encode(stream)
            extensionV1.encodeNullable(stream)
        }
    }

    data class ContractData(
        override val lastModifiedLedgerSeq: UInt,
        val contractData: ContractDataEntry,
        override val extensionV1: LedgerEntryExtensionV1?
    ): LedgerEntry(LedgerEntryType.CONTRACT_DATA){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            contractData.encode(stream)
            extensionV1.encodeNullable(stream)
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
                    LedgerEntryAccount(lastModifiedLedgerSeq, account, extensionV1)
                }
                LedgerEntryType.TRUSTLINE -> {
                    val trustline = TrustLineEntry.decode(stream)
                    val discriminant = stream.readInt()
                    val extensionV1 = if (discriminant == 1){
                        LedgerEntryExtensionV1.decode(stream)
                    }else{
                        null
                    }
                    LedgerEntryTrustline(lastModifiedLedgerSeq, trustline, extensionV1)
                }
                LedgerEntryType.OFFER -> {
                    val offer = OfferEntry.decode(stream)
                    val discriminant = stream.readInt()
                    val extensionV1 = if (discriminant == 1){
                        LedgerEntryExtensionV1.decode(stream)
                    }else{
                        null
                    }
                    LedgerEntryOffer(lastModifiedLedgerSeq, offer, extensionV1)
                }
                LedgerEntryType.DATA ->  {
                    val data = DataEntry.decode(stream)
                    val discriminant = stream.readInt()
                    val extensionV1 = if (discriminant == 1){
                        LedgerEntryExtensionV1.decode(stream)
                    }else{
                        null
                    }
                    LedgerEntryData(lastModifiedLedgerSeq, data, extensionV1)
                }
                LedgerEntryType.CLAIMABLE_BALANCE -> {
                    val claimableBalance = ClaimableBalanceEntry.decode(stream)
                    val discriminant = stream.readInt()
                    val extensionV1 = if (discriminant == 1){
                        LedgerEntryExtensionV1.decode(stream)
                    }else{
                        null
                    }
                    LedgerEntryClaimableBalance(lastModifiedLedgerSeq, claimableBalance, extensionV1)
                }
                LedgerEntryType.LIQUIDITY_POOL -> {
                    val liquidityPool = LiquidityPoolEntry.decode(stream)
                    val discriminant = stream.readInt()
                    val extensionV1 = if (discriminant == 1){
                        LedgerEntryExtensionV1.decode(stream)
                    }else{
                        null
                    }
                    LedgerEntryLiquidityPool(lastModifiedLedgerSeq, liquidityPool, extensionV1)
                }

                LedgerEntryType.CONTRACT_DATA -> {
                    return ContractData(
                        lastModifiedLedgerSeq,
                        ContractDataEntry.decode(stream),
                        LedgerEntryExtensionV1.decodeNullable(stream)
                    )
                }
                LedgerEntryType.CONTRACT_CODE -> TODO()
                LedgerEntryType.CONFIG_SETTING -> TODO()
                LedgerEntryType.TTL -> TODO()
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