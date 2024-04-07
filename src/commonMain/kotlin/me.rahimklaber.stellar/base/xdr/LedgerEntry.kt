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
data class LedgerEntry(
    val lastModifiedLedgerSeq: UInt,
    val data: LedgerEntryData,
    val extensionV1: LedgerEntryExtensionV1? // this not actually nullable but the switch
): XdrElement {



    override fun encode(stream: XdrStream) {
        stream.writeInt(lastModifiedLedgerSeq.toInt())
        data.encode(stream)
        extensionV1.encodeNullable(stream)
    }

    companion object: XdrElementDecoder<LedgerEntry> {
        override fun decode(stream: XdrStream): LedgerEntry {
            val lastModifiedLedgerSeq= stream.readInt().toUInt()

            return LedgerEntry(lastModifiedLedgerSeq, LedgerEntryData.decode(stream), LedgerEntryExtensionV1.decodeNullable(stream))
        }
    }

}

sealed class LedgerEntryData(
    val ledgerEntryType: LedgerEntryType
): XdrElement{

    override fun encode(stream: XdrStream) {
        ledgerEntryType.encode(stream)
    }

    data class Account(
        val account: AccountEntry,
    ) : LedgerEntryData(LedgerEntryType.ACCOUNT)

    data class Trustline(
        val trustLine: TrustLineEntry,
    ) : LedgerEntryData(LedgerEntryType.TRUSTLINE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            trustLine.encode(stream)
        }
    }

    data class Offer(
        val offer: OfferEntry,
    ) : LedgerEntryData(LedgerEntryType.OFFER){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            offer.encode(stream)
        }
    }

    data class Data(
        val data: DataEntry,
    ) : LedgerEntryData(LedgerEntryType.DATA){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            data.encode(stream)
        }
    }

    data class ClaimableBalance(
        val claimableBalance: ClaimableBalanceEntry,
    ) : LedgerEntryData(LedgerEntryType.CLAIMABLE_BALANCE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            claimableBalance.encode(stream)
        }
    }

    data class LiquidityPool(
        val liquidityPool: LiquidityPoolEntry,
    ) : LedgerEntryData(LedgerEntryType.LIQUIDITY_POOL){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            liquidityPool.encode(stream)
        }
    }

    data class ContractData(
        val contractData: ContractDataEntry,
    ): LedgerEntryData(LedgerEntryType.CONTRACT_DATA){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            contractData.encode(stream)
        }
    }

    data class ContractCode(val contractCodeEntry: ContractCodeEntry): LedgerEntryData(LedgerEntryType.CONTRACT_CODE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            contractCodeEntry.encode(stream)
        }
    }

    data class TTL(
        val ttl: TTLEntry,
    ): LedgerEntryData(LedgerEntryType.TTL){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            ttl.encode(stream)
        }
    }

    companion object: XdrElementDecoder<LedgerEntryData> {
        override fun decode(stream: XdrStream): LedgerEntryData {
            return when(LedgerEntryType.decode(stream)){
                LedgerEntryType.ACCOUNT -> Account(AccountEntry.decode(stream))
                LedgerEntryType.TRUSTLINE -> Trustline(TrustLineEntry.decode(stream))
                LedgerEntryType.OFFER -> Offer(OfferEntry.decode(stream))
                LedgerEntryType.DATA -> Data(DataEntry.decode(stream))
                LedgerEntryType.CLAIMABLE_BALANCE -> ClaimableBalance(ClaimableBalanceEntry.decode(stream))
                LedgerEntryType.LIQUIDITY_POOL -> LiquidityPool(LiquidityPoolEntry.decode(stream))
                LedgerEntryType.CONTRACT_DATA -> ContractData(ContractDataEntry.decode(stream))
                LedgerEntryType.CONTRACT_CODE -> ContractCode(ContractCodeEntry.decode(stream))
                LedgerEntryType.CONFIG_SETTING -> TODO()
                LedgerEntryType.TTL -> TTL(TTLEntry.decode(stream))
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