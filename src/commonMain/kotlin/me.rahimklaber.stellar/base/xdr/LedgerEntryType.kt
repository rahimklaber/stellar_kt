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
enum class LedgerEntryType(val value: Int) : XdrElement {
    ACCOUNT(0),
    TRUSTLINE(1),
    OFFER(2),
    DATA(3),
    CLAIMABLE_BALANCE(4),
    LIQUIDITY_POOL(5);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<LedgerEntryType>{
        override fun decode(stream: XdrStream): LedgerEntryType {
            return when(val value = stream.readInt()){
                0 -> ACCOUNT
                1 -> TRUSTLINE
                2 -> OFFER
                3 -> DATA
                4 -> CLAIMABLE_BALANCE
                5 -> LIQUIDITY_POOL
                else -> throw IllegalArgumentException("could not decode $value to LedgerEntryType")

            }
        }

    }
}