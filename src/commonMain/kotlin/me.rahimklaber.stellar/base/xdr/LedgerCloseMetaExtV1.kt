// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * LedgerCloseMetaExtV1's original definition in the XDR file is:
 * ```
 * struct LedgerCloseMetaExtV1
{
ExtensionPoint ext;
int64 sorobanFeeWrite1KB;
};
 * ```
 */
data class LedgerCloseMetaExtV1(
    val ext: ExtensionPoint,
    val sorobanFeeWrite1KB: Int64,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        ext.encode(stream)
        sorobanFeeWrite1KB.encode(stream)
    }

    companion object : XdrElementDecoder<LedgerCloseMetaExtV1> {
        override fun decode(stream: XdrInputStream): LedgerCloseMetaExtV1 {
            val ext = ExtensionPoint.decode(stream)
            val sorobanFeeWrite1KB = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            return LedgerCloseMetaExtV1(
                ext,
                sorobanFeeWrite1KB,
            )
        }
    }
}
