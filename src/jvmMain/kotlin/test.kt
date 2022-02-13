import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.get
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.horizon.Order
import me.rahimklaber.stellar.horizon.operations.OperationResponse
import me.rahimklaber.stellar.horizon.operations.PaymentResponse


val testPaymentResponse = """
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

private val json = Json {
    ignoreUnknownKeys = true
}

suspend fun main() {

    val server = Server("https://horizon-testnet.stellar.org")

    val decodedPaymentResponse = json.decodeFromString<OperationResponse>(testPaymentResponse)
    println(decodedPaymentResponse)

//    val res =
//        server.accounts().forSigner("GBMKMDDKDTFAUFEW6AUTD2GHF6YBBLEOVYYLNFMZA5I5UGCI3TUC74HY")
//            .callAsync().get()
//
//    println(res)
//    val response = server.transactions()
//        .limit(1)
//        .order(Order.DESC)
//        .forLiquidityPool("586137aefc278a84c021895076d93342b7b1d61ee6b5e8ef87f50273412ace9a")
//    println(when(response){
//        is Ok -> response.value
//        is Err -> response.error
//    })

}