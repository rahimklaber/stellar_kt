// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * AuthenticatedMessage's original definition in the XDR file is:
 * ```
 * union AuthenticatedMessage switch (uint32 v)
{
case 0:
struct
{
uint64 sequence;
StellarMessage message;
HmacSha256Mac mac;
} v0;
};
 * ```
 */
sealed class AuthenticatedMessage(val type: Uint32) : XdrElement {
    fun v0OrNull(): AuthenticatedMessageV0? = if (this is AuthenticatedMessageV0) this else null
    data class AuthenticatedMessageV0(
        val v0: AuthenticatedMessageV0Anon,
    ) : AuthenticatedMessage(0u) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            v0.encode(stream)
        }
    }

    companion object : XdrElementDecoder<AuthenticatedMessage> {
        override fun decode(stream: XdrInputStream): AuthenticatedMessage {
            return when (val type = Uint32.decode(stream)) {
                0u -> {
                    val v0 = AuthenticatedMessageV0Anon.decode(stream)
                    AuthenticatedMessageV0(v0)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }

    /**
     * AuthenticatedMessageV0Anon's original definition in the XDR file is:
     * ```
     * struct
    {
    uint64 sequence;
    StellarMessage message;
    HmacSha256Mac mac;
    }
     * ```
     */
    data class AuthenticatedMessageV0Anon(
        val sequence: Uint64,
        val message: StellarMessage,
        val mac: HmacSha256Mac,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            sequence.encode(stream)
            message.encode(stream)
            mac.encode(stream)
        }

        companion object : XdrElementDecoder<AuthenticatedMessageV0Anon> {
            override fun decode(stream: XdrInputStream): AuthenticatedMessageV0Anon {
                val sequence = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
                val message = StellarMessage.decode(stream)
                val mac = HmacSha256Mac.decode(stream)
                return AuthenticatedMessageV0Anon(
                    sequence,
                    message,
                    mac,
                )
            }
        }

    }
}
