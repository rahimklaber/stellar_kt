package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// // this is the meta produced when applying transactions
//// it does not include pre-apply updates such as fees
//union TransactionMeta switch (int v)
//{
//case 0:
//    OperationMeta operations<>;
//case 1:
//    TransactionMetaV1 v1;
//case 2:
//    TransactionMetaV2 v2;
//case 3:
//    TransactionMetaV3 v3;
//};
///////////////////////////////////////////////////////////////////////////
sealed class TransactionMeta(val discriminant: Int): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(discriminant)
    }

    data class V3(val v3: TransactionMetaV3): TransactionMeta(3){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            v3.encode(stream)
        }
    }


    companion object: XdrElementDecoder<TransactionMeta> {
        override fun decode(stream: XdrStream): TransactionMeta {
            val discriminant = stream.readInt()

            return when(discriminant){
                3 -> V3(TransactionMetaV3.decode(stream))
                else -> TODO()
            }
        }
    }

}
