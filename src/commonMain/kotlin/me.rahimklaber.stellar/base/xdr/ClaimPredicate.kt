package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union ClaimPredicate switch (ClaimPredicateType type)
//{
//case CLAIM_PREDICATE_UNCONDITIONAL:
//    void;
//case CLAIM_PREDICATE_AND:
//    ClaimPredicate andPredicates<2>;
//case CLAIM_PREDICATE_OR:
//    ClaimPredicate orPredicates<2>;
//case CLAIM_PREDICATE_NOT:
//    ClaimPredicate* notPredicate;
//case CLAIM_PREDICATE_BEFORE_ABSOLUTE_TIME:
//    int64 absBefore; // Predicate will be true if closeTime < absBefore
//case CLAIM_PREDICATE_BEFORE_RELATIVE_TIME:
//    int64 relBefore; // Seconds since closeTime of the ledger in which the
//                     // ClaimableBalanceEntry was created
//};
///////////////////////////////////////////////////////////////////////////
sealed class ClaimPredicate(val type: ClaimPredicateType): XdrElement {
    object ClaimPredicateUnconditional : ClaimPredicate(ClaimPredicateType.CLAIM_PREDICATE_UNCONDITIONAL){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
        }
    }
    data class ClaimPredicateAnd(val predicates: List<ClaimPredicate>) :
        ClaimPredicate(ClaimPredicateType.CLAIM_PREDICATE_AND) {
        init {
            require(predicates.size <= 2) { "And Claim predicate can have maximum 2 predicates" }
        }

        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(predicates.size)
            predicates.forEach {
                it.encode(stream)
            }
        }
    }

    data class ClaimPredicateOr(val predicates: List<ClaimPredicate>) :
        ClaimPredicate(ClaimPredicateType.CLAIM_PREDICATE_OR) {
        init {
            require(predicates.size <= 2) { "Or Claim predicate can have maximum 2 predicates" }
        }

        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(predicates.size)
            predicates.forEach {
                it.encode(stream)
            }
        }
    }

    data class ClaimPredicateNot(val predicate: ClaimPredicate?) : ClaimPredicate(ClaimPredicateType.CLAIM_PREDICATE_NOT){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            if(predicate == null){
                stream.writeInt(0)
            }else{
                stream.writeInt(1)
                predicate.encode(stream)
            }
        }
    }
    data class ClaimPredicateBeforeAbsoluteTime(val absBefore: Long) :
        ClaimPredicate(ClaimPredicateType.CLAIM_PREDICATE_BEFORE_ABSOLUTE_TIME){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeLong(absBefore)
        }
        }

    data class ClaimPredicateBeforeRelativeTime(val relBefore: Long) :
        ClaimPredicate(ClaimPredicateType.CLAIM_PREDICATE_BEFORE_RELATIVE_TIME){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeLong(relBefore)
        }
        }

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    companion object: XdrElementDecoder<ClaimPredicate>{
        override fun decode(stream: XdrStream): ClaimPredicate {

            return when(ClaimPredicateType.decode(stream)){
                ClaimPredicateType.CLAIM_PREDICATE_UNCONDITIONAL -> {
                    ClaimPredicateUnconditional
                }
                ClaimPredicateType.CLAIM_PREDICATE_AND -> {
                    val amount = stream.readInt()
                    val predicates = mutableListOf<ClaimPredicate>()
                    for(i in 0 until amount){
                        predicates.add(decode(stream))
                    }
                    ClaimPredicateAnd(predicates)
                }
                ClaimPredicateType.CLAIM_PREDICATE_OR -> {
                    val amount = stream.readInt()
                    val predicates = mutableListOf<ClaimPredicate>()
                    for(i in 0 until amount){
                        predicates.add(decode(stream))
                    }
                    ClaimPredicateOr(predicates)
                }
                ClaimPredicateType.CLAIM_PREDICATE_NOT -> {
                    val nullOrNot = stream.readInt()
                    val predicate = if(nullOrNot == 1){ // not null
                        decode(stream)
                    }else{
                        null
                    }
                    ClaimPredicateNot(predicate)
                }
                ClaimPredicateType.CLAIM_PREDICATE_BEFORE_ABSOLUTE_TIME -> {
                    ClaimPredicateBeforeAbsoluteTime(stream.readLong())
                }
                ClaimPredicateType.CLAIM_PREDICATE_BEFORE_RELATIVE_TIME -> {
                    ClaimPredicateBeforeRelativeTime(stream.readLong())
                }
            }
        }

    }
}
