package me.rahimklaber.stellar.horizon

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.base.Transaction
import me.rahimklaber.stellar.base.xdr.toXdrBase64


class Server(val horizonUrl: String) {
    val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                this.ignoreUnknownKeys = true
            })
        }
    }

    fun accounts() = AccountRequestBuilder(client, horizonUrl)
    fun transactions() = TransactionRequestBuilder(client, horizonUrl)
    fun operations() = OperationsRequestBuilder(client, horizonUrl)

    suspend fun submitTransaction(transaction: Transaction): SubmitTransactionResponse {
        val transactionXdr = transaction.toEnvelopeXdr().toXdrBase64()
        return submitTransactionXdr(transactionXdr)
    }

    suspend fun submitTransactionXdr(xdr: String): SubmitTransactionResponse {
        val url = URLBuilder(horizonUrl)
        url.path("/transactions")

        return client
            .post(url.build()) {
                setBody(
                    FormDataContent(
                        parameters {
                            append("tx", xdr)
                        }
                    )
                )
            }.body()
    }

}