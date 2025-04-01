// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * LiquidityPoolParameters's original definition in the XDR file is:
 * ```
 * union LiquidityPoolParameters switch (LiquidityPoolType type)
{
case LIQUIDITY_POOL_CONSTANT_PRODUCT:
LiquidityPoolConstantProductParameters constantProduct;
};
 * ```
 */
sealed class LiquidityPoolParameters(val type: LiquidityPoolType) : XdrElement {
    fun constantProductOrNull(): ConstantProduct? = if (this is ConstantProduct) this else null
    data class ConstantProduct(
        val constantProduct: LiquidityPoolConstantProductParameters,
    ) : LiquidityPoolParameters(LiquidityPoolType.LIQUIDITY_POOL_CONSTANT_PRODUCT) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            constantProduct.encode(stream)
        }
    }

    companion object : XdrElementDecoder<LiquidityPoolParameters> {
        override fun decode(stream: XdrInputStream): LiquidityPoolParameters {
            return when (val type = LiquidityPoolType.decode(stream)) {
                LiquidityPoolType.LIQUIDITY_POOL_CONSTANT_PRODUCT -> {
                    val constantProduct = LiquidityPoolConstantProductParameters.decode(stream)
                    ConstantProduct(constantProduct)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
