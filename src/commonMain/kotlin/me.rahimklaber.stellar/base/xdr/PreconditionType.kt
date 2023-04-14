package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum PreconditionType
//{
//    PRECOND_NONE = 0,
//    PRECOND_TIME = 1,
//    PRECOND_V2 = 2
//};
///////////////////////////////////////////////////////////////////////////
enum class PreconditionType(val value: Int): XdrElement {
    PRECOND_NONE(0),
    PRECOND_TIME(1),
    PRECOND_V2(2);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<PreconditionType> {
        override fun decode(stream: XdrStream): PreconditionType {
            return when(val value = stream.readInt()){
                PRECOND_NONE.value -> PRECOND_NONE
                PRECOND_TIME.value -> PRECOND_TIME
                PRECOND_V2.value -> PRECOND_V2
                else -> throw IllegalArgumentException("Could not decode PreconditionType for value: $value")
            }
        }
    }
}