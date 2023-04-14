package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* ManageData
//    Adds, Updates, or Deletes a key value pair associated with a particular
//        account.
//    Threshold: med
//    Result: ManageDataResult
//*/
//struct ManageDataOp
//{
//    string64 dataName;
//    DataValue* dataValue; // set to null to clear
//};
///////////////////////////////////////////////////////////////////////////
data class ManageDataOp(
    val dataName: String64,
    val dataValue: DataValue?
): XdrElement {
    override fun encode(stream: XdrStream) {
        dataName.encode(stream)
        dataName.encodeNullable(stream)
    }

    companion object: XdrElementDecoder<ManageDataOp> {
        override fun decode(stream: XdrStream): ManageDataOp {
            val dataName = String64.decode(stream)
            val dataValue = DataValue.decodeNullable(stream)
            return ManageDataOp(dataName, dataValue)
        }
    }
}