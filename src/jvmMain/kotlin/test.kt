import com.github.michaelbull.result.orElseThrow
import com.ionspin.kotlin.crypto.signature.Signature
import io.ktor.utils.io.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.operations.Payment
import me.rahimklaber.stellar.horizon.Server
import me.rahimklaber.stellar.horizon.TransactionResponse
import me.rahimklaber.stellar.horizon.toAccount
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

    val server = Server("https://horizon-testnet.stellar.org")
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

//    val transaction = Transaction(
//        sourceAccount = account,
//        fee = 1000u,
//        sequenceNumber = server.accounts().account(account).orElseThrow().value.sequence + 1,
//        preconditions = Preconditions.None,
//        memo = Memo.None,
//        operations = listOf(
//            Payment(
//                destination = "GAPXFBCUZVX4YJ6D5JDUSAVHPZVAX4PPDM3V7HE5YH4Z7PSACDNYEXOS",
//                amount = tokenAmount(1_000_000_0),
//                asset = Asset.Native
//            )
//        ),
//        network = Network.TESTNET
//
//    )

    val source = server.accounts().account(account).orElseThrow().value.toAccount()
    val transaction = transactionBuilder(source, Network.TESTNET) {
        addOperation(
            Payment(
                destination = source.accountId,
                amount = tokenAmount(1_000_000_0),
                asset = Asset.Native
            )
        )
    }

    transaction.sign(keypair)

    server
        .transactions()
        .stream()
        .collect{
            println("""
                ledger: ${it.ledger}
                ledger: ${it.hash}
                pagingToken: ${it.pagingToken}
            """.trimIndent())
        }

//
//    println(
//        server.submitTransaction(transaction)
//    )
}