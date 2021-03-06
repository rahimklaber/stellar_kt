package me.rahimklaber.stellar.horizon


import com.github.michaelbull.result.Result
import io.ktor.client.*
import io.ktor.http.*
import me.rahimklaber.stellar.base.Asset
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

typealias RequestResult<T> = Result<T, Throwable>

/**
 * Exception which indicates that a request is invalid.
 */
class InvalidRequest(message: String) : Exception(message)

abstract class RequestBuilder<T>(
    protected val client: HttpClient, horizonUrlString: String,
    private val urlExtension: String
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
        queryParamsToParameters().forEach(urlBuilder.parameters::appendAll)
        return urlBuilder
            .path(listOf(extension) + path).build()
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


}

enum class Order(val value: String) {
    DESC("desc"),
    ASC("asc")
}