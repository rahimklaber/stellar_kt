package me.rahimklaber.stellar.base.xdr

public data class SCPHistoryEntryV0(
    public val quorumSets: SCPQuorumSet,
    public val ledgerMessages: LedgerSCPMessages,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        quorumSets.encode(stream)
        ledgerMessages.encode(stream)
    }

    public companion object : XdrElementDecoder<SCPHistoryEntryV0> {
        override fun decode(stream: XdrStream): SCPHistoryEntryV0 {
            val quorumSets = SCPQuorumSet.decode(stream)
            val ledgerMessages = LedgerSCPMessages.decode(stream)
            return SCPHistoryEntryV0(quorumSets,ledgerMessages)
        }
    }
}
