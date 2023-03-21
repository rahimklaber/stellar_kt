package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum ClaimantType
//{
//    CLAIMANT_TYPE_V0 = 0
//};
///////////////////////////////////////////////////////////////////////////
enum class ClaimantType(val value: Int): XdrElement {
    CLAIMANT_TYPE_V0(0);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<ClaimantType>{
        override fun decode(stream: XdrStream): ClaimantType {
           return when(val value = stream.readInt()){
                CLAIMANT_TYPE_V0.value -> CLAIMANT_TYPE_V0
                else -> throw IllegalArgumentException("Could not decode ClaimantType for value: $value")
            }
        }

    }
}