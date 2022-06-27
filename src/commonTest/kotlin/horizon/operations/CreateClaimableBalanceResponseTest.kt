package horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.horizon.operations.CreateClaimableBalanceResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CreateClaimableBalanceResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic(){
        val decoded = json.decodeFromString<CreateClaimableBalanceResponse>(basicResponse)
        assertEquals("https://horizon.stellar.org/operations/124922916260433921",decoded.links.self)
        assertEquals("https://horizon.stellar.org/transactions/f94c338370839a598753221714de0b0193d4fc56ea369db6efe88f18669cc5a1",decoded.links.transaction)
        assertEquals("https://horizon.stellar.org/operations/124922916260433921/effects",decoded.links.effects)
        assertEquals("https://horizon.stellar.org/effects?order=desc\u0026cursor=124922916260433921",decoded.links.succeeds)
        assertEquals("https://horizon.stellar.org/effects?order=asc\u0026cursor=124922916260433921",decoded.links.precedes)
        assertEquals("NGNT:GAWODAROMJ33V5YDFY3NPYTHVYQG7MJXVJ2ND3AOGIHYRWINES6ACCPD",decoded.asset)
        assertEquals("200.0000000",decoded.amount)
        assertEquals("GC3C4AKRBQLHOJ45U4XG35ESVWRDECWO5XLDGYADO6DPR3L7KIDVUMML",decoded.claimants[0].destination)
        assertNotNull(decoded.claimants[0].predicate.and)
    }
    private val basicResponse = """
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
      "type": "create_claimable_balance",
      "type_i": 14,
      "created_at": "2020-04-09T00:14:11Z",
      "transaction_hash": "f94c338370839a598753221714de0b0193d4fc56ea369db6efe88f18669cc5a1",
      "asset": "NGNT:GAWODAROMJ33V5YDFY3NPYTHVYQG7MJXVJ2ND3AOGIHYRWINES6ACCPD",
      "amount": "200.0000000",
      "claimants": [
        {
          "destination": "GC3C4AKRBQLHOJ45U4XG35ESVWRDECWO5XLDGYADO6DPR3L7KIDVUMML",
          "predicate": {
            "and": [
              {
                "or": [
                  {
                    "relBefore": "12"
                  },
                  {
                    "absBefore": "2020-08-26T11:15:39Z",
                    "absBeforeEpoch": "1598440539"
                  }
                ]
              },
              {
                "not": { "unconditional": true }
              }
            ]
          }
        }
      ]
    }
""".trimIndent()
}


