package horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.horizon.operations.BumpSequenceResponse
import me.rahimklaber.stellar.horizon.operations.ChangeTrustResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class ChangeTrustResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<ChangeTrustResponse>(basicResponse)
        assertEquals(
            "https://horizon.stellar.org/operations/120192477935251457",
            decoded.links.self
        )
        assertEquals(
            "https://horizon.stellar.org/transactions/ec4116595bdfa8c1039c40af425e497c91fcf387c2a2a0cfa1f3bf64733f1f23",
            decoded.links.transaction
        )
        assertEquals(
            "https://horizon.stellar.org/operations/120192477935251457/effects",
            decoded.links.effects
        )
        assertEquals(
            "https://horizon.stellar.org/effects?order=desc\u0026cursor=120192477935251457",
            decoded.links.succeeds
        )
        assertEquals(
            "https://horizon.stellar.org/effects?order=asc\u0026cursor=120192477935251457",
            decoded.links.precedes
        )
        assertEquals("922337203685.4775807",decoded.limit)
        assertEquals("GAWODAROMJ33V5YDFY3NPYTHVYQG7MJXVJ2ND3AOGIHYRWINES6ACCPD",decoded.trustee)
        assertEquals("GAWODAROMJ33V5YDFY3NPYTHVYQG7MJXVJ2ND3AOGIHYRWINES6ACCPD",decoded.trustor)

    }

    val basicResponse = """
        {
          "_links": {
            "self": {
              "href": "https://horizon.stellar.org/operations/120192477935251457"
            },
            "transaction": {
              "href": "https://horizon.stellar.org/transactions/ec4116595bdfa8c1039c40af425e497c91fcf387c2a2a0cfa1f3bf64733f1f23"
            },
            "effects": {
              "href": "https://horizon.stellar.org/operations/120192477935251457/effects"
            },
            "succeeds": {
              "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=120192477935251457"
            },
            "precedes": {
              "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=120192477935251457"
            }
          },
          "id": "120192477935251457",
          "paging_token": "120192477935251457",
          "transaction_successful": true,
          "source_account": "GAYOLLLUIZE4DZMBB2ZBKGBUBZLIOYU6XFLW37GBP2VZD3ABNXCW4BVA",
          "type": "change_trust",
          "type_i": 6,
          "created_at": "2020-01-29T19:46:55Z",
          "transaction_hash": "ec4116595bdfa8c1039c40af425e497c91fcf387c2a2a0cfa1f3bf64733f1f23",
          "asset_type": "credit_alphanum4",
          "asset_code": "NGNT",
          "asset_issuer": "GAWODAROMJ33V5YDFY3NPYTHVYQG7MJXVJ2ND3AOGIHYRWINES6ACCPD",
          "limit": "922337203685.4775807",
          "trustee": "GAWODAROMJ33V5YDFY3NPYTHVYQG7MJXVJ2ND3AOGIHYRWINES6ACCPD",
          "trustor": "GAWODAROMJ33V5YDFY3NPYTHVYQG7MJXVJ2ND3AOGIHYRWINES6ACCPD"
        }
    """.trimIndent()
}