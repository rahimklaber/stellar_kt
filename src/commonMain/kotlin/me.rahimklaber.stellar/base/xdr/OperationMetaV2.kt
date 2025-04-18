// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * OperationMetaV2's original definition in the XDR file is:
 * ```
 * struct OperationMetaV2
{
ExtensionPoint ext;

LedgerEntryChanges changes;

ContractEvent events<>;
DiagnosticEvent diagnosticEvents<>;
};
 * ```
 */
data class OperationMetaV2(
    val ext: ExtensionPoint,
    val changes: LedgerEntryChanges,
    val events: List<ContractEvent>,
    val diagnosticEvents: List<DiagnosticEvent>,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        ext.encode(stream)
        changes.encode(stream)
        val eventsSize = events.size
        stream.writeInt(eventsSize)
        events.encodeXdrElements(stream)
        val diagnosticEventsSize = diagnosticEvents.size
        stream.writeInt(diagnosticEventsSize)
        diagnosticEvents.encodeXdrElements(stream)
    }

    companion object : XdrElementDecoder<OperationMetaV2> {
        override fun decode(stream: XdrInputStream): OperationMetaV2 {
            val ext = ExtensionPoint.decode(stream)
            val changes = LedgerEntryChanges.decode(stream)
            val eventsSize = stream.readInt()
            val events: List<ContractEvent> = decodeXdrElementsList(eventsSize, stream, ContractEvent.decoder())
            val diagnosticEventsSize = stream.readInt()
            val diagnosticEvents: List<DiagnosticEvent> = decodeXdrElementsList(diagnosticEventsSize, stream, DiagnosticEvent.decoder())
            return OperationMetaV2(
                ext,
                changes,
                events,
                diagnosticEvents,
            )
        }
    }
}
