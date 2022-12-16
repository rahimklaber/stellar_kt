package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum LedgerEntryType
//{
//    ACCOUNT = 0,
//    TRUSTLINE = 1,
//    OFFER = 2,
//    DATA = 3,
//    CLAIMABLE_BALANCE = 4,
//    LIQUIDITY_POOL = 5
//};
///////////////////////////////////////////////////////////////////////////
enum class LedgerEntryType(val value: Int) {
    ACCOUNT(0),
    TRUSTLINE(1),
    OFFER(2),
    DATA(3),
    CLAIMABLE_BALANCE(4),
    LIQUIDITY_POOL(5)
}