package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union Claimant switch (ClaimantType type)
//{
//case CLAIMANT_TYPE_V0:
//    struct
//    {
//        AccountID destination;    // The account that can use this condition
//        ClaimPredicate predicate; // Claimable if predicate is true
//    } v0;
//};
///////////////////////////////////////////////////////////////////////////
sealed class Claimant(val type: ClaimantType): XdrElement{
    data class ClaimantV0(val destination: AccountID, val predicate: ClaimPredicate) : Claimant(ClaimantType.CLAIMANT_TYPE_V0){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            destination.encode(stream)
            predicate.encode(stream)
        }
    }

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    companion object: XdrElementDecoder<Claimant>{
        override fun decode(stream: XdrStream): Claimant {
            return when(val discriminant = ClaimantType.decode(stream)){
                ClaimantType.CLAIMANT_TYPE_V0 -> {
                    val destination = AccountID.decode(stream)
                    val predicate = ClaimPredicate.decode(stream)
                    ClaimantV0(destination,predicate)
                }
                else -> throw IllegalArgumentException("Cannot decode Claimant for type $discriminant")
            }
        }

    }
}