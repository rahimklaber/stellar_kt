package horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.horizon.operations.LiquidityPoolDepositResponse
import me.rahimklaber.stellar.horizon.operations.LiquidityPoolWithdrawResponse
import me.rahimklaber.stellar.horizon.operations.LiquidityPoolResponseAsset
import me.rahimklaber.stellar.horizon.operations.PriceR
import kotlin.test.Test
import kotlin.test.assertEquals

class LiquidityPoolDepositResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<LiquidityPoolDepositResponse>(basicResponse)
        assertEquals("abcdef",decoded.liquidityPoolId)
        assertEquals(listOf(
            LiquidityPoolResponseAsset(asset = "JPY:GBVAOIACNSB7OVUXJYC5UE2D4YK2F7A24T7EE5YOMN4CE6GCHUTOUQXM",
                amount = "1000.0000005"),
            LiquidityPoolResponseAsset(asset = "EURT:GAP5LETOV6YIE62YAM56STDANPRDO7ZFDBGSNHJQIYGGKSMOZAHOOS2S",
            amount = "3000.0000005")
        ),decoded.reservesMax)
        assertEquals("0.2680000",decoded.minPrice)
        assertEquals(PriceR(
            n = 67,
            d = 250
        ),decoded.minPriceR)
        assertEquals("0.3680000",decoded.maxPrice)
        assertEquals(PriceR(
            n = 73,
            d = 250
        ),decoded.maxPriceR)
        assertEquals(listOf(
            LiquidityPoolResponseAsset(
                asset = "JPY:GBVAOIACNSB7OVUXJYC5UE2D4YK2F7A24T7EE5YOMN4CE6GCHUTOUQXM",
                amount = "983.0000005"
            ),
            LiquidityPoolResponseAsset(
                asset = "EURT:GAP5LETOV6YIE62YAM56STDANPRDO7ZFDBGSNHJQIYGGKSMOZAHOOS2S",
                amount = "2378.0000005"
            )
        ), decoded.reservesDeposited)
        assertEquals("1000",decoded.sharesReceived)

    }

    val basicResponse = """
{
  "_links": {
    "self": {
      "href": "https://horizon.stellar.org/operations/178352279358488577"
    },
    "transaction": {
      "href": "https://horizon.stellar.org/transactions/11953ffe58ccfb8b83e4ad8e1afac5433f7387cf1a3c6cbafb6ea784dc096505"
    },
    "effects": {
      "href": "https://horizon.stellar.org/operations/178352279358488577/effects"
    },
    "succeeds": {
      "href": "https://horizon.stellar.org/effects?order=desc&cursor=178352279358488577"
    },
    "precedes": {
      "href": "https://horizon.stellar.org/effects?order=asc&cursor=178352279358488577"
    }
  },
  "id": "3697472920621057",
  "paging_token": "3697472920621057",
  "transaction_successful": true,
  "source_account": "GBB4JST32UWKOLGYYSCEYBHBCOFL2TGBHDVOMZP462ET4ZRD4ULA7S2L",
  "type": "liquidity_pool_deposit",
  "type_i": 22,
  "created_at": "2021-11-18T03:47:47Z",
  "transaction_hash": "43ed5ce19190822ec080b67c3ccbab36a56bc34102b1a21d3ee690ed3bc23378",
  "liquidity_pool_id": "abcdef",
  "reserves_max": [
    {
      "asset": "JPY:GBVAOIACNSB7OVUXJYC5UE2D4YK2F7A24T7EE5YOMN4CE6GCHUTOUQXM",
      "amount": "1000.0000005"
    },
    {
      "asset": "EURT:GAP5LETOV6YIE62YAM56STDANPRDO7ZFDBGSNHJQIYGGKSMOZAHOOS2S",
      "amount": "3000.0000005"
    }
  ],
  "min_price": "0.2680000",
  "min_price_r": {
    "n": 67,
    "d": 250
  },
  "max_price": "0.3680000",
  "max_price_r": {
    "n": 73,
    "d": 250
  },
  "reserves_deposited": [
    {
      "asset": "JPY:GBVAOIACNSB7OVUXJYC5UE2D4YK2F7A24T7EE5YOMN4CE6GCHUTOUQXM",
      "amount": "983.0000005"
    },
    {
      "asset": "EURT:GAP5LETOV6YIE62YAM56STDANPRDO7ZFDBGSNHJQIYGGKSMOZAHOOS2S",
      "amount": "2378.0000005"
    }
  ],
  "shares_received": "1000"
}
    """.trimIndent()
}