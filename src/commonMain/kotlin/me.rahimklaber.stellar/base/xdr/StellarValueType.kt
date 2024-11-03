package me.rahimklaber.stellar.base.xdr

/**
 * enum StellarValueType
 * {
 *     STELLAR_VALUE_BASIC = 0,
 *     STELLAR_VALUE_SIGNED = 1
 * };
 */
enum class StellarValueType(
    val value: Int
): XdrElement {
    STELLAR_VALUE_BASIC(0),
    STELLAR_VALUE_SIGNED(1);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<StellarValueType> {
        override fun decode(stream: XdrStream): StellarValueType {
            val value = stream.readInt()

            return entries.find { it.value == value } ?: xdrDecodeError("Could not find ")
        }
    }
}
