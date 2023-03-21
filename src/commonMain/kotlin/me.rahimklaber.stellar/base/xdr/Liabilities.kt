package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct Liabilities
//{
//    int64 buying;
//    int64 selling;
//};
///////////////////////////////////////////////////////////////////////////
data class Liabilities(
    val buying: Long,
    val selling: Long
) : XdrElement{
    override fun encode(stream: XdrStream) {
        stream.writeLong(buying)
        stream.writeLong(selling)
    }

    companion object: XdrElementDecoder<Liabilities>{
        override fun decode(stream: XdrStream): Liabilities {
            val buying = stream.readLong()
            val selling = stream.readLong()
            return Liabilities(buying,selling)
        }

    }

}