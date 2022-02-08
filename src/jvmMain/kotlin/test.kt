import com.github.michaelbull.result.get
import kotlinx.serialization.Serializable

@Serializable
data class User(val userId: Int, val id: Int, val title: String, val completed: Boolean)
suspend fun main() {

    val server = Server("https://horizon-testnet.stellar.org")
//    val res =
//        server.accounts().forSigner("GBMKMDDKDTFAUFEW6AUTD2GHF6YBBLEOVYYLNFMZA5I5UGCI3TUC74HY")
//            .callAsync().get()
//
//    println(res)
    println(server.transactions().transaction("a6ea4f092e2da0bd505faacaacc60bccc79a77ef8c14136eed91d362fff374cc"))
}