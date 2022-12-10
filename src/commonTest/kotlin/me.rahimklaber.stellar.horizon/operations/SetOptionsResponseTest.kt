package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class SetOptionsResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<SetOptionsResponse>(basicResponse)

        assertEquals(true,decoded.transactionSuccessful)
        assertEquals("www.stellar.org",decoded.homeDomain)




    }

    val basicResponse = """
    {
      "_links": {
        "self": {
          "href": "https://horizon.stellar.org/operations/102125410241826819"
        },
        "transaction": {
          "href": "https://horizon.stellar.org/transactions/e020277cf755a1c29234d34f123f546a2c4805d7b4ca9303e253667b0ff4d846"
        },
        "effects": {
          "href": "https://horizon.stellar.org/operations/102125410241826819/effects"
        },
        "succeeds": {
          "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=102125410241826819"
        },
        "precedes": {
          "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=102125410241826819"
        }
      },
      "id": "102125410241826819",
      "paging_token": "102125410241826819",
      "transaction_successful": true,
      "source_account": "GABMKJM6I25XI4K7U6XWMULOUQIQ27BCTMLS6BYYSOWKTBUXVRJSXHYQ",
      "type": "set_options",
      "type_i": 5,
      "created_at": "2019-05-08T21:20:34Z",
      "transaction_hash": "e020277cf755a1c29234d34f123f546a2c4805d7b4ca9303e253667b0ff4d846",
      "home_domain": "www.stellar.org"
    }
""".trimIndent()
}