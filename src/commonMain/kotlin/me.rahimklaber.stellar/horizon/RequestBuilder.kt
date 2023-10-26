package me.rahimklaber.stellar.horizon


import com.github.michaelbull.result.Result
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.base.Asset
import me.rahimklaber.stellar.horizon.operations.OperationResponse
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

typealias RequestResult<T> = Result<T, Throwable>

/**
 * Exception which indicates that a request is invalid.
 */
class InvalidRequest(message: String) : Exception(message)

abstract class RequestBuilder<T: Response>(
    protected val client: HttpClient, horizonUrlString: String,
    protected val urlExtension: String,
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
            is Asset.CreditAlphaNum -> queryParams["asset"] = "${asset.code}:${asset.issuer}"
        }
    }

    protected fun addPath(pathFragment: String): RequestBuilder<T> {
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

    protected fun buildUrl(extension : String = urlExtension): Url {
        val cloned = urlBuilder.clone()
        queryParamsToParameters().forEach(cloned.parameters::appendAll)
         return cloned
            .appendPathSegments(extension, *path.toTypedArray())
             .build()
    }

    protected suspend inline fun <reified T: Response>  inlineStream() = flow {
        var bodyChannel = `access$client`.get(`access$buildUrl`()) {
            header("Accept", ContentType.Text.EventStream)

        }.bodyAsChannel()

        var latestCursor = "0"

        while (true) {
            bodyChannel.awaitContent()
            val line = bodyChannel.readUTF8Line() ?: error("expected a line")

            when{
                line.startsWith("event") -> {
                    if (line.startsWith("event: close")){
                        cursor(latestCursor.toString())
                        bodyChannel = `access$client`.get(`access$buildUrl`()) {
                            header("Accept", ContentType.Text.EventStream)

                        }.bodyAsChannel()
                    }
                }
                line.startsWith("id") -> {}
                // length check to filter out "data: hello"
                line.startsWith("data") && line.length > 20 -> {
                    val data = `access$json`.decodeFromString<T>(line.drop(6))
                    latestCursor = data.pagingToken
                    emit(data)
                }
                else -> {
                    //todo add logging and log that we are ignoring the input
                }
            }

        }

    }

    abstract suspend fun callAsync(): RequestResult<Page<T>>

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
        addQueryParam("order",order.value)
        return this
    }

    @PublishedApi
    internal val `access$client`: HttpClient
        get() = client

    @PublishedApi
    internal fun `access$buildUrl`(extension: String = urlExtension) = buildUrl(extension)

    @PublishedApi
    internal val `access$json`: Json
        get() = json


}

enum class Order(val value: String) {
    DESC("desc"),
    ASC("asc")
}