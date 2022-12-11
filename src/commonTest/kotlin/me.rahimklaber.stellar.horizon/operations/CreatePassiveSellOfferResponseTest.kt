package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class CreatePassiveSellOfferResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<CreatePassiveSellOfferResponse>(basicResponse)

        assertEquals("1.0000000",decoded.amount)
        assertEquals("1.0000000",decoded.price)
        assertEquals(PriceR(1L,1L),decoded.priceR)
        assertEquals("credit_alphanum4",decoded.buyingAssetType)
        assertEquals("USD",decoded.buyingAssetCode)
        assertEquals("GBNLJIYH34UWO5YZFA3A3HD3N76R6DOI33N4JONUOHEEYZYCAYTEJ5AK",decoded.buyingAssetIssuer)
        assertEquals("credit_alphanum4",decoded.sellingAssetType)
        assertEquals("USD",decoded.sellingAssetCode)
        assertEquals("GDUKMGUGDZQK6YHYA5Z6AY2G4XDSZPSZ3SW5UN3ARVMO6QSRDWP5YLEX",decoded.sellingAssetIssuer)


    }

val basicResponse = """
    {
      "_links": {
        "self": {
          "href": "https://horizon.stellar.org/operations/124895183656849409"
        },
        "transaction": {
          "href": "https://horizon.stellar.org/transactions/20afb7f9613efe9e851579190c80758ee101550e85740f71274c3eb3f0cb0418"
        },
        "effects": {
          "href": "https://horizon.stellar.org/operations/124895183656849409/effects"
        },
        "succeeds": {
          "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=124895183656849409"
        },
        "precedes": {
          "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=124895183656849409"
        }
      },
      "id": "124895183656849409",
      "paging_token": "124895183656849409",
      "transaction_successful": true,
      "source_account": "GAYOLLLUIZE4DZMBB2ZBKGBUBZLIOYU6XFLW37GBP2VZD3ABNXCW4BVA",
      "type": "create_passive_sell_offer",
      "type_i": 4,
      "created_at": "2020-04-08T14:28:15Z",
      "transaction_hash": "20afb7f9613efe9e851579190c80758ee101550e85740f71274c3eb3f0cb0418",
      "amount": "1.0000000",
      "price": "1.0000000",
      "price_r": {
        "n": 1,
        "d": 1
      },
      "buying_asset_type": "credit_alphanum4",
      "buying_asset_code": "USD",
      "buying_asset_issuer": "GBNLJIYH34UWO5YZFA3A3HD3N76R6DOI33N4JONUOHEEYZYCAYTEJ5AK",
      "selling_asset_type": "credit_alphanum4",
      "selling_asset_code": "USD",
      "selling_asset_issuer": "GDUKMGUGDZQK6YHYA5Z6AY2G4XDSZPSZ3SW5UN3ARVMO6QSRDWP5YLEX"
    }
""".trimIndent()
}