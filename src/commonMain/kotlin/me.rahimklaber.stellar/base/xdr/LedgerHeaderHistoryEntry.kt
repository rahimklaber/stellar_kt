package me.rahimklaber.stellar.base.xdr

/**
 * struct LedgerHeaderHistoryEntry
 * {
 *     Hash hash;
 *     LedgerHeader header;
 *
 *     // reserved for future use
 *     union switch (int v)
 *     {
 *     case 0:
 *         void;
 *     }
 *     ext;
 * };
 */

data class LedgerHeaderHistoryEntry(
    val hash: Hash,
    val header: LedgerHeader,
    val discriminant: Int
): XdrElement {
    override fun encode(stream: XdrStream) {
        hash.encode(stream)
        header.encode(stream)
        stream.writeInt(discriminant)
    }

    companion object: XdrElementDecoder<LedgerHeaderHistoryEntry> {
        override fun decode(stream: XdrStream): LedgerHeaderHistoryEntry {
            return LedgerHeaderHistoryEntry(
                Hash.decode(stream),
                LedgerHeader.decode(stream),
                stream.readInt()
            )
        }
    }
}