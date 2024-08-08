package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Upload WASM, create, and invoke contracts in Soroban.
//
//    Threshold: med
//    Result: InvokeHostFunctionResult
//*/
//struct InvokeHostFunctionOp
//{
//    // Host function to invoke.
//    HostFunction hostFunction;
//    // Per-address authorizations for this host function.
//    SorobanAuthorizationEntry auth<>;
//};
///////////////////////////////////////////////////////////////////////////
data class InvokeHostFunctionOp(
    val hostFunction: HostFunction,
    var auth: List<SorobanAuthorizationEntry>
): XdrElement {
    override fun encode(stream: XdrStream) {
        hostFunction.encode(stream)
        auth.encode(stream)
    }

    companion object: XdrElementDecoder<InvokeHostFunctionOp> {
        override fun decode(stream: XdrStream): InvokeHostFunctionOp {
            return InvokeHostFunctionOp(HostFunction.decode(stream), decodeXdrElementList(stream, SorobanAuthorizationEntry::decode))
        }
    }
}
