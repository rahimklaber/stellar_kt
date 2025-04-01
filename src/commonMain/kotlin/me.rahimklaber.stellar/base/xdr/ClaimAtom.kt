// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ClaimAtom's original definition in the XDR file is:
 * ```
 * union ClaimAtom switch (ClaimAtomType type)
{
case CLAIM_ATOM_TYPE_V0:
ClaimOfferAtomV0 v0;
case CLAIM_ATOM_TYPE_ORDER_BOOK:
ClaimOfferAtom orderBook;
case CLAIM_ATOM_TYPE_LIQUIDITY_POOL:
ClaimLiquidityAtom liquidityPool;
};
 * ```
 */
sealed class ClaimAtom(val type: ClaimAtomType) : XdrElement {
    fun v0OrNull(): V0? = if (this is V0) this else null
    data class V0(
        val v0: ClaimOfferAtomV0,
    ) : ClaimAtom(ClaimAtomType.CLAIM_ATOM_TYPE_V0) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            v0.encode(stream)
        }
    }

    fun orderBookOrNull(): OrderBook? = if (this is OrderBook) this else null
    data class OrderBook(
        val orderBook: ClaimOfferAtom,
    ) : ClaimAtom(ClaimAtomType.CLAIM_ATOM_TYPE_ORDER_BOOK) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            orderBook.encode(stream)
        }
    }

    fun liquidityPoolOrNull(): LiquidityPool? = if (this is LiquidityPool) this else null
    data class LiquidityPool(
        val liquidityPool: ClaimLiquidityAtom,
    ) : ClaimAtom(ClaimAtomType.CLAIM_ATOM_TYPE_LIQUIDITY_POOL) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            liquidityPool.encode(stream)
        }
    }

    companion object : XdrElementDecoder<ClaimAtom> {
        override fun decode(stream: XdrInputStream): ClaimAtom {
            return when (val type = ClaimAtomType.decode(stream)) {
                ClaimAtomType.CLAIM_ATOM_TYPE_V0 -> {
                    val v0 = ClaimOfferAtomV0.decode(stream)
                    V0(v0)
                }

                ClaimAtomType.CLAIM_ATOM_TYPE_ORDER_BOOK -> {
                    val orderBook = ClaimOfferAtom.decode(stream)
                    OrderBook(orderBook)
                }

                ClaimAtomType.CLAIM_ATOM_TYPE_LIQUIDITY_POOL -> {
                    val liquidityPool = ClaimLiquidityAtom.decode(stream)
                    LiquidityPool(liquidityPool)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
