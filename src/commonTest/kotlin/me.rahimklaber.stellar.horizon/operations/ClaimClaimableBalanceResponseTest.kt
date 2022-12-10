package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ClaimClaimableBalanceResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<ClaimClaimableBalanceResponse>(basicResponse)
        assertEquals("000000000102030000000000000000000000000000000000000000000000000000000000",
        decoded.balanceId)
        assertEquals("GC3C4AKRBQLHOJ45U4XG35ESVWRDECWO5XLDGYADO6DPR3L7KIDVUMML",decoded.claimant)

    }

    val basicResponse = """
        {
          "_links": {
            "self": {
              "href": "https://horizon.stellar.org/operations/124922916260433921"
            },
            "transaction": {
              "href": "https://horizon.stellar.org/transactions/f94c338370839a598753221714de0b0193d4fc56ea369db6efe88f18669cc5a1"
            },
            "effects": {
              "href": "https://horizon.stellar.org/operations/124922916260433921/effects"
            },
            "succeeds": {
              "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=124922916260433921"
            },
            "precedes": {
              "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=124922916260433921"
            }
          },
          "id": "124922916260433921",
          "paging_token": "124922916260433921",
          "transaction_successful": true,
          "source_account": "GAYOLLLUIZE4DZMBB2ZBKGBUBZLIOYU6XFLW37GBP2VZD3ABNXCW4BVA",
          "type": "claim_claimable_balance",
          "type_i": 15,
          "created_at": "2020-04-09T00:14:11Z",
          "transaction_hash": "f94c338370839a598753221714de0b0193d4fc56ea369db6efe88f18669cc5a1",
          "balance_id": "000000000102030000000000000000000000000000000000000000000000000000000000",
          "claimant": "GC3C4AKRBQLHOJ45U4XG35ESVWRDECWO5XLDGYADO6DPR3L7KIDVUMML"
        }
    """.trimIndent()
}