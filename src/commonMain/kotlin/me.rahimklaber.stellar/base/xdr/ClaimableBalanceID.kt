// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ClaimableBalanceID's original definition in the XDR file is:
 * ```
 * union ClaimableBalanceID switch (ClaimableBalanceIDType type)
{
case CLAIMABLE_BALANCE_ID_TYPE_V0:
Hash v0;
};
 * ```
 */
sealed class ClaimableBalanceID(val type: ClaimableBalanceIDType) : XdrElement {
    fun v0OrNull(): V0? = if (this is V0) this else null
    data class V0(
        val v0: Hash,
    ) : ClaimableBalanceID(ClaimableBalanceIDType.CLAIMABLE_BALANCE_ID_TYPE_V0) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            v0.encode(stream)
        }
    }

    companion object : XdrElementDecoder<ClaimableBalanceID> {
        override fun decode(stream: XdrInputStream): ClaimableBalanceID {
            return when (val type = ClaimableBalanceIDType.decode(stream)) {
                ClaimableBalanceIDType.CLAIMABLE_BALANCE_ID_TYPE_V0 -> {
                    val v0 = Hash.decode(stream)
                    V0(v0)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
