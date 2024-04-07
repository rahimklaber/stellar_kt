package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Extend the TTL of the entries specified in the readOnly footprint
//   so they will live at least extendTo ledgers from lcl.
//
//    Threshold: med
//    Result: ExtendFootprintTTLResult
//*/
//struct ExtendFootprintTTLOp
//{
//    ExtensionPoint ext;
//    uint32 extendTo;
//};
///////////////////////////////////////////////////////////////////////////
data class ExtendFootprintTTLOp(
    val extendTo: UInt
): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(0) // TODO: replace with sealed class incase there are more cases
        stream.writeInt(extendTo.toInt())
    }

    companion object: XdrElementDecoder<ExtendFootprintTTLOp> {
        override fun decode(stream: XdrStream): ExtendFootprintTTLOp {
            stream.readInt()
            return ExtendFootprintTTLOp(stream.readInt().toUInt())
        }
    }
}
