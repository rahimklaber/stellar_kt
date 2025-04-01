// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * TransactionPhase's original definition in the XDR file is:
 * ```
 * union TransactionPhase switch (int v)
{
case 0:
TxSetComponent v0Components<>;
};
 * ```
 */
sealed class TransactionPhase(val type: Int) : XdrElement {
    fun v0ComponentsOrNull(): TransactionPhaseV0? = if (this is TransactionPhaseV0) this else null
    data class TransactionPhaseV0(
        val v0Components: List<TxSetComponent>,
    ) : TransactionPhase(0) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            val v0ComponentsSize = v0Components.size
            stream.writeInt(v0ComponentsSize)
            v0Components.encodeXdrElements(stream)
        }
    }

    companion object : XdrElementDecoder<TransactionPhase> {
        override fun decode(stream: XdrInputStream): TransactionPhase {
            return when (val type = Int.decode(stream)) {
                0 -> {
                    val v0ComponentsSize = stream.readInt()
                    val v0Components: List<TxSetComponent> = decodeXdrElementsList(v0ComponentsSize, stream, TxSetComponent.decoder())
                    TransactionPhaseV0(v0Components)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
