package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum ClaimableBalanceFlags
//{
//    // If set, the issuer account of the asset held by the claimable balance may
//    // clawback the claimable balance
//    CLAIMABLE_BALANCE_CLAWBACK_ENABLED_FLAG = 0x1
//};
///////////////////////////////////////////////////////////////////////////
enum class ClaimableBalanceFlags(val value: Int): XdrElement {
    CLAIMABLE_BALANCE_CLAWBACK_ENABLED_FLAG(0x1);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<ClaimableBalanceFlags>{
        override fun decode(stream: XdrStream): ClaimableBalanceFlags {
            return when(val value = stream.readInt()){
                CLAIMABLE_BALANCE_CLAWBACK_ENABLED_FLAG.value -> ClaimableBalanceFlags.CLAIMABLE_BALANCE_CLAWBACK_ENABLED_FLAG
                else -> throw IllegalArgumentException("Could not decode for value: $value")
            }
        }
    }

}