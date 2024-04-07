package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum LedgerEntryType
//{
//    ACCOUNT = 0,
//    TRUSTLINE = 1,
//    OFFER = 2,
//    DATA = 3,
//    CLAIMABLE_BALANCE = 4,
//    LIQUIDITY_POOL = 5,
//    CONTRACT_DATA = 6,
//    CONTRACT_CODE = 7,
//    CONFIG_SETTING = 8,
//    TTL = 9
//};
///////////////////////////////////////////////////////////////////////////
enum class LedgerEntryType(val value: Int) : XdrElement {
    ACCOUNT(0),
    TRUSTLINE(1),
    OFFER(2),
    DATA(3),
    CLAIMABLE_BALANCE(4),
    LIQUIDITY_POOL(5),
    CONTRACT_DATA(6),
    CONTRACT_CODE(7),
    CONFIG_SETTING(8),
    TTL(9);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<LedgerEntryType>{
        override fun decode(stream: XdrStream): LedgerEntryType {
            val value = stream.readInt()
            return entries.find { it.value == value }
                ?: throw IllegalArgumentException("could not decode $value to LedgerEntryType")
        }

    }
}