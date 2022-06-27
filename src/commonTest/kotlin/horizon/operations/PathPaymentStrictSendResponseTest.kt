package horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.horizon.operations.PathAsset
import me.rahimklaber.stellar.horizon.operations.PathPaymentStrictReceiveResponse
import me.rahimklaber.stellar.horizon.operations.PathPaymentStrictSendResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class PathPaymentStrictSendResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<PathPaymentStrictSendResponse>(basicResponse)

        assertEquals("credit_alphanum4",decoded.assetType)
        assertEquals("BRL",decoded.assetCode)
        assertEquals("GDVKY2GU2DRXWTBEYJJWSFXIGBZV6AZNBVVSUHEPZI54LIS6BA7DVVSP",decoded.assetIssuer)
        assertEquals("GBZH7S5NC57XNHKHJ75C5DGMI3SP6ZFJLIKW74K6OSMA5E5DFMYBDD2Z",decoded.from)
        assertEquals("GBZH7S5NC57XNHKHJ75C5DGMI3SP6ZFJLIKW74K6OSMA5E5DFMYBDD2Z",decoded.to)
        assertEquals("26.5544244",decoded.amount)
        assertEquals(listOf(
            PathAsset(assetType = "credit_alphanum4", assetCode = "EURT", assetIssuer = "GAP5LETOV6YIE62YAM56STDANPRDO7ZFDBGSNHJQIYGGKSMOZAHOOS2S"),
            PathAsset(assetType = "native")
        ),decoded.path)
        assertEquals("5.0000000",decoded.sourceAmount)
        assertEquals("26.5544244",decoded.destinationMin)
        assertEquals("GDUKMGUGDZQK6YHYA5Z6AY2G4XDSZPSZ3SW5UN3ARVMO6QSRDWP5YLEX",decoded.sourceAssetIssuer)
        assertEquals("USD",decoded.sourceAssetCode)
        assertEquals("credit_alphanum4",decoded.sourceAssetType)



    }

    val basicResponse = """
       {
      "_links": {
        "self": {
          "href": "https://horizon.stellar.org/operations/124624072438579201"
        },
        "transaction": {
          "href": "https://horizon.stellar.org/transactions/2b863994825fe85b80bfdff433b348d5ce80b23cd9ee2a56dcd6ee1abd52c9f8"
        },
        "effects": {
          "href": "https://horizon.stellar.org/operations/124624072438579201/effects"
        },
        "succeeds": {
          "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=124624072438579201"
        },
        "precedes": {
          "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=124624072438579201"
        }
      },
      "id": "124624072438579201",
      "paging_token": "124624072438579201",
      "transaction_successful": true,
      "source_account": "GBZH7S5NC57XNHKHJ75C5DGMI3SP6ZFJLIKW74K6OSMA5E5DFMYBDD2Z",
      "type": "path_payment_strict_send",
      "type_i": 13,
      "created_at": "2020-04-04T13:47:50Z",
      "transaction_hash": "2b863994825fe85b80bfdff433b348d5ce80b23cd9ee2a56dcd6ee1abd52c9f8",
      "asset_type": "credit_alphanum4",
      "asset_code": "BRL",
      "asset_issuer": "GDVKY2GU2DRXWTBEYJJWSFXIGBZV6AZNBVVSUHEPZI54LIS6BA7DVVSP",
      "from": "GBZH7S5NC57XNHKHJ75C5DGMI3SP6ZFJLIKW74K6OSMA5E5DFMYBDD2Z",
      "to": "GBZH7S5NC57XNHKHJ75C5DGMI3SP6ZFJLIKW74K6OSMA5E5DFMYBDD2Z",
      "amount": "26.5544244",
      "path": [
        {
          "asset_type": "credit_alphanum4",
          "asset_code": "EURT",
          "asset_issuer": "GAP5LETOV6YIE62YAM56STDANPRDO7ZFDBGSNHJQIYGGKSMOZAHOOS2S"
        },
        {
          "asset_type": "native"
        }
      ],
      "source_amount": "5.0000000",
      "destination_min": "26.5544244",
      "source_asset_type": "credit_alphanum4",
      "source_asset_code": "USD",
      "source_asset_issuer": "GDUKMGUGDZQK6YHYA5Z6AY2G4XDSZPSZ3SW5UN3ARVMO6QSRDWP5YLEX"
    }
""".trimIndent()
}