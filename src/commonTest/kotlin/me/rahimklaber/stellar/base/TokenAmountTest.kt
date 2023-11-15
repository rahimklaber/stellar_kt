package me.rahimklaber.stellar.base

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.expect

//TODO: add kotest later for data providers
class TokenAmountTest{


    @Test
    fun testTokenAmountFromString(){
        assertEquals(1_000_000_0, tokenAmount("1").value)
    }

    @Test
    fun testTokenAmountFromStringWithDot(){
        assertEquals(1_100_000_0, tokenAmount("1.1").value)
    }

    @Test
    fun testTokenAmountFromStringWithMoreThan7Decimals(){
        assertFails {
            tokenAmount("1.10000000")
        }
    }

}