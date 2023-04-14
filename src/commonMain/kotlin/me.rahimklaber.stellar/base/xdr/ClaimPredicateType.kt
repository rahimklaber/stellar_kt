package me.rahimklaber.stellar.base.xdr

enum class ClaimPredicateType(val value: Int) : XdrElement {
    CLAIM_PREDICATE_UNCONDITIONAL(0),
    CLAIM_PREDICATE_AND(1),
    CLAIM_PREDICATE_OR(2),
    CLAIM_PREDICATE_NOT(3),
    CLAIM_PREDICATE_BEFORE_ABSOLUTE_TIME(4),
    CLAIM_PREDICATE_BEFORE_RELATIVE_TIME(5);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<ClaimPredicateType>{
        override fun decode(stream: XdrStream): ClaimPredicateType {
            return when(val value = stream.readInt()){
                CLAIM_PREDICATE_UNCONDITIONAL.value -> CLAIM_PREDICATE_UNCONDITIONAL
                CLAIM_PREDICATE_AND.value -> CLAIM_PREDICATE_AND
                CLAIM_PREDICATE_OR.value -> CLAIM_PREDICATE_OR
                CLAIM_PREDICATE_NOT.value -> CLAIM_PREDICATE_NOT
                CLAIM_PREDICATE_BEFORE_ABSOLUTE_TIME.value -> CLAIM_PREDICATE_BEFORE_ABSOLUTE_TIME
                CLAIM_PREDICATE_BEFORE_RELATIVE_TIME.value -> CLAIM_PREDICATE_BEFORE_RELATIVE_TIME
                else -> throw IllegalArgumentException("Could not decode for value: $value")
            }
        }

    }
}