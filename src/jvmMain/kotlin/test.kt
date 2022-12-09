import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonClassDiscriminator
import me.rahimklaber.stellar.horizon.HrefSerializer
import me.rahimklaber.stellar.horizon.Page
import me.rahimklaber.stellar.horizon.operations.OperationResponse


val testAccountMerge = """
{

      "_links": {
        "self": {
          "href": "https://horizon.stellar.org/operations/121887714411839489"
        },
        "transaction": {
          "href": "https://horizon.stellar.org/transactions/02077009a551ec94c776f83529293dcfc1c2cd5b38af043ef7f3699bf5a71f0a"
        },
        "effects": {
          "href": "https://horizon.stellar.org/operations/121887714411839489/effects"
        },
        "succeeds": {
          "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=121887714411839489"
        },
        "precedes": {
          "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=121887714411839489"
        }
      },
      "id": "121887714411839489",
      "paging_token": "121887714411839489",
      "transaction_successful": true,
      "source_account": "GCVLWV5B3L3YE6DSCCMHLCK7QIB365NYOLQLW3ZKHI5XINNMRLJ6YHVX",
      "type_i": 8,
      "created_at": "2020-02-24T17:03:00Z",
      "transaction_hash": "02077009a551ec94c776f83529293dcfc1c2cd5b38af043ef7f3699bf5a71f0a",
      "account": "GCVLWV5B3L3YE6DSCCMHLCK7QIB365NYOLQLW3ZKHI5XINNMRLJ6YHVX",
      "into": "GATL3ETTZ3XDGFXX2ELPIKCZL7S5D2HY3VK4T7LRPD6DW5JOLAEZSZBA",
      "type": "account_merge"
}
""".trimIndent()



private val json = Json {
    ignoreUnknownKeys = true
}

 fun main() {

//    println(json.decodeFromString<TestI>(testAccountMerge))
//    println(json.decodeFromString<Links2>(links))

//    val server = Server("https://horizon-testnet.stellar.org")
//
    val decodedPaymentResponse = json.decodeFromString<OperationResponse>(testAccountMerge)
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