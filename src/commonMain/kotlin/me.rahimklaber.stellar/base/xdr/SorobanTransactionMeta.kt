package me.rahimklaber.stellar.base.xdr

import me.rahimklaber.stellar.base.xdr.soroban.SCVal

///////////////////////////////////////////////////////////////////////////
// struct SorobanTransactionMeta
//{
//    ExtensionPoint ext;
//
//    ContractEvent events<>;             // custom events populated by the
//                                        // contracts themselves.
//    SCVal returnValue;                  // return value of the host fn invocation
//
//    // Diagnostics events that are not hashed.
//    // This will contain all contract and diagnostic events. Even ones
//    // that were emitted in a failed contract call.
//    DiagnosticEvent diagnosticEvents<>;
//};
///////////////////////////////////////////////////////////////////////////
data class SorobanTransactionMeta(
    val events: List<ContractEvent>,
    val returnValue: SCVal,
    val diagnosticEvents: List<DiagnosticEvent>
): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(0) // extpoint

        events.encode(stream)
        returnValue.encode(stream)

        diagnosticEvents.encode(stream)

    }

    companion object: XdrElementDecoder<SorobanTransactionMeta> {
        override fun decode(stream: XdrStream): SorobanTransactionMeta {
            stream.readInt() // 0 ext point

            val events = decodeXdrElementList(stream, ContractEvent::decode)
            val returnValue = SCVal.decode(stream)

            val diagnosticEvents = decodeXdrElementList(stream, DiagnosticEvent::decode)

            return SorobanTransactionMeta(events, returnValue, diagnosticEvents)
        }
    }

}
