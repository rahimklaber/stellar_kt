// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCMetaV0's original definition in the XDR file is:
 * ```
 * struct SCMetaV0
{
string key<>;
string val<>;
};
 * ```
 */
data class SCMetaV0(
    val key: String,
    val value: String,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        val keySize = key.length
        stream.writeInt(keySize)
        stream.writeBytes(key.encodeToByteArray())
        val valueSize = value.length
        stream.writeInt(valueSize)
        stream.writeBytes(value.encodeToByteArray())
    }

    companion object : XdrElementDecoder<SCMetaV0> {
        override fun decode(stream: XdrInputStream): SCMetaV0 {
            val keySize = stream.readInt()
            val key = decodeString(keySize, stream)
            val valueSize = stream.readInt()
            val value = decodeString(valueSize, stream)
            return SCMetaV0(
                key,
                value,
            )
        }
    }
}
