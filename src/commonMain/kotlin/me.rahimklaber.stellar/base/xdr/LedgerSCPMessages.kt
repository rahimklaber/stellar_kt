package me.rahimklaber.stellar.base.xdr

/**
 * // historical SCP messages
 *
 * struct LedgerSCPMessages
 * {
 *     uint32 ledgerSeq;
 *     SCPEnvelope messages<>;
 * };
 */
data class LedgerSCPMessages(
    val ledgerSeq: UInt,
    val messages: List<SCPEnvelope>,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(ledgerSeq.toInt())
        messages.encode(stream)
    }

    companion object : XdrElementDecoder<LedgerSCPMessages> {
        override fun decode(stream: XdrStream): LedgerSCPMessages {
            val ledgerSeq = stream.readInt().toUInt()
            val messages = decodeXdrElementList(stream, SCPEnvelope::decode)
            return LedgerSCPMessages(ledgerSeq, messages)
        }
    }
}