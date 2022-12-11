package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class BumpSequenceResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<BumpSequenceResponse>(basicResponse)
        assertEquals(
            "https://horizon.stellar.org/operations/124922916260433921",
            decoded.links.self
        )
        assertEquals(
            "https://horizon.stellar.org/transactions/f94c338370839a598753221714de0b0193d4fc56ea369db6efe88f18669cc5a1",
            decoded.links.transaction
        )
        assertEquals(
            "https://horizon.stellar.org/operations/124922916260433921/effects",
            decoded.links.effects
        )
        assertEquals(
            "https://horizon.stellar.org/effects?order=desc\u0026cursor=124922916260433921",
            decoded.links.succeeds
        )
        assertEquals(
            "https://horizon.stellar.org/effects?order=asc\u0026cursor=124922916260433921",
            decoded.links.precedes
        )
        assertEquals("120192344968520085",decoded.bumpTo)


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
          "type": "bump_sequence",
          "type_i": 11,
          "created_at": "2020-04-09T00:14:11Z",
          "transaction_hash": "f94c338370839a598753221714de0b0193d4fc56ea369db6efe88f18669cc5a1",
          "bump_to": "120192344968520085"
        }
    """.trimIndent()
}