package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class PaymentResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<PaymentResponse>(basicResponse)

        assertEquals("credit_alphanum4",decoded.assetType)
        assertEquals("NGNT",decoded.assetCode)
        assertEquals("GAWODAROMJ33V5YDFY3NPYTHVYQG7MJXVJ2ND3AOGIHYRWINES6ACCPD",decoded.assetIssuer)
        assertEquals("GCAXBKU3AKYJPLQ6PEJ6L47KOATCYCBJ2NFRGAK7FUUA2DCEUC265SU2",decoded.from)
        assertEquals("GC2QCKFI3DOBEYVBONPVNA2PMLU225IKKI6XPENMWR2CTWSFBAOU7T34",decoded.to)
        assertEquals("5.0000000",decoded.amount)



    }

    val basicResponse = """
    {
      "_links": {
        "self": {
          "href": "https://horizon.stellar.org/operations/122511124621283329"
        },
        "transaction": {
          "href": "https://horizon.stellar.org/transactions/452a180790caf4dbe658d996316cd727ce5573f5f0a77790da540cc49214fe80"
        },
        "effects": {
          "href": "https://horizon.stellar.org/operations/122511124621283329/effects"
        },
        "succeeds": {
          "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=122511124621283329"
        },
        "precedes": {
          "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=122511124621283329"
        }
      },
      "id": "122511124621283329",
      "paging_token": "122511124621283329",
      "transaction_successful": true,
      "source_account": "GCAXBKU3AKYJPLQ6PEJ6L47KOATCYCBJ2NFRGAK7FUUA2DCEUC265SU2",
      "type": "payment",
      "type_i": 1,
      "created_at": "2020-03-04T22:46:47Z",
      "transaction_hash": "452a180790caf4dbe658d996316cd727ce5573f5f0a77790da540cc49214fe80",
      "asset_type": "credit_alphanum4",
      "asset_code": "NGNT",
      "asset_issuer": "GAWODAROMJ33V5YDFY3NPYTHVYQG7MJXVJ2ND3AOGIHYRWINES6ACCPD",
      "from": "GCAXBKU3AKYJPLQ6PEJ6L47KOATCYCBJ2NFRGAK7FUUA2DCEUC265SU2",
      "to": "GC2QCKFI3DOBEYVBONPVNA2PMLU225IKKI6XPENMWR2CTWSFBAOU7T34",
      "amount": "5.0000000"
    }
""".trimIndent()
}