package me.rahimklaber.stellar.base.xdr

/**
 * union TxSetComponent switch (TxSetComponentType type)
 * {
 * case TXSET_COMP_TXS_MAYBE_DISCOUNTED_FEE:
 *   struct
 *   {
 *     int64* baseFee;
 *     TransactionEnvelope txs<>;
 *   } txsMaybeDiscountedFee;
 * };
 */
sealed class TxSetComponent(
    val type: TxSetComponentType,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }


    data class MaybeDiscountedFee(
        val txsMaybeDiscountedFee: TxsMaybeDiscountedFee,
    ) : TxSetComponent(TxSetComponentType.TXSET_COMP_TXS_MAYBE_DISCOUNTED_FEE) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            txsMaybeDiscountedFee.encode(stream)
        }
    }

    companion object : XdrElementDecoder<TxSetComponent> {
        override fun decode(stream: XdrStream): TxSetComponent {
            return when (val type = TxSetComponentType.decode(stream)) {
                TxSetComponentType.TXSET_COMP_TXS_MAYBE_DISCOUNTED_FEE -> {
                    val txsMaybeDiscountedFee = TxsMaybeDiscountedFee.decode(stream)
                    MaybeDiscountedFee(txsMaybeDiscountedFee)
                }
            }
        }
    }
}

data class TxsMaybeDiscountedFee(
    val baseFee: Long?,
    val txs: List<TransactionEnvelope>,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeLongNullable(baseFee)
        txs.encode(stream)
    }

    companion object : XdrElementDecoder<TxsMaybeDiscountedFee> {
        override fun decode(stream: XdrStream): TxsMaybeDiscountedFee {
            val baseFee = stream.readLongNullable()
            val txs = decodeXdrElementList(stream, TransactionEnvelope::decode)
            return TxsMaybeDiscountedFee(baseFee, txs)
        }
    }
}