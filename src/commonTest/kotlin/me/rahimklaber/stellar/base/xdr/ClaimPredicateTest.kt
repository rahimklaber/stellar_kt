package me.rahimklaber.stellar.base.xdr

import io.ktor.util.*
import kotlin.test.Test

class ClaimPredicateTest {
    @Test
    fun test1(){
        val predicate= ClaimPredicate.ClaimPredicateAnd(
            listOf(
                ClaimPredicate.ClaimPredicateUnconditional,
                ClaimPredicate.ClaimPredicateBeforeAbsoluteTime(1)
            )
        )

        val stream = XdrStream()
        predicate.encode(stream)

//        println(stream.buffer.readByteArray().encodeBase64())
    }
}