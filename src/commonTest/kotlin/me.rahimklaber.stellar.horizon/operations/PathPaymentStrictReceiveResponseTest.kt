package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class PathPaymentStrictReceiveResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<PathPaymentStrictReceiveResponse>(basicResponse)

        assertEquals("credit_alphanum4",decoded.assetType)
        assertEquals("BRL",decoded.assetCode)
        assertEquals("GDVKY2GU2DRXWTBEYJJWSFXIGBZV6AZNBVVSUHEPZI54LIS6BA7DVVSP",decoded.assetIssuer)
        assertEquals("GBZH7S5NC57XNHKHJ75C5DGMI3SP6ZFJLIKW74K6OSMA5E5DFMYBDD2Z",decoded.from)
        assertEquals("GBZH7S5NC57XNHKHJ75C5DGMI3SP6ZFJLIKW74K6OSMA5E5DFMYBDD2Z",decoded.to)
        assertEquals("0.1000000",decoded.amount)
        assertEquals(listOf(
            PathAsset(assetType = "credit_alphanum4", assetCode = "USD", assetIssuer = "GBUYUAI75XXWDZEKLY66CFYKQPET5JR4EENXZBUZ3YXZ7DS56Z4OKOFU"),
            PathAsset(assetType = "native")
        ),decoded.path)
        assertEquals("0.0198773",decoded.sourceAmount)
        assertEquals("0.0198774",decoded.sourceMax)
        assertEquals("GDUKMGUGDZQK6YHYA5Z6AY2G4XDSZPSZ3SW5UN3ARVMO6QSRDWP5YLEX",decoded.sourceAssetIssuer)
        assertEquals("USD",decoded.sourceAssetCode)
        assertEquals("credit_alphanum4",decoded.sourceAssetType)



    }

    val basicResponse = """
    {
      "_links": {
        "self": {
          "href": "https://horizon.stellar.org/operations/124018825644490753"
        },
        "transaction": {
          "href": "https://horizon.stellar.org/transactions/2624935eefedc195562623d982e501ba2a183382959fa0b9d03cf66dced3b332"
        },
        "effects": {
          "href": "https://horizon.stellar.org/operations/124018825644490753/effects"
        },
        "succeeds": {
          "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=124018825644490753"
        },
        "precedes": {
          "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=124018825644490753"
        }
      },
      "id": "124018825644490753",
      "paging_token": "124018825644490753",
      "transaction_successful": true,
      "source_account": "GBZH7S5NC57XNHKHJ75C5DGMI3SP6ZFJLIKW74K6OSMA5E5DFMYBDD2Z",
      "type": "path_payment_strict_receive",
      "type_i": 2,
      "created_at": "2020-03-26T19:33:55Z",
      "transaction_hash": "2624935eefedc195562623d982e501ba2a183382959fa0b9d03cf66dced3b332",
      "asset_type": "credit_alphanum4",
      "asset_code": "BRL",
      "asset_issuer": "GDVKY2GU2DRXWTBEYJJWSFXIGBZV6AZNBVVSUHEPZI54LIS6BA7DVVSP",
      "from": "GBZH7S5NC57XNHKHJ75C5DGMI3SP6ZFJLIKW74K6OSMA5E5DFMYBDD2Z",
      "to": "GBZH7S5NC57XNHKHJ75C5DGMI3SP6ZFJLIKW74K6OSMA5E5DFMYBDD2Z",
      "amount": "0.1000000",
      "path": [
        {
          "asset_type": "credit_alphanum4",
          "asset_code": "USD",
          "asset_issuer": "GBUYUAI75XXWDZEKLY66CFYKQPET5JR4EENXZBUZ3YXZ7DS56Z4OKOFU"
        },
        {
          "asset_type": "native"
        }
      ],
      "source_amount": "0.0198773",
      "source_max": "0.0198774",
      "source_asset_type": "credit_alphanum4",
      "source_asset_code": "USD",
      "source_asset_issuer": "GDUKMGUGDZQK6YHYA5Z6AY2G4XDSZPSZ3SW5UN3ARVMO6QSRDWP5YLEX"
    }
""".trimIndent()
}