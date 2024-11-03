package me.rahimklaber.stellar.base.xdr

/**
 * // SCP history file is an array of these
 * union SCPHistoryEntry switch (int v)
 * {
 * case 0:
 *     SCPHistoryEntryV0 v0;
 * };
 */
sealed class SCPHistoryEntry(
    val v: Int,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(v)
    }

    data class V0(
        val v0: SCPHistoryEntryV0,
    ) : SCPHistoryEntry(0) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            v0.encode(stream)
        }
    }

    companion object : XdrElementDecoder<SCPHistoryEntry> {
        override fun decode(stream: XdrStream): SCPHistoryEntry {
            return when (val type = stream.readInt()) {
                0 -> {
                    val v0 = SCPHistoryEntryV0.decode(stream)
                    V0(v0)
                }

                else -> xdrDecodeError("Could not decode SCPHistoryEntry for $type")
            }
        }
    }
}
