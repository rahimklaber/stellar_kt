package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct TTLEntry {
//    // Hash of the LedgerKey that is associated with this TTLEntry
//    Hash keyHash;
//    uint32 liveUntilLedgerSeq;
//};
///////////////////////////////////////////////////////////////////////////
data class TTLEntry(
    val keyHash: Hash,
    val liveUntilLedgerSeq: UInt
): XdrElement{
    override fun encode(stream: XdrStream) {
        keyHash.encode(stream)
        stream.writeInt(liveUntilLedgerSeq.toInt())
    }

    companion object: XdrElementDecoder<TTLEntry> {
        override fun decode(stream: XdrStream): TTLEntry {
            return TTLEntry(
                Hash.decode(stream),
                stream.readInt().toUInt()
            )
        }
    }
}
