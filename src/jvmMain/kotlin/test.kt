import arrow.core.computations.ResultEffect.bind
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import me.rahimklaber.stellar.horizon.AccountResponse
import me.rahimklaber.stellar.horizon.Page
import me.rahimklaber.stellar.horizon.next
import me.rahimklaber.stellar.horizon.prev

@Serializable
data class User(val userId: Int, val id: Int, val title: String, val completed: Boolean)
suspend fun main() {

    val server = Server("https://horizon-testnet.stellar.org")
    val res = server.accounts().forSigner("GBMKMDDKDTFAUFEW6AUTD2GHF6YBBLEOVYYLNFMZA5I5UGCI3TUC74HY")
        .callAsync().bind()
    println(res.records.first().links)

//    println(res)
//    println(
//        accountRequestBuilder.forAccount("GBAKUWF2HTJ325PH6VATZQ3UNTK2AGTATR43U52WQCYJ25JNSCF5OFUN")
//            .callAsync()
//    )
//    val res = server.client.get<User>("https://jsonplaceholder.typicode.com/todos/1")
//    println(res)
}