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
enum class ThresholdIndexes(val value: Int) {
    THRESHOLD_MASTER_WEIGHT(0),
    THRESHOLD_LOW(1),
    THRESHOLD_MED(2),
    THRESHOLD_HIGH(3)
}