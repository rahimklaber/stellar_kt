package me.rahimklaber.stellar.horizon


import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.base.Asset
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set


/**
 * Exception which indicates that a request is invalid.
 */
class InvalidRequest(message: String) : Exception(message)

abstract class RequestBuilder<T : Response>(
    protected val client: HttpClient,
    horizonUrlString: String,
    internal var urlExtension: String,
    protected val json: Json = Json { ignoreUnknownKeys = true }
) {
    private val urlBuilder = URLBuilder(horizonUrlString)
    val queryParams = mutableMapOf<String, String>()
    private val path = mutableListOf<String>()

    protected fun addQueryParam(name: String, value: String) {
        queryParams[name] = value
    }

    protected fun checkQueryParam(name: String): String? = queryParams[name]
    protected fun addAssetQueryParam(asset: Asset) {
        when (asset) {
            is Asset.Native -> queryParams["asset"] = "native"
            is Asset.AlphaNum -> queryParams["asset"] = "${asset.code}:${asset.issuer}"
        }
    }

    internal fun addPath(pathFragment: String): RequestBuilder<T> {
        path.add(pathFragment)
        return this
    }

    protected fun queryParamsToParameters(): List<Parameters> {
        val parameters = mutableListOf<Parameters>()
        for ((name, value) in queryParams) {
            parameters.add(parametersOf(name, value))
        }
        return parameters
    }

    protected fun buildUrl(extension: String = urlExtension): Url {
        val cloned = urlBuilder.clone()
        queryParamsToParameters().forEach(cloned.parameters::appendAll)
        return cloned
            .appendPathSegments(extension, *path.toTypedArray())
            .build()
    }

    protected suspend inline fun <reified T : Response> inlineStream() = flow {
        var bodyChannel = accessxclient.get(accessxbuildUrl()) {
            header("Accept", ContentType.Text.EventStream)

        }.bodyAsChannel()

        var latestCursor = "0"

        while (true) {
            bodyChannel.awaitContent()
            val line = bodyChannel.readUTF8Line() ?: error("expected a line")

            when {
                line.startsWith("event") -> {
                    if (line.startsWith("event: close")) {
                        cursor(latestCursor.toString())
                        bodyChannel = accessxclient.get(accessxbuildUrl()) {
                            header("Accept", ContentType.Text.EventStream)

                        }.bodyAsChannel()
                    }
                }

                line.startsWith("id") -> {}
                // length check to filter out "data: hello"
                line.startsWith("data") && line.length > 20 -> {
                    val data = accessxjson.decodeFromString<T>(line.drop(6))
                    latestCursor = data.pagingToken
                    emit(data)
                }

                else -> {
                    //todo add logging and log that we are ignoring the input
                }
            }

        }

    }

    abstract suspend fun call(): Page<T>

    /**
     * Specifies the amount of records to return.
     * @param limit the amount of records to return.
     */
    open fun limit(limit: Int): RequestBuilder<T> {
        addQueryParam("limit", "$limit")
        return this
    }

    /**
     * Request records from a certain point onwards.
     */
    open fun cursor(cursor: String): RequestBuilder<T> {
        addQueryParam("cursor", cursor)
        return this
    }

    /**
     * defines the order of the response.
     */
    open fun order(order: Order): RequestBuilder<T> {
        addQueryParam("order", order.value)
        return this
    }

    @PublishedApi
    internal val accessxclient: HttpClient
        get() = client

    @PublishedApi
    internal fun accessxbuildUrl(extension: String = urlExtension) = buildUrl(extension)

    @PublishedApi
    internal val accessxjson: Json
        get() = json


}

enum class Order(val value: String) {
    DESC("desc"),
    ASC("asc")
}