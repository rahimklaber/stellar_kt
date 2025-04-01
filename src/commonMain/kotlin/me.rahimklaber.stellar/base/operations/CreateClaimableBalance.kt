package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.xdr.ClaimPredicate
import me.rahimklaber.stellar.base.xdr.CreateClaimableBalanceOp

sealed interface Predicate{
    fun toXdr() : ClaimPredicate
    data object Unconditional : Predicate {
        override fun toXdr(): ClaimPredicate {
            return ClaimPredicate.Unconditional
        }
    }

    data class Not(val predicate: Predicate) : Predicate{
        override fun toXdr(): ClaimPredicate {
            return ClaimPredicate.Not(predicate.toXdr())
        }
    }
    data class And(val predicates: List<Predicate>) : Predicate{
        override fun toXdr(): ClaimPredicate {
            return ClaimPredicate.And(predicates.map(Predicate::toXdr))
        }
    }
    data class Or(val predicates: List<Predicate>) : Predicate{
        override fun toXdr(): ClaimPredicate {
            return ClaimPredicate.Or(predicates.map(Predicate::toXdr))
        }
    }
    data class AbsBefore(val epochSeconds: Long) : Predicate {
        override fun toXdr(): ClaimPredicate {
            return ClaimPredicate.BeforeAbsoluteTime(epochSeconds)
        }
    }

    data class RelBefore(val secondsSinceClose: Long) : Predicate {
        override fun toXdr(): ClaimPredicate {
            return ClaimPredicate.BeforeRelativeTime(secondsSinceClose)
        }
    }

}
abstract class PredicateScope{
    protected abstract fun addPredicate(predicate: Predicate)
    fun unconditional(){
        addPredicate(Predicate.Unconditional)
    }
    fun and(block: BinaryPredicateScope.() -> Unit){
        addPredicate(PredicateDSl.predicateAnd(block))
    }
    fun or(block: BinaryPredicateScope.() -> Unit){
        addPredicate(PredicateDSl.predicateOr(block))
    }
    fun not(block: UnaryPredicateScope.() -> Unit){
        addPredicate(PredicateDSl.predicateNot(block))
    }
    fun absBefore(epochSeconds: Long){
        addPredicate(PredicateDSl.predicateAbsBefore(epochSeconds))
    }
    fun relBefore(secondsSinceClose: Long){
        addPredicate(PredicateDSl.predicateRelBefore(secondsSinceClose))
    }
}
class BinaryPredicateScope(internal val predicates : MutableList<Predicate> = mutableListOf()): PredicateScope(){

    override fun addPredicate(predicate: Predicate) {
        check(predicates.size < 2){"max amount of child predicates is 2"}
        predicates.add(predicate)
    }

}
class UnaryPredicateScope(internal var predicate : Predicate? = null): PredicateScope() {

    override fun addPredicate(predicate: Predicate) {
        check(this.predicate == null){"Not predicate can only have one child"}
        this.predicate = predicate
    }

}

object PredicateDSl
fun PredicateDSl.predicateNot(block: UnaryPredicateScope.() -> Unit): Predicate {
    val scope = UnaryPredicateScope()
    block(scope)
    return Predicate.Not(scope.predicate!!)
}

fun PredicateDSl.predicateAnd(block: BinaryPredicateScope.() -> Unit): Predicate.And {
    val scope = BinaryPredicateScope()
    block(scope)
    return Predicate.And(scope.predicates)
}

fun PredicateDSl.predicateOr(block: BinaryPredicateScope.() -> Unit): Predicate.Or {
    val scope = BinaryPredicateScope()
    block(scope)
    return Predicate.Or(scope.predicates)
}

fun PredicateDSl.predicateUnconditional() = Predicate.Unconditional
fun PredicateDSl.predicateRelBefore(secondsSinceClose: Long) = Predicate.RelBefore(secondsSinceClose)
fun PredicateDSl.predicateAbsBefore(epochSeconds: Long) = Predicate.AbsBefore(epochSeconds)

data class Claimant(val destination: String, val predicate: Predicate){
    fun toXdr(): me.rahimklaber.stellar.base.xdr.Claimant {
        return me.rahimklaber.stellar.base.xdr.Claimant.V0(
           me.rahimklaber.stellar.base.xdr.Claimant.ClaimantV0Anon(
               StrKey.encodeToAccountIDXDR(destination),
               predicate.toXdr()
           )
        )
    }
}
data class CreateClaimableBalance(
    override val sourceAccount: String? = null,
    val asset: Asset,
    val amount : TokenAmount,
    val claimants: List<Claimant>

) : Operation {
    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            body = me.rahimklaber.stellar.base.xdr.Operation.OperationBody.CreateClaimableBalance(
                CreateClaimableBalanceOp(
                    asset = asset.toXdr(),
                    amount = amount.value,
                    claimants = claimants.map(Claimant::toXdr)
                )
            )
        )
    }
}
