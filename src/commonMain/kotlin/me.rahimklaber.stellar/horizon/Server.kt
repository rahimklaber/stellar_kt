package me.rahimklaber.stellar.horizon

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.base.Transaction
import me.rahimklaber.stellar.base.xdr.toXdrString
import me.rahimklaber.stellar.horizon.AccountRequestBuilder
import me.rahimklaber.stellar.horizon.OperationsRequestBuilder
import me.rahimklaber.stellar.horizon.TransactionRequestBuilder


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

    suspend fun submitTransaction(transaction: Transaction): String/*: SubmitTransactionResponse*/ {
        val transactionXdr = transaction.toEnvelopeXdr().toXdrString()
        return submitTransactionXdr(transactionXdr)
    }

    suspend fun submitTransactionXdr(xdr: String): String/*: SubmitTransactionResponse*/ {
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
            }.bodyAsText()
    }

}