package horizon.operations

import io.ktor.util.InternalAPI
import io.ktor.util.decodeBase64String
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.horizon.operations.CreatePassiveSellOfferResponse
import me.rahimklaber.stellar.horizon.operations.ManageDataResponse
import me.rahimklaber.stellar.horizon.operations.PriceR
import kotlin.test.Test
import kotlin.test.assertEquals

class ManageDataResponseTest {
    val json = Json { ignoreUnknownKeys = true }


    @Test
    fun basic() {
        val decoded = json.decodeFromString<ManageDataResponse>(basicResponse)


        assertEquals("config.memo_required",decoded.name)
        assertEquals("MQ==",decoded.value)



    }

    val basicResponse = """
        {
      "_links": {
        "self": {
          "href": "https://horizon.stellar.org/operations/121957408846438401"
        },
        "transaction": {
          "href": "https://horizon.stellar.org/transactions/1e1b8f628c338a0306cbcb512bd89473a0c6b25df67ad77cfc1478437a575665"
        },
        "effects": {
          "href": "https://horizon.stellar.org/operations/121957408846438401/effects"
        },
        "succeeds": {
          "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=121957408846438401"
        },
        "precedes": {
          "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=121957408846438401"
        }
      },
      "id": "121957408846438401",
      "paging_token": "121957408846438401",
      "transaction_successful": true,
      "source_account": "GCAXBKU3AKYJPLQ6PEJ6L47KOATCYCBJ2NFRGAK7FUUA2DCEUC265SU2",
      "type": "manage_data",
      "type_i": 10,
      "created_at": "2020-02-25T17:47:32Z",
      "transaction_hash": "1e1b8f628c338a0306cbcb512bd89473a0c6b25df67ad77cfc1478437a575665",
      "name": "config.memo_required",
      "value": "MQ=="
    }
""".trimIndent()
}