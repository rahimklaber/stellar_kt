// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SorobanAuthorizedInvocation's original definition in the XDR file is:
 * ```
 * struct SorobanAuthorizedInvocation
{
SorobanAuthorizedFunction function;
SorobanAuthorizedInvocation subInvocations<>;
};
 * ```
 */
data class SorobanAuthorizedInvocation(
    val function: SorobanAuthorizedFunction,
    val subInvocations: List<SorobanAuthorizedInvocation>,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        function.encode(stream)
        val subInvocationsSize = subInvocations.size
        stream.writeInt(subInvocationsSize)
        subInvocations.encodeXdrElements(stream)
    }

    companion object : XdrElementDecoder<SorobanAuthorizedInvocation> {
        override fun decode(stream: XdrInputStream): SorobanAuthorizedInvocation {
            val function = SorobanAuthorizedFunction.decode(stream)
            val subInvocationsSize = stream.readInt()
            val subInvocations: List<SorobanAuthorizedInvocation> =
                decodeXdrElementsList(subInvocationsSize, stream, SorobanAuthorizedInvocation.decoder())
            return SorobanAuthorizedInvocation(
                function,
                subInvocations,
            )
        }
    }
}
