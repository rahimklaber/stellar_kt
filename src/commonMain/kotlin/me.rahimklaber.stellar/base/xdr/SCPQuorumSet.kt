// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCPQuorumSet's original definition in the XDR file is:
 * ```
 * struct SCPQuorumSet
{
uint32 threshold;
NodeID validators<>;
SCPQuorumSet innerSets<>;
};
 * ```
 */
data class SCPQuorumSet(
    val threshold: Uint32,
    val validators: List<NodeID>,
    val innerSets: List<SCPQuorumSet>,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        threshold.encode(stream)
        val validatorsSize = validators.size
        stream.writeInt(validatorsSize)
        validators.encodeXdrElements(stream)
        val innerSetsSize = innerSets.size
        stream.writeInt(innerSetsSize)
        innerSets.encodeXdrElements(stream)
    }

    companion object : XdrElementDecoder<SCPQuorumSet> {
        override fun decode(stream: XdrInputStream): SCPQuorumSet {
            val threshold = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val validatorsSize = stream.readInt()
            val validators: List<NodeID> = decodeXdrElementsList(validatorsSize, stream, NodeID.decoder())
            val innerSetsSize = stream.readInt()
            val innerSets: List<SCPQuorumSet> = decodeXdrElementsList(innerSetsSize, stream, SCPQuorumSet.decoder())
            return SCPQuorumSet(
                threshold,
                validators,
                innerSets,
            )
        }
    }
}
