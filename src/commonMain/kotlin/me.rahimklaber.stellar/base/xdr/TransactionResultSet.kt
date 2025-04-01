// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * TransactionResultSet's original definition in the XDR file is:
 * ```
 * struct TransactionResultSet
{
TransactionResultPair results<>;
};
 * ```
 */
@JvmInline
value class TransactionResultSet(val results: List<TransactionResultPair>) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        val resultsSize = results.size
        stream.writeInt(resultsSize)
        results.encodeXdrElements(stream)
    }

    companion object : XdrElementDecoder<TransactionResultSet> {
        override fun decode(stream: XdrInputStream): TransactionResultSet {
            val resultsSize = stream.readInt()
            val results: List<TransactionResultPair> = decodeXdrElementsList(resultsSize, stream, TransactionResultPair.decoder())
            return TransactionResultSet(
                results,
            )
        }
    }
}
