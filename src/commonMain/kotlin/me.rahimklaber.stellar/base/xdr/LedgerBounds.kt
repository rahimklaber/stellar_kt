// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * LedgerBounds's original definition in the XDR file is:
 * ```
 * struct LedgerBounds
{
uint32 minLedger;
uint32 maxLedger; // 0 here means no maxLedger
};
 * ```
 */
data class LedgerBounds(
    val minLedger: Uint32,
    val maxLedger: Uint32,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        minLedger.encode(stream)
        maxLedger.encode(stream)
    }

    companion object : XdrElementDecoder<LedgerBounds> {
        override fun decode(stream: XdrInputStream): LedgerBounds {
            val minLedger = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val maxLedger = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            return LedgerBounds(
                minLedger,
                maxLedger,
            )
        }
    }
}
