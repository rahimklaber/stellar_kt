package me.rahimklaber.stellar.base.xdr

/**
 * struct SCPEnvelope
 * {
 *     SCPStatement statement;
 *     Signature signature;
 * };
 */
data class SCPEnvelope(
    val statement: SCPStatement,
    val signature: Signature,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        statement.encode(stream)
        signature.encode(stream)
    }

    companion object : XdrElementDecoder<SCPEnvelope> {
        override fun decode(stream: XdrStream): SCPEnvelope {
            val statement = SCPStatement.decode(stream)
            val signature = Signature.decode(stream)
            return SCPEnvelope(statement, signature)
        }
    }
}

