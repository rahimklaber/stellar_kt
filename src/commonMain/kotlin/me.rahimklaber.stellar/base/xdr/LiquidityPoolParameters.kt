package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union LiquidityPoolParameters switch (LiquidityPoolType type)
//{
//case LIQUIDITY_POOL_CONSTANT_PRODUCT:
//    LiquidityPoolConstantProductParameters constantProduct;
//};
///////////////////////////////////////////////////////////////////////////
sealed class LiquidityPoolParameters(val type: LiquidityPoolType) : XdrElement{
    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    companion object: XdrElementDecoder<LiquidityPoolParameters> {
        override fun decode(stream: XdrStream): LiquidityPoolParameters {
            return when(LiquidityPoolType.decode(stream)){
                LiquidityPoolType.LIQUIDITY_POOL_CONSTANT_PRODUCT -> {
                    ConstantProduct(LiquidityPoolConstantProductParameters.decode(stream))
                }
            }
        }
    }
    data class ConstantProduct(val constantProductParameters: LiquidityPoolConstantProductParameters) : LiquidityPoolParameters(LiquidityPoolType.LIQUIDITY_POOL_CONSTANT_PRODUCT){
        override fun encode(stream: XdrStream) {
            constantProductParameters.encode(stream)
        }
    }


}

