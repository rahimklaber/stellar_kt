// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr

import kotlin.jvm.JvmInline


/**
 * ProofLevel's original definition in the XDR file is:
 * ```
 * typedef ArchivalProofNode ProofLevel<>;
 * ```
 */
@JvmInline
value class ProofLevel(val value: List<ArchivalProofNode>) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        val valueSize = value.size
        stream.writeInt(valueSize)
        value.encodeXdrElements(stream)
    }

    companion object : XdrElementDecoder<ProofLevel> {
        override fun decode(stream: XdrInputStream): ProofLevel {
            val valueSize = stream.readInt()
            val value: List<ArchivalProofNode> = decodeXdrElementsList(valueSize, stream, ArchivalProofNode.decoder())
            return ProofLevel(value)
        }
    }
}
