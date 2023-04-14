package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct LedgerBounds
//{
//    uint32 minLedger;
//    uint32 maxLedger; // 0 here means no maxLedger
//};
///////////////////////////////////////////////////////////////////////////
data class LedgerBounds(
    val minLedger: UInt,
    val maxLedger: UInt
) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(minLedger.toInt())
        stream.writeInt(maxLedger.toInt())
    }

    companion object: XdrElementDecoder<LedgerBounds> {
        override fun decode(stream: XdrStream): LedgerBounds {
            val minLedger = stream.readInt().toUInt()
            val maxLedger = stream.readInt().toUInt()
            return LedgerBounds(minLedger, maxLedger)
        }
    }
}
