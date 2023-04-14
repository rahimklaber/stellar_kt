package me.rahimklaber.stellar.base.xdr

enum class LiquidityPoolType(val value: Int): XdrElement {
    LIQUIDITY_POOL_CONSTANT_PRODUCT(0);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<LiquidityPoolType> {
        override fun decode(stream: XdrStream): LiquidityPoolType {
            return when(val value = stream.readInt()){
                LIQUIDITY_POOL_CONSTANT_PRODUCT.value -> LIQUIDITY_POOL_CONSTANT_PRODUCT
                else -> throw IllegalArgumentException("Could not decode LiquidityPoolType for value: $value")
            }

        }
    }
}