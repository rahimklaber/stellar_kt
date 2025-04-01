// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * MuxedAccount's original definition in the XDR file is:
 * ```
 * union MuxedAccount switch (CryptoKeyType type)
{
case KEY_TYPE_ED25519:
uint256 ed25519;
case KEY_TYPE_MUXED_ED25519:
struct
{
uint64 id;
uint256 ed25519;
} med25519;
};
 * ```
 */
sealed class MuxedAccount(val type: CryptoKeyType) : XdrElement {
    fun ed25519OrNull(): KeyTypeEd25519? = if (this is KeyTypeEd25519) this else null
    data class KeyTypeEd25519(
        val ed25519: Uint256,
    ) : MuxedAccount(CryptoKeyType.KEY_TYPE_ED25519) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            ed25519.encode(stream)
        }
    }

    fun med25519OrNull(): KeyTypeMuxedEd25519? = if (this is KeyTypeMuxedEd25519) this else null
    data class KeyTypeMuxedEd25519(
        val med25519: MuxedAccountMed25519,
    ) : MuxedAccount(CryptoKeyType.KEY_TYPE_MUXED_ED25519) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            med25519.encode(stream)
        }
    }

    companion object : XdrElementDecoder<MuxedAccount> {
        override fun decode(stream: XdrInputStream): MuxedAccount {
            return when (val type = CryptoKeyType.decode(stream)) {
                CryptoKeyType.KEY_TYPE_ED25519 -> {
                    val ed25519 = Uint256.decode(stream)
                    KeyTypeEd25519(ed25519)
                }

                CryptoKeyType.KEY_TYPE_MUXED_ED25519 -> {
                    val med25519 = MuxedAccountMed25519.decode(stream)
                    KeyTypeMuxedEd25519(med25519)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }

    /**
     * MuxedAccountMed25519's original definition in the XDR file is:
     * ```
     * struct
    {
    uint64 id;
    uint256 ed25519;
    }
     * ```
     */
    data class MuxedAccountMed25519(
        val id: Uint64,
        val ed25519: Uint256,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            id.encode(stream)
            ed25519.encode(stream)
        }

        companion object : XdrElementDecoder<MuxedAccountMed25519> {
            override fun decode(stream: XdrInputStream): MuxedAccountMed25519 {
                val id = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
                val ed25519 = Uint256.decode(stream)
                return MuxedAccountMed25519(
                    id,
                    ed25519,
                )
            }
        }

    }
}
