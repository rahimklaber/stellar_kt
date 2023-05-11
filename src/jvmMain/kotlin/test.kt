import com.ionspin.kotlin.crypto.signature.Signature
import io.ktor.util.*
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.operations.Operation
import me.rahimklaber.stellar.base.operations.Payment
import me.rahimklaber.stellar.base.xdr.XdrStream
import kotlin.random.Random


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

suspend fun main() {

//    println(json.decodeFromString<TestI>(testAccountMerge))
//    println(json.decodeFromString<Links2>(links))

//    val server = Server("https://horizon.stellar.org")
//
//     var operations = server.operations()
//         .forAccount("GAAUMMCT5PVLB5SP7FJYDXKZYDFJLXLJ34EXFREMDWOZLKYVE2PNVZWO")
//         .limit(200)
//         .callAsync().unwrap()
//
//
//     while (true){
//
////         println(operations.records.first())
//         val res = operations.next(server)
//         if (res is Err<Throwable>){
//             res.getError()?.printStackTrace()
//             println(operations.links)
//             exitProcess(0)
//         }
//         operations = res.unwrap()

    KeyPair.random()
    val kp = Signature.keypair()
//     Signature.init()
    val msg = Random.nextBytes(32).toUByteArray()
    val signature = Signature.sign(msg, kp.secretKey)

    val account = "GAPXFBCUZVX4YJ6D5JDUSAVHPZVAX4PPDM3V7HE5YH4Z7PSACDNYEXOS"
    val keypair = KeyPair.fromSecretSeed("SDCIQUQKNIIDWSX4E46GQCO7ZR6PC4X7EA7D2LRQYMIFSZ6BGZV4I3YN")
    println(keypair.accountId)
    val stream = XdrStream()

    val transaction = Transaction(
        sourceAccount = account,
        fee = 1000u,
        sequenceNumber = 3327611811921923,
        preconditions = Preconditions.None,
        memo = Memo.None,
        operations = listOf(
            Payment(
                destination = account,
                asset = Asset.Native,
                amount = 1_000_000_0,
            )
        ),
        network = Network.TESTNET

    )
    transaction.sign(keypair)

    transaction.toEnvelopeXdr().encode(stream)
    val xdr = stream.buffer.snapshot().toByteArray().encodeBase64()
    println(xdr)
//     println("privkey: ${kp.secretKey.toHexString()}")
//     println("pubkey: ${kp.publicKey.toHexString()}")
//     println("msg: ${msg.toHexString()}")
//     println("signature: ${signature/*.take(64).toUByteArray()*/.toHexString()}")
//     println("signature: ${signature.size}")


}