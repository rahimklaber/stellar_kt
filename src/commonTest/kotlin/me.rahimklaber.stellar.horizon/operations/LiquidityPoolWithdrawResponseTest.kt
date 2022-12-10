package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class LiquidityPoolWithdrawResponseTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun basic() {
        val decoded = json.decodeFromString<LiquidityPoolWithdrawResponse>(basicResponse)
        assertEquals("a468d41d8e9b8f3c7209651608b74b7db7ac9952dcae0cdf24871d1d9c7b0088",
        decoded.liquidityPoolId)
        assertEquals(listOf(
            LiquidityPoolResponseAsset(
                asset = "native",
                amount = "43.6857654"
            ),
            LiquidityPoolResponseAsset(
                asset = "USDC:GA5ZSEJYB37JRC5AVCIA5MOP4RHTM335X2KGX3IHOJAPP5RE34K4KZVN",
                amount = "5.0953943"
            )
        ), decoded.reservesMin)
        assertEquals("14.2559585", decoded.shares)
        assertEquals(listOf(
            LiquidityPoolResponseAsset(
                asset = "native",
                amount = "43.8318716"
            ),
            LiquidityPoolResponseAsset(
                asset = "USDC:GA5ZSEJYB37JRC5AVCIA5MOP4RHTM335X2KGX3IHOJAPP5RE34K4KZVN",
                amount = "5.1124357"
            )
        ), decoded.reservesReceived)
    }

    val basicResponse = """
{
  "_links": {
    "self": {
      "href": "https://horizon.stellar.org/operations/178352412502749185"
    },
    "transaction": {
      "href": "https://horizon.stellar.org/transactions/7f98b10bed2e4e755cfb831b28cf547cde3fd72ff3d78e25b82cb25e8335a679"
    },
    "effects": {
      "href": "https://horizon.stellar.org/operations/178352412502749185/effects"
    },
    "succeeds": {
      "href": "https://horizon.stellar.org/effects?order=desc&cursor=178352412502749185"
    },
    "precedes": {
      "href": "https://horizon.stellar.org/effects?order=asc&cursor=178352412502749185"
    }
  },
  "id": "178352412502749185",
  "paging_token": "178352412502749185",
  "transaction_successful": true,
  "source_account": "GBCBBAGIYJD7LCLDYVCYCLUHNPMLJ6GTWF2MKRAZLJBYBU5GB4CQFJVK",
  "type": "liquidity_pool_withdraw",
  "type_i": 23,
  "created_at": "2022-06-28T17:43:18Z",
  "transaction_hash": "7f98b10bed2e4e755cfb831b28cf547cde3fd72ff3d78e25b82cb25e8335a679",
  "liquidity_pool_id": "a468d41d8e9b8f3c7209651608b74b7db7ac9952dcae0cdf24871d1d9c7b0088",
  "reserves_min": [
    {
      "asset": "native",
      "amount": "43.6857654"
    },
    {
      "asset": "USDC:GA5ZSEJYB37JRC5AVCIA5MOP4RHTM335X2KGX3IHOJAPP5RE34K4KZVN",
      "amount": "5.0953943"
    }
  ],
  "shares": "14.2559585",
  "reserves_received": [
    {
      "asset": "native",
      "amount": "43.8318716"
    },
    {
      "asset": "USDC:GA5ZSEJYB37JRC5AVCIA5MOP4RHTM335X2KGX3IHOJAPP5RE34K4KZVN",
      "amount": "5.1124357"
    }
  ]
}
    """.trimIndent()
}