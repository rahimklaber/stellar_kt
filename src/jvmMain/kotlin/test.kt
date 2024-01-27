import com.github.michaelbull.result.expect
import com.github.michaelbull.result.map
import kotlinx.serialization.json.*
import me.rahimklaber.stellar.JsonRpcClient
import me.rahimklaber.stellar.JsonRpcRequest
import me.rahimklaber.stellar.base.*
import me.rahimklaber.stellar.base.operations.InvokeHostFunction
import me.rahimklaber.stellar.base.xdr.*
import me.rahimklaber.stellar.base.xdr.soroban.SCMap
import me.rahimklaber.stellar.base.xdr.soroban.SCMapEntry
import me.rahimklaber.stellar.base.xdr.soroban.SCSymbol
import me.rahimklaber.stellar.base.xdr.soroban.SCVal
import me.rahimklaber.stellar.horizon.Server
import me.rahimklaber.stellar.horizon.toAccount
import kotlin.io.encoding.ExperimentalEncodingApi


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

public data class SplitRecipientContract(
    public val address: me.rahimklaber.stellar.base.xdr.soroban.SCVal.Address,
    public val args: kotlin.collections.List<kotlin.Any>,
    public val function: kotlin.String,
) {
    public fun toScVal(): me.rahimklaber.stellar.base.xdr.soroban.SCVal {
        return SCVal.Map(
            SCMap(
                listOf(
                    SCMapEntry(SCVal.Symbol(SCSymbol("address")), address.toScVal()),
                    SCMapEntry(SCVal.Symbol(SCSymbol("args")), args.toScVal()),
                    SCMapEntry(SCVal.Symbol(SCSymbol("function")), function.toScVal(me.rahimklaber.stellar.base.xdr.sorobanspec.SCSpecTypeDef.Symbol)),

                    )
            )
        )
    }
}
@OptIn(ExperimentalStdlibApi::class, ExperimentalEncodingApi::class)
suspend fun main() {

    val jsonRpcClient = JsonRpcClient("https://soroban-testnet.stellar.org")

    val horizon = Server("https://horizon-testnet.stellar.org")

    val keypair = KeyPair.fromSecretSeed("SD3R3XBV4YU33EHVTW5FOZ3HKKJHH3B5QFW6NNJOZ34GQI3XG24ETIIZ")

    val account = horizon.accounts().account(keypair.accountId)
        .map {
            it.toAccount()
        }.expect { }


    val tx = transactionBuilder(account, Network.TESTNET) {
        addOperation(
            InvokeHostFunction(
                InvokeHostFunctionOp(
                    HostFunction.InvokeContract(
                        InvokeContractArgs(
                            StrKey.encodeToScAddress("CDZRZMZECOWC6VJ2M2JG5Y53PPYGIOHHHWYZKWCTMWIQ3UF6QRJ2VY4I"),
                            SCSymbol("hello"),
                            listOf(SCVal.Symbol(SCSymbol("hello")))
                        ),
                    ),
                    listOf()
                ),
            )
        )
        setFee(100_000u)
    }

    tx.sign(keypair)

    val params = buildJsonObject {
        put("transaction", JsonPrimitive(tx.toEnvelopeXdr().toXdrString()))
    }

    val response = jsonRpcClient.executeRequest(JsonRpcRequest("simulateTransaction", params))
    val sorobanData = response["result"]!!.jsonObject["transactionData"]!!.jsonPrimitive.content
    println(sorobanData)
    val withSorobanData = tx.withSorobanData(SorobanTransactionData.decodeFromString(sorobanData))
    withSorobanData.sign(keypair)
    println("hi " + withSorobanData.toEnvelopeXdr().toXdrString())

//    val specbase64 = "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"
//
//    val stream = XdrStream()
//    stream.writeBytes(Base64.decode(specbase64))
//    val specs = mutableListOf<SCSpecEntry>()
//
//    do{
//        try {
//            specs.add(SCSpecEntry.decode(stream))
//        }catch (e: Exception){
//            println(e)
//            break
//        }
//    }while (true)
//
//    while (!KeyPair.isInit){
//        delay(200)
//    }

//    println(
//        SplitRecipientContract(
//            SCVal.Address(ScAddress.Account(StrKey.encodeToAccountIDXDR(KeyPair.random().accountId))),
//            listOf(SCVal.Symbol(SCSymbol("hi"))),
//            "test"
//        ).toScVal()
//            .toXdrString()
//    )

//    println(wrapperForSpecEntry(specs.first()))



}