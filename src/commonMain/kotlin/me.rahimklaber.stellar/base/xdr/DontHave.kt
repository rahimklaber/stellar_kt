// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * DontHave's original definition in the XDR file is:
 * ```
 * struct DontHave
{
MessageType type;
uint256 reqHash;
};
 * ```
 */
data class DontHave(
    val type: MessageType,
    val reqHash: Uint256,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        type.encode(stream)
        reqHash.encode(stream)
    }

    companion object : XdrElementDecoder<DontHave> {
        override fun decode(stream: XdrInputStream): DontHave {
            val type = MessageType.decode(stream)
            val reqHash = Uint256.decode(stream)
            return DontHave(
                type,
                reqHash,
            )
        }
    }
}
