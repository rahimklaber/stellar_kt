// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * NonexistenceProofBody's original definition in the XDR file is:
 * ```
 * struct NonexistenceProofBody
{
ColdArchiveBucketEntry entriesToProve<>;

// Vector of vectors, where proofLevels[level]
// contains all HashNodes that correspond with that level
ProofLevel proofLevels<>;
};
 * ```
 */
data class NonexistenceProofBody(
    val entriesToProve: List<ColdArchiveBucketEntry>,
    val proofLevels: List<ProofLevel>,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        val entriesToProveSize = entriesToProve.size
        stream.writeInt(entriesToProveSize)
        entriesToProve.encodeXdrElements(stream)
        val proofLevelsSize = proofLevels.size
        stream.writeInt(proofLevelsSize)
        proofLevels.encodeXdrElements(stream)
    }

    companion object : XdrElementDecoder<NonexistenceProofBody> {
        override fun decode(stream: XdrInputStream): NonexistenceProofBody {
            val entriesToProveSize = stream.readInt()
            val entriesToProve: List<ColdArchiveBucketEntry> = decodeXdrElementsList(entriesToProveSize, stream, ColdArchiveBucketEntry.decoder())
            val proofLevelsSize = stream.readInt()
            val proofLevels: List<ProofLevel> = decodeXdrElementsList(proofLevelsSize, stream, ProofLevel.decoder())
            return NonexistenceProofBody(
                entriesToProve,
                proofLevels,
            )
        }
    }
}
