// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * LiquidityPoolEntry's original definition in the XDR file is:
 * ```
 * struct LiquidityPoolEntry
{
PoolID liquidityPoolID;

union switch (LiquidityPoolType type)
{
case LIQUIDITY_POOL_CONSTANT_PRODUCT:
struct
{
LiquidityPoolConstantProductParameters params;

int64 reserveA;        // amount of A in the pool
int64 reserveB;        // amount of B in the pool
int64 totalPoolShares; // total number of pool shares issued
int64 poolSharesTrustLineCount; // number of trust lines for the
// associated pool shares
} constantProduct;
}
body;
};
 * ```
 */
data class LiquidityPoolEntry(
    val liquidityPoolID: PoolID,
    val body: LiquidityPoolEntryBody,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        liquidityPoolID.encode(stream)
        body.encode(stream)
    }

    companion object : XdrElementDecoder<LiquidityPoolEntry> {
        override fun decode(stream: XdrInputStream): LiquidityPoolEntry {
            val liquidityPoolID = PoolID.decode(stream)
            val body = LiquidityPoolEntryBody.decode(stream)
            return LiquidityPoolEntry(
                liquidityPoolID,
                body,
            )
        }
    }

    /**
     * LiquidityPoolEntryBody's original definition in the XDR file is:
     * ```
     * union switch (LiquidityPoolType type)
    {
    case LIQUIDITY_POOL_CONSTANT_PRODUCT:
    struct
    {
    LiquidityPoolConstantProductParameters params;

    int64 reserveA;        // amount of A in the pool
    int64 reserveB;        // amount of B in the pool
    int64 totalPoolShares; // total number of pool shares issued
    int64 poolSharesTrustLineCount; // number of trust lines for the
    // associated pool shares
    } constantProduct;
    }
     * ```
     */
    sealed class LiquidityPoolEntryBody(val type: LiquidityPoolType) : XdrElement {
        fun constantProductOrNull(): ConstantProduct? = if (this is ConstantProduct) this else null
        data class ConstantProduct(
            val constantProduct: LiquidityPoolEntryConstantProduct,
        ) : LiquidityPoolEntryBody(LiquidityPoolType.LIQUIDITY_POOL_CONSTANT_PRODUCT) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                constantProduct.encode(stream)
            }
        }

        companion object : XdrElementDecoder<LiquidityPoolEntryBody> {
            override fun decode(stream: XdrInputStream): LiquidityPoolEntryBody {
                return when (val type = LiquidityPoolType.decode(stream)) {
                    LiquidityPoolType.LIQUIDITY_POOL_CONSTANT_PRODUCT -> {
                        val constantProduct = LiquidityPoolEntryConstantProduct.decode(stream)
                        ConstantProduct(constantProduct)
                    }

                    else -> throw IllegalArgumentException("unknown type: $type")
                }
            }
        }

        /**
         * LiquidityPoolEntryConstantProduct's original definition in the XDR file is:
         * ```
         * struct
        {
        LiquidityPoolConstantProductParameters params;

        int64 reserveA;        // amount of A in the pool
        int64 reserveB;        // amount of B in the pool
        int64 totalPoolShares; // total number of pool shares issued
        int64 poolSharesTrustLineCount; // number of trust lines for the
        // associated pool shares
        }
         * ```
         */
        data class LiquidityPoolEntryConstantProduct(
            val params: LiquidityPoolConstantProductParameters,
            val reserveA: Int64,
            val reserveB: Int64,
            val totalPoolShares: Int64,
            val poolSharesTrustLineCount: Int64,
        ) : XdrElement {
            override fun encode(stream: XdrOutputStream) {
                params.encode(stream)
                reserveA.encode(stream)
                reserveB.encode(stream)
                totalPoolShares.encode(stream)
                poolSharesTrustLineCount.encode(stream)
            }

            companion object : XdrElementDecoder<LiquidityPoolEntryConstantProduct> {
                override fun decode(stream: XdrInputStream): LiquidityPoolEntryConstantProduct {
                    val params = LiquidityPoolConstantProductParameters.decode(stream)
                    val reserveA = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
                    val reserveB = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
                    val totalPoolShares = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
                    val poolSharesTrustLineCount = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
                    return LiquidityPoolEntryConstantProduct(
                        params,
                        reserveA,
                        reserveB,
                        totalPoolShares,
                        poolSharesTrustLineCount,
                    )
                }
            }

        }
    }
}
