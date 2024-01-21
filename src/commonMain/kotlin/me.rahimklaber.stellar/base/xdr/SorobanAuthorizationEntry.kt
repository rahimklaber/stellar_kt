package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Unit of authorization data for Soroban.
//
//   Represents an authorization for executing the tree of authorized contract 
//   and/or host function calls by the user defined by `credentials`.
//*/
///////////////////////////////////////////////////////////////////////////
data class SorobanAuthorizationEntry(
    val credentials: SorobanCredentials,
    val rootInvocation: SorobanAuthorizedInvocation
): XdrElement {
    override fun encode(stream: XdrStream) {
        credentials.encode(stream)
        rootInvocation.encode(stream)
    }

    companion object: XdrElementDecoder<SorobanAuthorizationEntry> {
        override fun decode(stream: XdrStream): SorobanAuthorizationEntry {
            return SorobanAuthorizationEntry(
                SorobanCredentials.decode(stream),
                SorobanAuthorizedInvocation.decode(stream)
            )
        }
    }
}
