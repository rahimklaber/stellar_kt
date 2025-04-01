// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * Signer's original definition in the XDR file is:
 * ```
 * struct Signer
{
SignerKey key;
uint32 weight; // really only need 1 byte
};
 * ```
 */
data class Signer(
    val key: SignerKey,
    val weight: Uint32,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        key.encode(stream)
        weight.encode(stream)
    }

    companion object : XdrElementDecoder<Signer> {
        override fun decode(stream: XdrInputStream): Signer {
            val key = SignerKey.decode(stream)
            val weight = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            return Signer(
                key,
                weight,
            )
        }
    }
}
