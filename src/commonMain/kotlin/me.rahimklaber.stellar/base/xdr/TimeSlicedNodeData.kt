// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * TimeSlicedNodeData's original definition in the XDR file is:
 * ```
 * struct TimeSlicedNodeData
{
uint32 addedAuthenticatedPeers;
uint32 droppedAuthenticatedPeers;
uint32 totalInboundPeerCount;
uint32 totalOutboundPeerCount;

// SCP stats
uint32 p75SCPFirstToSelfLatencyMs;
uint32 p75SCPSelfToOtherLatencyMs;

// How many times the node lost sync in the time slice
uint32 lostSyncCount;

// Config data
bool isValidator;
uint32 maxInboundPeerCount;
uint32 maxOutboundPeerCount;
};
 * ```
 */
data class TimeSlicedNodeData(
    val addedAuthenticatedPeers: Uint32,
    val droppedAuthenticatedPeers: Uint32,
    val totalInboundPeerCount: Uint32,
    val totalOutboundPeerCount: Uint32,
    val p75SCPFirstToSelfLatencyMs: Uint32,
    val p75SCPSelfToOtherLatencyMs: Uint32,
    val lostSyncCount: Uint32,
    val isValidator: Boolean,
    val maxInboundPeerCount: Uint32,
    val maxOutboundPeerCount: Uint32,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        addedAuthenticatedPeers.encode(stream)
        droppedAuthenticatedPeers.encode(stream)
        totalInboundPeerCount.encode(stream)
        totalOutboundPeerCount.encode(stream)
        p75SCPFirstToSelfLatencyMs.encode(stream)
        p75SCPSelfToOtherLatencyMs.encode(stream)
        lostSyncCount.encode(stream)
        stream.writeBoolean(isValidator)
        maxInboundPeerCount.encode(stream)
        maxOutboundPeerCount.encode(stream)
    }

    companion object : XdrElementDecoder<TimeSlicedNodeData> {
        override fun decode(stream: XdrInputStream): TimeSlicedNodeData {
            val addedAuthenticatedPeers = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val droppedAuthenticatedPeers = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val totalInboundPeerCount = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val totalOutboundPeerCount = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val p75SCPFirstToSelfLatencyMs = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val p75SCPSelfToOtherLatencyMs = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val lostSyncCount = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val isValidator = stream.readBoolean()
            val maxInboundPeerCount = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val maxOutboundPeerCount = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            return TimeSlicedNodeData(
                addedAuthenticatedPeers,
                droppedAuthenticatedPeers,
                totalInboundPeerCount,
                totalOutboundPeerCount,
                p75SCPFirstToSelfLatencyMs,
                p75SCPSelfToOtherLatencyMs,
                lostSyncCount,
                isValidator,
                maxInboundPeerCount,
                maxOutboundPeerCount,
            )
        }
    }
}
