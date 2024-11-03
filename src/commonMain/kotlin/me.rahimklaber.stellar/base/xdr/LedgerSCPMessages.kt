package me.rahimklaber.stellar.base.xdr

public data class LedgerSCPMessages(
    public val ledgerSeq: UInt,
    public val messages: SCPEnvelope,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(ledgerSeq.toInt())
        messages.encode(stream)
    }

    public companion object : XdrElementDecoder<LedgerSCPMessages> {
        override fun decode(stream: XdrStream): LedgerSCPMessages {
            val ledgerSeq = stream.readInt().toUInt()
            val messages = SCPEnvelope.decode(stream)
            return LedgerSCPMessages(ledgerSeq,messages)
        }
    }
}