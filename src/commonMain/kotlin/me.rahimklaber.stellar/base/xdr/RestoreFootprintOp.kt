package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Restore the archived entries specified in the readWrite footprint.
//
//    Threshold: med
//    Result: RestoreFootprintOp
//*/
//struct RestoreFootprintOp
//{
//    ExtensionPoint ext;
//};
///////////////////////////////////////////////////////////////////////////
data object RestoreFootprintOp: XdrElement, XdrElementDecoder<RestoreFootprintOp> {
    override fun encode(stream: XdrStream) {
        stream.writeInt(0)
    }

    override fun decode(stream: XdrStream): RestoreFootprintOp {
        stream.readInt()
        return this
    }
}