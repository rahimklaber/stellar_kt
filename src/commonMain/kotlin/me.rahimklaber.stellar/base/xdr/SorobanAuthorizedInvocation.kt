package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct SorobanAuthorizedInvocation
//{
//    SorobanAuthorizedFunction function;
//    SorobanAuthorizedInvocation subInvocations<>;
//};
///////////////////////////////////////////////////////////////////////////
// TODO: test this.
data class SorobanAuthorizedInvocation(
    val function: SorobanAuthorizedFunction,
    val subInvocations: List<SorobanAuthorizedInvocation>
): XdrElement {
    override fun encode(stream: XdrStream) {
        function.encode(stream)
        subInvocations.encode(stream)
    }

    companion object: XdrElementDecoder<SorobanAuthorizedInvocation> {
        override fun decode(stream: XdrStream): SorobanAuthorizedInvocation {
            return SorobanAuthorizedInvocation(
                SorobanAuthorizedFunction.decode(stream),
                decodeXdrElementList(stream, SorobanAuthorizedInvocation::decode)
            )
        }
    }
}