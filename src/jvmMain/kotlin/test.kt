import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.get
import kotlinx.serialization.Serializable
import me.rahimklaber.stellar.horizon.Order

@Serializable
data class User(val userId: Int, val id: Int, val title: String, val completed: Boolean)
suspend fun main() {

    val server = Server("https://horizon-testnet.stellar.org")
//    val res =
//        server.accounts().forSigner("GBMKMDDKDTFAUFEW6AUTD2GHF6YBBLEOVYYLNFMZA5I5UGCI3TUC74HY")
//            .callAsync().get()
//
//    println(res)
    val response = server.transactions()
        .limit(1)
        .forLedger(906102)
    println(when(response){
        is Ok -> response.value
        is Err -> response.error
    })
}