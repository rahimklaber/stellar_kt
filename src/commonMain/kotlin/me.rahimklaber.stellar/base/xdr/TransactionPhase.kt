package me.rahimklaber.stellar.base.xdr

sealed class TransactionPhase(val discriminant: Int) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(discriminant)
    }

    data class TransactionPhaseV0(val v0Components: List<TxSetComponent>) : TransactionPhase(0) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            v0Components.encode(stream)
        }
    }

    companion object : XdrElementDecoder<TransactionPhase> {
        override fun decode(stream: XdrStream): TransactionPhase {
            return when (val type = stream.readInt()) {
                0 -> {
                    val v0Components = decodeXdrElementList(stream, TxSetComponent::decode)
                    TransactionPhaseV0(v0Components)
                }

                else -> xdrDecodeError("Could not decode TransactionPhase for $type")
            }
        }
    }
}
