import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.horizon.AccountRequestBuilder
import me.rahimklaber.stellar.horizon.TransactionRequestBuilder


class Server(val horizonUrl: String) {
    val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    fun accounts() = AccountRequestBuilder(client, horizonUrl)
    fun transactions() = TransactionRequestBuilder(client,horizonUrl)

}