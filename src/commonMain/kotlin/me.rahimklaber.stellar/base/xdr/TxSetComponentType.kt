package me.rahimklaber.stellar.base.xdr

public enum class TxSetComponentType(
    public val `value`: Int,
) : XdrElement {
    TXSET_COMP_TXS_MAYBE_DISCOUNTED_FEE(0),
    ;

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    public companion object : XdrElementDecoder<TxSetComponentType> {
        override fun decode(stream: XdrStream): TxSetComponentType {
            val value = stream.readInt()
            return entries.find { it.value == value } ?: xdrDecodeError("Could not find enum case")
        }
    }
}
