package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum ClaimableBalanceIDType
//{
//    CLAIMABLE_BALANCE_ID_TYPE_V0 = 0
//};
///////////////////////////////////////////////////////////////////////////
enum class ClaimableBalanceIDType(val value: Int) : XdrElement{
    CLAIMABLE_BALANCE_ID_TYPE_V0(0);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<ClaimableBalanceIDType>{
        override fun decode(stream: XdrStream): ClaimableBalanceIDType {
            return when(val value = stream.readInt()){
                CLAIMABLE_BALANCE_ID_TYPE_V0.value -> ClaimableBalanceIDType.CLAIMABLE_BALANCE_ID_TYPE_V0
                else -> throw IllegalArgumentException("Could not decode ClaimableBalanceIDType for value: $value")
            }
        }

    }
}