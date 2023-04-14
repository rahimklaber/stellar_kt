package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* DataEntry
//    Data can be attached to accounts.
//*/
//struct DataEntry
//{
//    AccountID accountID; // account this data belongs to
//    string64 dataName;
//    DataValue dataValue;
//
//    // reserved for future use
//    union switch (int v)
//    {
//    case 0:
//        void;
//    }
//    ext;
//};
///////////////////////////////////////////////////////////////////////////
data class DataEntry(
    val accountID: AccountID,
    val dataName: String64,
    val dataValue: DataValue,
    val discriminant: Int
) : XdrElement{
    override fun encode(stream: XdrStream) {
        accountID.encode(stream)
        dataName.encode(stream)
        dataValue.encode(stream)
        stream.writeInt(discriminant)
    }

    companion object: XdrElementDecoder<DataEntry>{
        override fun decode(stream: XdrStream): DataEntry {
            val accountID = AccountID.decode(stream)
            val dataName = String64.decode(stream)
            val dataValue = DataValue.decode(stream)
            val discriminant = stream.readInt()
            return DataEntry(accountID,dataName,dataValue,discriminant)
        }

    }
}