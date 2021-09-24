import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class User(val userId: Int, val id: Int, val title: String, val completed: Boolean)
suspend fun main() {

    val server = Server("https://horizon-testnet.stellar.org")
    val res = server.accounts().account("GB543WLQE7P7WDSFLCYNTU74ESUUPE4HOH6JH5SMK5J26TEFRH6YLDUB")
    println(res)
//    println(
//        accountRequestBuilder.forAccount("GBAKUWF2HTJ325PH6VATZQ3UNTK2AGTATR43U52WQCYJ25JNSCF5OFUN")
//            .callAsync()
//    )
//    val res = server.client.get<User>("https://jsonplaceholder.typicode.com/todos/1")
//    println(res)
}