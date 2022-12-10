package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ManageBuyOfferResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<ManageBuyOfferResponse>(basicResponse)

        assertEquals("20.4521401",decoded.amount)
        assertEquals("0.0300003",decoded.price)
        assertEquals(PriceR(9000190L,300003333L),decoded.priceR)
        assertEquals("native",decoded.buyingAssetType)
        assertEquals("credit_alphanum4",decoded.sellingAssetType)
        assertEquals("EURT",decoded.sellingAssetCode)
        assertEquals("GAP5LETOV6YIE62YAM56STDANPRDO7ZFDBGSNHJQIYGGKSMOZAHOOS2S",decoded.sellingAssetIssuer)
        assertEquals("0",decoded.offerId)


    }

    val basicResponse = """
    {
      "_links": {
        "self": {
          "href": "https://horizon.stellar.org/operations/124893981065674756"
        },
        "transaction": {
          "href": "https://horizon.stellar.org/transactions/3b41ec1411ed67ed47c96c34067c9fcfadf6e7cc013effa0b10f3df5ed758ffc"
        },
        "effects": {
          "href": "https://horizon.stellar.org/operations/124893981065674756/effects"
        },
        "succeeds": {
          "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=124893981065674756"
        },
        "precedes": {
          "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=124893981065674756"
        }
      },
      "id": "124893981065674756",
      "paging_token": "124893981065674756",
      "transaction_successful": true,
      "source_account": "GDT7WYNV6YBFJH3G6TX5K3ALBZY7A7A7CLIGXK4XZ6H5SROPS4UFGEMC",
      "type": "manage_buy_offer",
      "type_i": 12,
      "created_at": "2020-04-08T14:03:03Z",
      "transaction_hash": "3b41ec1411ed67ed47c96c34067c9fcfadf6e7cc013effa0b10f3df5ed758ffc",
      "amount": "20.4521401",
      "price": "0.0300003",
      "price_r": {
        "n": 9000190,
        "d": 300003333
      },
      "buying_asset_type": "native",
      "selling_asset_type": "credit_alphanum4",
      "selling_asset_code": "EURT",
      "selling_asset_issuer": "GAP5LETOV6YIE62YAM56STDANPRDO7ZFDBGSNHJQIYGGKSMOZAHOOS2S",
      "offer_id": "0"
    }
""".trimIndent()
}