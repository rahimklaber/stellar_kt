package horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.horizon.operations.ChangeTrustResponse
import me.rahimklaber.stellar.horizon.operations.CreateAccountResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class CreateAccountResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<CreateAccountResponse>(basicResponse)
        assertEquals(
            "GBVFTZL5HIPT4PFQVTZVIWR77V7LWYCXU4CLYWWHHOEXB64XPG5LDMTU",
            decoded.sourceAccount
        )
        assertEquals(
            "2.0000000",
            decoded.startingBalance
        )
        assertEquals(
            "GBVFTZL5HIPT4PFQVTZVIWR77V7LWYCXU4CLYWWHHOEXB64XPG5LDMTU",
            decoded.funder
        )
        assertEquals(
            "GAYOLLLUIZE4DZMBB2ZBKGBUBZLIOYU6XFLW37GBP2VZD3ABNXCW4BVA",
            decoded.account
        )

    }

    val basicResponse = """
        {
  "_links": {
    "self": {
      "href": "https://horizon.stellar.org/operations/120192344791343105"
    },
    "transaction": {
      "href": "https://horizon.stellar.org/transactions/ef0fe04ac3c7de7228ca2598886059868ad05c224a041e8b2d9ee2a8a9dd6894"
    },
    "effects": {
      "href": "https://horizon.stellar.org/operations/120192344791343105/effects"
    },
    "succeeds": {
      "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=120192344791343105"
    },
    "precedes": {
      "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=120192344791343105"
    }
  },
  "id": "120192344791343105",
  "paging_token": "120192344791343105",
  "transaction_successful": true,
  "source_account": "GBVFTZL5HIPT4PFQVTZVIWR77V7LWYCXU4CLYWWHHOEXB64XPG5LDMTU",
  "type": "create_account",
  "type_i": 0,
  "created_at": "2020-01-29T19:43:59Z",
  "transaction_hash": "ef0fe04ac3c7de7228ca2598886059868ad05c224a041e8b2d9ee2a8a9dd6894",
  "starting_balance": "2.0000000",
  "funder": "GBVFTZL5HIPT4PFQVTZVIWR77V7LWYCXU4CLYWWHHOEXB64XPG5LDMTU",
  "account": "GAYOLLLUIZE4DZMBB2ZBKGBUBZLIOYU6XFLW37GBP2VZD3ABNXCW4BVA"
}
    """.trimIndent()
}