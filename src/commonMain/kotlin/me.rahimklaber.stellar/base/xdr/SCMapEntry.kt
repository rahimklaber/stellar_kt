// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCMapEntry's original definition in the XDR file is:
 * ```
 * struct SCMapEntry
{
SCVal key;
SCVal val;
};
 * ```
 */
data class SCMapEntry(
    val key: SCVal,
    val value: SCVal,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        key.encode(stream)
        value.encode(stream)
    }

    companion object : XdrElementDecoder<SCMapEntry> {
        override fun decode(stream: XdrInputStream): SCMapEntry {
            val key = SCVal.decode(stream)
            val value = SCVal.decode(stream)
            return SCMapEntry(
                key,
                value,
            )
        }
    }
}
