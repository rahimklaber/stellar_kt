package me.rahimklaber.stellar.base.xdr

/**
 * struct TransactionSetV1
 * {
 *     Hash previousLedgerHash;
 *     TransactionPhase phases<>;
 * };
 */
data class TransactionSetV1(
    val previousLedgerHash: Hash,
    val phases: List<TransactionPhase>
): XdrElement {
    override fun encode(stream: XdrStream) {
        previousLedgerHash.encode(stream)
        phases.encode(stream)
    }

    companion object : XdrElementDecoder<TransactionSetV1> {
        override fun decode(stream: XdrStream): TransactionSetV1 {
            val previousLedgerHash = Hash.decode(stream)
            val phases = decodeXdrElementList(stream, TransactionPhase::decode)

            return TransactionSetV1(previousLedgerHash,phases)
        }
    }
}
