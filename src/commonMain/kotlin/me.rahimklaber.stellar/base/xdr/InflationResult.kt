// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * InflationResult's original definition in the XDR file is:
 * ```
 * union InflationResult switch (InflationResultCode code)
{
case INFLATION_SUCCESS:
InflationPayout payouts<>;
case INFLATION_NOT_TIME:
void;
};
 * ```
 */
sealed class InflationResult(val type: InflationResultCode) : XdrElement {
    fun payoutsOrNull(): InflationSuccess? = if (this is InflationSuccess) this else null
    data class InflationSuccess(
        val payouts: List<InflationPayout>,
    ) : InflationResult(InflationResultCode.INFLATION_SUCCESS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            val payoutsSize = payouts.size
            stream.writeInt(payoutsSize)
            payouts.encodeXdrElements(stream)
        }
    }

    data object InflationNotTime : InflationResult(InflationResultCode.INFLATION_NOT_TIME) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    companion object : XdrElementDecoder<InflationResult> {
        override fun decode(stream: XdrInputStream): InflationResult {
            return when (val type = InflationResultCode.decode(stream)) {
                InflationResultCode.INFLATION_SUCCESS -> {
                    val payoutsSize = stream.readInt()
                    val payouts: List<InflationPayout> = decodeXdrElementsList(payoutsSize, stream, InflationPayout.decoder())
                    InflationSuccess(payouts)
                }

                InflationResultCode.INFLATION_NOT_TIME -> InflationNotTime
                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
