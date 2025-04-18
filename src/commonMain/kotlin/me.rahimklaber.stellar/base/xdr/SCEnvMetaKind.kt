// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCEnvMetaKind's original definition in the XDR file is:
 * ```
 * enum SCEnvMetaKind
{
SC_ENV_META_KIND_INTERFACE_VERSION = 0
};
 * ```
 */
enum class SCEnvMetaKind(val value: Int) : XdrElement {
    SC_ENV_META_KIND_INTERFACE_VERSION(0);

    companion object : XdrElementDecoder<SCEnvMetaKind> {
        override fun decode(stream: XdrInputStream): SCEnvMetaKind {
            return when (val value = stream.readInt()) {
                0 -> SC_ENV_META_KIND_INTERFACE_VERSION
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
