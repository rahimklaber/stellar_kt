// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * StoredTransactionSet's original definition in the XDR file is:
 * ```
 * union StoredTransactionSet switch (int v)
{
case 0:
TransactionSet txSet;
case 1:
GeneralizedTransactionSet generalizedTxSet;
};
 * ```
 */
sealed class StoredTransactionSet(val type: Int) : XdrElement {
    fun txSetOrNull(): StoredTransactionSetV0? = if (this is StoredTransactionSetV0) this else null
    data class StoredTransactionSetV0(
        val txSet: TransactionSet,
    ) : StoredTransactionSet(0) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            txSet.encode(stream)
        }
    }

    fun generalizedTxSetOrNull(): StoredTransactionSetV1? = if (this is StoredTransactionSetV1) this else null
    data class StoredTransactionSetV1(
        val generalizedTxSet: GeneralizedTransactionSet,
    ) : StoredTransactionSet(1) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            generalizedTxSet.encode(stream)
        }
    }

    companion object : XdrElementDecoder<StoredTransactionSet> {
        override fun decode(stream: XdrInputStream): StoredTransactionSet {
            return when (val type = Int.decode(stream)) {
                0 -> {
                    val txSet = TransactionSet.decode(stream)
                    StoredTransactionSetV0(txSet)
                }

                1 -> {
                    val generalizedTxSet = GeneralizedTransactionSet.decode(stream)
                    StoredTransactionSetV1(generalizedTxSet)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
