package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class AllowTrustResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<AllowTrustResponse>(basicResponse)
        assertEquals(
            "https://horizon.stellar.org/operations/120497059836067841",
            decoded.links.self
        )
        assertEquals(
            "https://horizon.stellar.org/transactions/ac8dd0ddf1d047081c8e4c2a7ef9cc38a1a8af6c211184e1b16ebf2e32915d7f",
            decoded.links.transaction
        )
        assertEquals(
            "https://horizon.stellar.org/operations/120497059836067841/effects",
            decoded.links.effects
        )
        assertEquals(
            "https://horizon.stellar.org/effects?order=desc\u0026cursor=120497059836067841",
            decoded.links.succeeds
        )
        assertEquals(
            "https://horizon.stellar.org/effects?order=asc\u0026cursor=120497059836067841",
            decoded.links.precedes
        )
        assertEquals("GCRZQVBBDAWVOCO5R2NI34YR55RO2GQXPTDUE5OZESXGZRRTAEQLKEKN",decoded.trustee)
        assertEquals("GDSYBYRG6NIBJWR7BLY72HYV7VM4A7WWHUJ45FI7H4Q2U2RPR3BB3CFR",decoded.trustor)
        assertEquals(true,decoded.authorize)

    }

    val basicResponse = """
        {
          "_links": {
            "self": {
              "href": "https://horizon.stellar.org/operations/120497059836067841"
            },
            "transaction": {
              "href": "https://horizon.stellar.org/transactions/ac8dd0ddf1d047081c8e4c2a7ef9cc38a1a8af6c211184e1b16ebf2e32915d7f"
            },
            "effects": {
              "href": "https://horizon.stellar.org/operations/120497059836067841/effects"
            },
            "succeeds": {
              "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=120497059836067841"
            },
            "precedes": {
              "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=120497059836067841"
            }
          },
          "id": "120497059836067841",
          "paging_token": "120497059836067841",
          "transaction_successful": true,
          "source_account": "GCRZQVBBDAWVOCO5R2NI34YR55RO2GQXPTDUE5OZESXGZRRTAEQLKEKN",
          "type": "allow_trust",
          "type_i": 7,
          "created_at": "2020-02-03T14:30:52Z",
          "transaction_hash": "ac8dd0ddf1d047081c8e4c2a7ef9cc38a1a8af6c211184e1b16ebf2e32915d7f",
          "asset_type": "credit_alphanum4",
          "asset_code": "LSV1",
          "asset_issuer": "GCRZQVBBDAWVOCO5R2NI34YR55RO2GQXPTDUE5OZESXGZRRTAEQLKEKN",
          "trustee": "GCRZQVBBDAWVOCO5R2NI34YR55RO2GQXPTDUE5OZESXGZRRTAEQLKEKN",
          "trustor": "GDSYBYRG6NIBJWR7BLY72HYV7VM4A7WWHUJ45FI7H4Q2U2RPR3BB3CFR",
          "authorize": true
        }
    """.trimIndent()
}