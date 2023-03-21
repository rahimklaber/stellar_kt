package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// // the 'Thresholds' type is packed uint8_t values
//// defined by these indexes
//enum ThresholdIndexes
//{
//    THRESHOLD_MASTER_WEIGHT = 0,
//    THRESHOLD_LOW = 1,
//    THRESHOLD_MED = 2,
//    THRESHOLD_HIGH = 3
//};
///////////////////////////////////////////////////////////////////////////
enum class ThresholdIndexes(val value: Int) : XdrElement {
    THRESHOLD_MASTER_WEIGHT(0),
    THRESHOLD_LOW(1),
    THRESHOLD_MED(2),
    THRESHOLD_HIGH(3);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<ThresholdIndexes>{
        override fun decode(stream: XdrStream): ThresholdIndexes {
            return when(val value = stream.readInt()){
                0 -> THRESHOLD_MASTER_WEIGHT
                1 -> THRESHOLD_LOW
                2 -> THRESHOLD_MED
                3 -> THRESHOLD_HIGH
                else -> throw IllegalArgumentException("Cannot decode $value to ThresholdIndexes")
            }
        }

    }
}