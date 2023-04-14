package me.rahimklaber.stellar.base.xdr

enum class EnvelopeType(val value: Int) : XdrElement {
    ENVELOPE_TYPE_TX_V0(0),
    ENVELOPE_TYPE_SCP(1),
    ENVELOPE_TYPE_TX(2),
    ENVELOPE_TYPE_AUTH(3),
    ENVELOPE_TYPE_SCPVALUE(4),
    ENVELOPE_TYPE_TX_FEE_BUMP(5),
    ENVELOPE_TYPE_OP_ID(6),
    ENVELOPE_TYPE_POOL_REVOKE_OP_ID(7);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<EnvelopeType>{
        override fun decode(stream: XdrStream): EnvelopeType {
            return when(val value= stream.readInt()){
                ENVELOPE_TYPE_TX_V0.value -> ENVELOPE_TYPE_TX_V0
                ENVELOPE_TYPE_SCP.value -> ENVELOPE_TYPE_SCP
                ENVELOPE_TYPE_TX.value -> ENVELOPE_TYPE_TX
                ENVELOPE_TYPE_AUTH.value -> ENVELOPE_TYPE_AUTH
                ENVELOPE_TYPE_SCPVALUE.value -> ENVELOPE_TYPE_SCPVALUE
                ENVELOPE_TYPE_TX_FEE_BUMP.value -> ENVELOPE_TYPE_TX_FEE_BUMP
                ENVELOPE_TYPE_OP_ID.value -> ENVELOPE_TYPE_OP_ID
                ENVELOPE_TYPE_POOL_REVOKE_OP_ID.value -> ENVELOPE_TYPE_POOL_REVOKE_OP_ID
                else -> throw IllegalArgumentException("Could not decode EnvelopeType for value: $value")
            }
        }

    }
}