package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct TimeBounds
//{
//    TimePoint minTime;
//    TimePoint maxTime; // 0 here means no maxTime
//};
///////////////////////////////////////////////////////////////////////////
data class TimeBounds(
    val minTime: TimePoint,
    val maxTime: TimePoint,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeLong(minTime.toLong())
        stream.writeLong(maxTime.toLong())
    }

    companion object: XdrElementDecoder<TimeBounds> {
        override fun decode(stream: XdrStream): TimeBounds {
            val minTime = stream.readLong().toULong()
            val maxTime = stream.readLong().toULong()
            return TimeBounds(minTime, maxTime)
        }
    }
}
