package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct TransactionResultPair
//{
//    Hash transactionHash;
//    TransactionResult result; // result for the transaction
//};
///////////////////////////////////////////////////////////////////////////
data class TransactionResultPair(
    val transactionHash: Hash,
    val result: TransactionResult
): XdrElement {
    override fun encode(stream: XdrStream) {
        transactionHash.encode(stream)
        result.encode(stream)
    }

    companion object: XdrElementDecoder<TransactionResultPair> {
        override fun decode(stream: XdrStream): TransactionResultPair {
            return TransactionResultPair(Hash.decode(stream), TransactionResult.decode(stream))
        }
    }
}
