package me.rahimklaber.stellar.base.xdr

/**
 * // note: ledgerMessages may refer to any quorumSets encountered
 * // in the file so far, not just the one from this entry
 * struct SCPHistoryEntryV0
 * {
 *     SCPQuorumSet quorumSets<>; // additional quorum sets used by ledgerMessages
 *     LedgerSCPMessages ledgerMessages;
 * };
 */
data class SCPHistoryEntryV0(
    val quorumSets: List<SCPQuorumSet>,
    val ledgerMessages: LedgerSCPMessages,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        quorumSets.encode(stream)
        ledgerMessages.encode(stream)
    }

    companion object : XdrElementDecoder<SCPHistoryEntryV0> {
        override fun decode(stream: XdrStream): SCPHistoryEntryV0 {
            val quorumSets = decodeXdrElementList(stream, SCPQuorumSet::decode)
            val ledgerMessages = LedgerSCPMessages.decode(stream)
            return SCPHistoryEntryV0(quorumSets, ledgerMessages)
        }
    }
}
