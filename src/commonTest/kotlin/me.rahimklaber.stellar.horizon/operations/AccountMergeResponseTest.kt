package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountMergeResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<AccountMergeResponse>(basicResponse)
        assertEquals(
            "https://horizon.stellar.org/operations/121887714411839489",
            decoded.links.self
        )
        assertEquals(
            "https://horizon.stellar.org/transactions/02077009a551ec94c776f83529293dcfc1c2cd5b38af043ef7f3699bf5a71f0a",
            decoded.links.transaction
        )
        assertEquals(
            "https://horizon.stellar.org/operations/121887714411839489/effects",
            decoded.links.effects
        )
        assertEquals(
            "https://horizon.stellar.org/effects?order=desc\u0026cursor=121887714411839489",
            decoded.links.succeeds
        )
        assertEquals(
            "https://horizon.stellar.org/effects?order=asc\u0026cursor=121887714411839489",
            decoded.links.precedes
        )
        assertEquals("02077009a551ec94c776f83529293dcfc1c2cd5b38af043ef7f3699bf5a71f0a",decoded.transactionHash)
        assertEquals("GCVLWV5B3L3YE6DSCCMHLCK7QIB365NYOLQLW3ZKHI5XINNMRLJ6YHVX",decoded.account)
        assertEquals("GATL3ETTZ3XDGFXX2ELPIKCZL7S5D2HY3VK4T7LRPD6DW5JOLAEZSZBA",decoded.into)
    }

val basicResponse = """
     {
  "_links": {
    "self": {
      "href": "https://horizon.stellar.org/operations/121887714411839489"
    },
    "transaction": {
      "href": "https://horizon.stellar.org/transactions/02077009a551ec94c776f83529293dcfc1c2cd5b38af043ef7f3699bf5a71f0a"
    },
    "effects": {
      "href": "https://horizon.stellar.org/operations/121887714411839489/effects"
    },
    "succeeds": {
      "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=121887714411839489"
    },
    "precedes": {
      "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=121887714411839489"
    }
  },
  "id": "121887714411839489",
  "paging_token": "121887714411839489",
  "transaction_successful": true,
  "source_account": "GCVLWV5B3L3YE6DSCCMHLCK7QIB365NYOLQLW3ZKHI5XINNMRLJ6YHVX",
  "type": "account_merge",
  "type_i": 8,
  "created_at": "2020-02-24T17:03:00Z",
  "transaction_hash": "02077009a551ec94c776f83529293dcfc1c2cd5b38af043ef7f3699bf5a71f0a",
  "account": "GCVLWV5B3L3YE6DSCCMHLCK7QIB365NYOLQLW3ZKHI5XINNMRLJ6YHVX",
  "into": "GATL3ETTZ3XDGFXX2ELPIKCZL7S5D2HY3VK4T7LRPD6DW5JOLAEZSZBA"
}
""".trimIndent()

}


