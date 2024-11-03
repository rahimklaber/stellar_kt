package me.rahimklaber.stellar.base.xdr

/**
 * struct LedgerCloseValueSignature
 * {
 *     NodeID nodeID;       // which node introduced the value
 *     Signature signature; // nodeID's signature
 * };
 */
data class LedgerCloseValueSignature(
    val nodeID: NodeID,
    val signature: Signature
): XdrElement {
    override fun encode(stream: XdrStream) {
        nodeID.encode(stream)
        signature.encode(stream)
    }

    companion object: XdrElementDecoder<LedgerCloseValueSignature> {
        override fun decode(stream: XdrStream): LedgerCloseValueSignature {
            return LedgerCloseValueSignature(
                NodeID.decode(stream),
                Signature.decode(stream)
            )
        }
    }
}
