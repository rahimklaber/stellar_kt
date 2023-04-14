package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union ClaimableBalanceID switch (ClaimableBalanceIDType type)
//{
//case CLAIMABLE_BALANCE_ID_TYPE_V0:
//    Hash v0;
//};
///////////////////////////////////////////////////////////////////////////
sealed class ClaimableBalanceID(val type: ClaimableBalanceIDType) : XdrElement{
    data class ClaimableBalanceIDV0(val hash: Hash) : ClaimableBalanceID(ClaimableBalanceIDType.CLAIMABLE_BALANCE_ID_TYPE_V0){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            hash.encode(stream)
        }
    }

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    companion object: XdrElementDecoder<ClaimableBalanceID>{
        override fun decode(stream: XdrStream): ClaimableBalanceID {
            return when(val discriminant = ClaimableBalanceIDType.decode(stream)){
                ClaimableBalanceIDType.CLAIMABLE_BALANCE_ID_TYPE_V0 -> {
                    ClaimableBalanceIDV0(Hash.decode(stream))
                }
            }
        }

    }
}