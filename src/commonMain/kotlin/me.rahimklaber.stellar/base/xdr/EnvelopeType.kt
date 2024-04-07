package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// // list of all envelope types used in the application
//// those are prefixes used when building signatures for
//// the respective envelopes
//enum EnvelopeType
//{
//    ENVELOPE_TYPE_TX_V0 = 0,
//    ENVELOPE_TYPE_SCP = 1,
//    ENVELOPE_TYPE_TX = 2,
//    ENVELOPE_TYPE_AUTH = 3,
//    ENVELOPE_TYPE_SCPVALUE = 4,
//    ENVELOPE_TYPE_TX_FEE_BUMP = 5,
//    ENVELOPE_TYPE_OP_ID = 6,
//    ENVELOPE_TYPE_POOL_REVOKE_OP_ID = 7,
//    ENVELOPE_TYPE_CONTRACT_ID = 8,
//    ENVELOPE_TYPE_SOROBAN_AUTHORIZATION = 9
//};
///////////////////////////////////////////////////////////////////////////
enum class EnvelopeType(val value: Int) : XdrElement {
    ENVELOPE_TYPE_TX_V0(0),
    ENVELOPE_TYPE_SCP(1),
    ENVELOPE_TYPE_TX(2),
    ENVELOPE_TYPE_AUTH(3),
    ENVELOPE_TYPE_SCPVALUE(4),
    ENVELOPE_TYPE_TX_FEE_BUMP(5),
    ENVELOPE_TYPE_OP_ID(6),
    ENVELOPE_TYPE_POOL_REVOKE_OP_ID(7),
    ENVELOPE_TYPE_CONTRACT_ID(8),
    ENVELOPE_TYPE_SOROBAN_AUTHORIZATION(9)
    ;

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<EnvelopeType>{
        override fun decode(stream: XdrStream): EnvelopeType {
            val value = stream.readInt()

            return entries.find { it.value == value } ?: throw IllegalArgumentException("Could not decode EnvelopeType for value: $value")
        }

    }
}