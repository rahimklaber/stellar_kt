package horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.horizon.operations.CreatePassiveSellOfferResponse
import me.rahimklaber.stellar.horizon.operations.ManageSellOfferResponse
import me.rahimklaber.stellar.horizon.operations.PriceR
import kotlin.test.Test
import kotlin.test.assertEquals

class ManageSellOfferResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<ManageSellOfferResponse>(basicResponse)

        assertEquals("1336.0326986",decoded.amount)
        assertEquals("0.0559999",decoded.price)
        assertEquals(PriceR(559999,10000000),decoded.priceR)
        assertEquals("credit_alphanum4",decoded.buyingAssetType)
        assertEquals("USD",decoded.buyingAssetCode)
        assertEquals("GDUKMGUGDZQK6YHYA5Z6AY2G4XDSZPSZ3SW5UN3ARVMO6QSRDWP5YLEX",decoded.buyingAssetIssuer)
        assertEquals("native",decoded.sellingAssetType)
        assertEquals("0",decoded.offerId)


    }

    val basicResponse = """
     {
      "_links": {
        "self": {
          "href": "https://horizon.stellar.org/operations/124892722640347138"
        },
        "transaction": {
          "href": "https://horizon.stellar.org/transactions/ef8ffb54ff5990a686fda3ebfc07b8162f042ff0fcdb4f7ff141531e386f0a18"
        },
        "effects": {
          "href": "https://horizon.stellar.org/operations/124892722640347138/effects"
        },
        "succeeds": {
          "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=124892722640347138"
        },
        "precedes": {
          "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=124892722640347138"
        }
      },
      "id": "124892722640347138",
      "paging_token": "124892722640347138",
      "transaction_successful": true,
      "source_account": "GCM4PT6XDZBWOOENDS6FOU22GJQLJPV2GC7VRVII4TFGZBA3ZXNM55SV",
      "type": "manage_sell_offer",
      "type_i": 3,
      "created_at": "2020-04-08T13:36:39Z",
      "transaction_hash": "ef8ffb54ff5990a686fda3ebfc07b8162f042ff0fcdb4f7ff141531e386f0a18",
      "amount": "1336.0326986",
      "price": "0.0559999",
      "price_r": {
        "n": 559999,
        "d": 10000000
      },
      "buying_asset_type": "credit_alphanum4",
      "buying_asset_code": "USD",
      "buying_asset_issuer": "GDUKMGUGDZQK6YHYA5Z6AY2G4XDSZPSZ3SW5UN3ARVMO6QSRDWP5YLEX",
      "selling_asset_type": "native",
      "offer_id": "0"
    }
""".trimIndent()
}