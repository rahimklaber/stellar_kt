// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCPNomination's original definition in the XDR file is:
 * ```
 * struct SCPNomination
{
Hash quorumSetHash; // D
Value votes<>;      // X
Value accepted<>;   // Y
};
 * ```
 */
data class SCPNomination(
    val quorumSetHash: Hash,
    val votes: List<Value>,
    val accepted: List<Value>,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        quorumSetHash.encode(stream)
        val votesSize = votes.size
        stream.writeInt(votesSize)
        votes.encodeXdrElements(stream)
        val acceptedSize = accepted.size
        stream.writeInt(acceptedSize)
        accepted.encodeXdrElements(stream)
    }

    companion object : XdrElementDecoder<SCPNomination> {
        override fun decode(stream: XdrInputStream): SCPNomination {
            val quorumSetHash = Hash.decode(stream)
            val votesSize = stream.readInt()
            val votes: List<Value> = decodeXdrElementsList(votesSize, stream, Value.decoder())
            val acceptedSize = stream.readInt()
            val accepted: List<Value> = decodeXdrElementsList(acceptedSize, stream, Value.decoder())
            return SCPNomination(
                quorumSetHash,
                votes,
                accepted,
            )
        }
    }
}
