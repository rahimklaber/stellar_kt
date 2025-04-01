// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * FloodAdvert's original definition in the XDR file is:
 * ```
 * struct FloodAdvert
{
TxAdvertVector txHashes;
};
 * ```
 */
@JvmInline
value class FloodAdvert(val txHashes: TxAdvertVector) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        txHashes.encode(stream)
    }

    companion object : XdrElementDecoder<FloodAdvert> {
        override fun decode(stream: XdrInputStream): FloodAdvert {
            val txHashes = TxAdvertVector.decode(stream)
            return FloodAdvert(
                txHashes,
            )
        }
    }
}
