package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// // Ledger key sets touched by a smart contract transaction.
//struct LedgerFootprint
//{
//    LedgerKey readOnly<>;
//    LedgerKey readWrite<>;
//};
///////////////////////////////////////////////////////////////////////////
data class LedgerFootprint(
    val readOnly: List<LedgerKey>,
    val readWrite: List<LedgerKey>
): XdrElement {
    override fun encode(stream: XdrStream) {
        readOnly.encode(stream)
        readWrite.encode(stream)
    }

    companion object: XdrElementDecoder<LedgerFootprint> {
        override fun decode(stream: XdrStream): LedgerFootprint {
            val readOnly = decodeXdrElementList(stream, LedgerKey::decode)
            val readWrite = decodeXdrElementList(stream, LedgerKey::decode)

            return LedgerFootprint(readOnly, readWrite)
        }
    }
}
