package me.rahimklaber.stellar.base.xdr

/**
 *
 * // supports things like: A,B,C,(D,E,F),(G,H,(I,J,K,L))
 * // only allows 2 levels of nesting
 * struct SCPQuorumSet
 * {
 *     uint32 threshold;
 *     NodeID validators<>;
 *     SCPQuorumSet innerSets<>;
 * };
 */
data class SCPQuorumSet(
    val threshold: UInt,
    val validators: List<NodeID>,
    val innerSets: List<SCPQuorumSet>,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(threshold.toInt())
        validators.encode(stream)
        innerSets.encode(stream)
    }

    companion object : XdrElementDecoder<SCPQuorumSet> {
        override fun decode(stream: XdrStream): SCPQuorumSet {
            val threshold = stream.readInt().toUInt()
            val validators = decodeXdrElementList(stream, NodeID::decode)
            val innerSets = decodeXdrElementList(stream, ::decode)
            return SCPQuorumSet(threshold, validators, innerSets)
        }
    }
}
