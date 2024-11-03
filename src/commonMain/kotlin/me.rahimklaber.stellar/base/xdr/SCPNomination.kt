package me.rahimklaber.stellar.base.xdr

/**
 *         struct SCPNomination
 *         {
 *             Hash quorumSetHash; // D
 *             Value votes<>;      // X
 *             Value accepted<>;   // Y
 *         };
 */
data class SCPNomination(
    val quorumSetHash: Hash,
    val votes: List<Value>,
    val accepted: List<Value>,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        quorumSetHash.encode(stream)
        votes.encode(stream)
        accepted.encode(stream)
    }

    companion object : XdrElementDecoder<SCPNomination> {
        override fun decode(stream: XdrStream): SCPNomination {
            val quorumSetHash = Hash.decode(stream)
            val votes = decodeXdrElementList(stream, Value::decode)
            val accepted = decodeXdrElementList(stream, Value::decode)
            return SCPNomination(quorumSetHash, votes, accepted)
        }
    }
}