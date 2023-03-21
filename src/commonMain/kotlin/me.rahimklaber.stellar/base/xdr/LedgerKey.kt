package me.rahimklaber.stellar.base.xdr

import kotlin.math.acos

///////////////////////////////////////////////////////////////////////////
// union LedgerKey switch (LedgerEntryType type)
//{
//case ACCOUNT:
//    struct
//    {
//        AccountID accountID;
//    } account;
//
//case TRUSTLINE:
//    struct
//    {
//        AccountID accountID;
//        TrustLineAsset asset;
//    } trustLine;
//
//case OFFER:
//    struct
//    {
//        AccountID sellerID;
//        int64 offerID;
//    } offer;
//
//case DATA:
//    struct
//    {
//        AccountID accountID;
//        string64 dataName;
//    } data;
//
//case CLAIMABLE_BALANCE:
//    struct
//    {
//        ClaimableBalanceID balanceID;
//    } claimableBalance;
//
//case LIQUIDITY_POOL:
//    struct
//    {
//        PoolID liquidityPoolID;
//    } liquidityPool;
//};
///////////////////////////////////////////////////////////////////////////
sealed class LedgerKey(val type: LedgerEntryType): XdrElement{
    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    companion object: XdrElementDecoder<LedgerKey>{
        override fun decode(stream: XdrStream): LedgerKey {
            return when(val type = LedgerEntryType.decode(stream)){
                LedgerEntryType.ACCOUNT -> {
                    val accountID = AccountID.decode(stream)
                    LedgerKeyAccount(accountID)
                }
                LedgerEntryType.TRUSTLINE -> {
                    val accountID = AccountID.decode(stream)
                    val asset = TrustLineAsset.decode(stream)
                    LedgerKeyTrustline(accountID, asset)
                }
                LedgerEntryType.OFFER -> {
                    val sellerID = AccountID.decode(stream)
                    val offerID = stream.readLong()
                    LedgerKeyOffer(sellerID, offerID)
                }
                LedgerEntryType.DATA -> {
                    val accountID = AccountID.decode(stream)
                    val dataName = String64.decode(stream)
                    LedgerKeyData(accountID, dataName)
                }
                LedgerEntryType.CLAIMABLE_BALANCE -> {
                    val balanceID = ClaimableBalanceID.decode(stream)
                    LedgerKeyClaimableBalance(balanceID)
                }
                LedgerEntryType.LIQUIDITY_POOL -> {
                    val liquidityPoolID = PoolID.decode(stream)
                    LedgerKeyLiquidityPool(liquidityPoolID)
                }
                else -> throw IllegalArgumentException("Could not decode LedgerKey for type: $type")
            }
        }

    }
}


data class LedgerKeyAccount(val accountID: AccountID) : LedgerKey(LedgerEntryType.ACCOUNT){
    override fun encode(stream: XdrStream) {
        super.encode(stream)
        accountID.encode(stream)
    }
}
data class LedgerKeyTrustline(val accountID: AccountID, val asset: TrustLineAsset): LedgerKey(LedgerEntryType.TRUSTLINE){
    override fun encode(stream: XdrStream) {
        super.encode(stream)
        accountID.encode(stream)
        asset.encode(stream)
    }
}
data class LedgerKeyOffer(val sellerID: AccountID, val offerID: Long): LedgerKey(LedgerEntryType.OFFER){
    override fun encode(stream: XdrStream) {
        super.encode(stream)
        sellerID.encode(stream)
        stream.writeLong(offerID)
    }
}
data class LedgerKeyData(val accountID: AccountID, val dataName: String64): LedgerKey(LedgerEntryType.DATA){
    override fun encode(stream: XdrStream) {
        super.encode(stream)
        accountID.encode(stream)
        dataName.encode(stream)
    }
}
data class LedgerKeyClaimableBalance(val balanceID: ClaimableBalanceID): LedgerKey(LedgerEntryType.CLAIMABLE_BALANCE){
    override fun encode(stream: XdrStream) {
        super.encode(stream)
        balanceID.encode(stream)
    }
}
data class LedgerKeyLiquidityPool(val liquidityPoolID: PoolID): LedgerKey(LedgerEntryType.LIQUIDITY_POOL){
    override fun encode(stream: XdrStream) {
        super.encode(stream)
        liquidityPoolID.encode(stream)
    }
}