// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ClaimPredicateType's original definition in the XDR file is:
 * ```
 * enum ClaimPredicateType
{
CLAIM_PREDICATE_UNCONDITIONAL = 0,
CLAIM_PREDICATE_AND = 1,
CLAIM_PREDICATE_OR = 2,
CLAIM_PREDICATE_NOT = 3,
CLAIM_PREDICATE_BEFORE_ABSOLUTE_TIME = 4,
CLAIM_PREDICATE_BEFORE_RELATIVE_TIME = 5
};
 * ```
 */
enum class ClaimPredicateType(val value: Int) : XdrElement {
    CLAIM_PREDICATE_UNCONDITIONAL(0),
    CLAIM_PREDICATE_AND(1),
    CLAIM_PREDICATE_OR(2),
    CLAIM_PREDICATE_NOT(3),
    CLAIM_PREDICATE_BEFORE_ABSOLUTE_TIME(4),
    CLAIM_PREDICATE_BEFORE_RELATIVE_TIME(5);

    companion object : XdrElementDecoder<ClaimPredicateType> {
        override fun decode(stream: XdrInputStream): ClaimPredicateType {
            return when (val value = stream.readInt()) {
                0 -> CLAIM_PREDICATE_UNCONDITIONAL
                1 -> CLAIM_PREDICATE_AND
                2 -> CLAIM_PREDICATE_OR
                3 -> CLAIM_PREDICATE_NOT
                4 -> CLAIM_PREDICATE_BEFORE_ABSOLUTE_TIME
                5 -> CLAIM_PREDICATE_BEFORE_RELATIVE_TIME
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
