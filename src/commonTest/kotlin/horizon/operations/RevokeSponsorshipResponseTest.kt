package horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.horizon.operations.EndSponsoringFutureReserves
import me.rahimklaber.stellar.horizon.operations.RevokeSponsorshipResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class RevokeSponsorshipResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<RevokeSponsorshipResponse>(basicResponse)
        assertEquals("GAYOLLLUIZE4DZMBB2ZBKGBUBZLIOYU6XFLW37GBP2VZD3ABNXCW4BVA",decoded.accountId)
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
              "type": "revoke_sponsorship",
              "type_i": 19,
              "created_at": "2020-04-09T00:14:11Z",
              "transaction_hash": "f94c338370839a598753221714de0b0193d4fc56ea369db6efe88f18669cc5a1",
              "account_id": "GAYOLLLUIZE4DZMBB2ZBKGBUBZLIOYU6XFLW37GBP2VZD3ABNXCW4BVA"
            }
    """.trimIndent()
}