package me.rahimklaber.stellar.base.xdr

/**
 * struct SCPBallot
 * {
 *     uint32 counter; // n
 *     Value value;    // x
 * };
 */
data class SCPBallot(
    val counter: UInt,
    val value: Value,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(counter.toInt())
        value.encode(stream)
    }

    companion object : XdrElementDecoder<SCPBallot> {
        override fun decode(stream: XdrStream): SCPBallot {
            val counter = stream.readInt().toUInt()
            val value = Value.decode(stream)
            return SCPBallot(counter, value)
        }
    }
}