import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import me.rahimklaber.stellar.horizon.AccountRequestBuilder


class Server(val horizonUrl: String) {
    val client: HttpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
    }

    fun accounts() = AccountRequestBuilder(client, horizonUrl)

}