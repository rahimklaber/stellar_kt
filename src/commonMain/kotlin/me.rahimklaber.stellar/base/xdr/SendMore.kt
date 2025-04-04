// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * SendMore's original definition in the XDR file is:
 * ```
 * struct SendMore
{
uint32 numMessages;
};
 * ```
 */
@JvmInline
value class SendMore(val numMessages: Uint32) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        numMessages.encode(stream)
    }

    companion object : XdrElementDecoder<SendMore> {
        override fun decode(stream: XdrInputStream): SendMore {
            val numMessages = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            return SendMore(
                numMessages,
            )
        }
    }
}
