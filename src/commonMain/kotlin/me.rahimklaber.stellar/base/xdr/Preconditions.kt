package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union Preconditions switch (PreconditionType type)
//{
//case PRECOND_NONE:
//    void;
//case PRECOND_TIME:
//    TimeBounds timeBounds;
//case PRECOND_V2:
//    PreconditionsV2 v2;
//};
///////////////////////////////////////////////////////////////////////////
sealed class Preconditions(val type: PreconditionType): XdrElement{

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    object None: Preconditions(PreconditionType.PRECOND_NONE)
    data class Time(val timeBounds: TimeBounds): Preconditions(PreconditionType.PRECOND_TIME){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            timeBounds.encode(stream)
        }
    }
    data class V2(val v2: PreconditionsV2): Preconditions(PreconditionType.PRECOND_V2){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            v2.encode(stream)
        }
    }

    companion object: XdrElementDecoder<Preconditions> {
        override fun decode(stream: XdrStream): Preconditions {
            return when(val type= PreconditionType.decode(stream)){
                PreconditionType.PRECOND_NONE -> None
                PreconditionType.PRECOND_TIME -> Time(TimeBounds.decode(stream))
                PreconditionType.PRECOND_V2 -> V2(PreconditionsV2.decode(stream))
            }
        }
    }

}
