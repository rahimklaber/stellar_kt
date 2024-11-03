package me.rahimklaber.stellar.base.xdr

/**
 * union GeneralizedTransactionSet switch (int v)
 * {
 * // We consider the legacy TransactionSet to be v0.
 * case 1:
 *     TransactionSetV1 v1TxSet;
 * };
 */
sealed class GeneralizedTransactionSet(val discriminant: Int) : XdrElement {

    override fun encode(stream: XdrStream) {
        stream.writeInt(discriminant)
    }

    data class GeneralizedTransactionSetV1(val v1TxSet: TransactionSetV1) : GeneralizedTransactionSet(1) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            v1TxSet.encode(stream)
        }
    }

    companion object : XdrElementDecoder<GeneralizedTransactionSet> {
        override fun decode(stream: XdrStream): GeneralizedTransactionSet {
            return when (val type = stream.readInt()) {
                1 -> {
                    val v1TxSet = TransactionSetV1.decode(stream)
                    GeneralizedTransactionSetV1(v1TxSet)
                }

                else -> xdrDecodeError("Could not decode GeneralizedTransactionSet for $type")
            }
        }
    }
}
