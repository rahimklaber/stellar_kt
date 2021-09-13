package horizon

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*


abstract class RequestBuilder<T>(protected val client: HttpClient, horizonUrlString: String,
                              private val urlExtension : String){
    private val urlBuilder = URLBuilder(horizonUrlString)
    val queryParams = mutableMapOf<String,String>()
    private val path = mutableListOf<String>()


    protected fun addPath(pathFragment : String): RequestBuilder<T> {
        path.add(pathFragment)
        return this
    }

    protected fun buildUrl(): Url {
        return urlBuilder.path(listOf(urlExtension) + path).build()
    }

    abstract suspend fun callAsync() : T

}

abstract class HorizonResponse()