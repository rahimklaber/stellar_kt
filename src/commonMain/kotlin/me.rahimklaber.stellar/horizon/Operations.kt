package me.rahimklaber.stellar.horizon


import com.github.michaelbull.result.Result
import com.github.michaelbull.result.runCatching
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.get
import kotlinx.serialization.KSerializer
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import me.rahimklaber.stellar.horizon.Page
import me.rahimklaber.stellar.horizon.RequestBuilder
import me.rahimklaber.stellar.horizon.RequestResult
import me.rahimklaber.stellar.horizon.TransactionResponse
import me.rahimklaber.stellar.horizon.operations.OperationResponse
//import me.rahimklaber.stellar.horizon.operations.PaymentResponse


class OperationsRequestBuilder(client: HttpClient, horizonUrl: String) :
    RequestBuilder<OperationResponse>(client, horizonUrl, "operations") {
    private var forAccount = false
    private var forLedger = false
    private var forTransaction = false



    fun forAccount(accountId : String) : OperationsRequestBuilder{
        forAccount = true
        addPath(accountId)
        addPath("operations")
        return this
    }

    fun forLedger(ledger : ULong) : OperationsRequestBuilder{
        forLedger = true
        addPath(ledger.toString())
        addPath("operations")
        return this
    }

    fun forTransaction(transactionId : String) : OperationsRequestBuilder{
        forTransaction = true
        addPath(transactionId)
        addPath("operations")
        return this
    }

    fun includeFailed(include : Boolean) : OperationsRequestBuilder{
        addQueryParam("include_failed",include.toString())
        return this
    }


    suspend fun operation(operationId : ULong): RequestResult<OperationResponse> {
        addPath(operationId.toString())
        return runCatching {
            client.get(buildUrl()).body()
        }
    }
    override fun limit(limit: Int): OperationsRequestBuilder {
        super.limit(limit)
        return this
    }

    override fun cursor(cursor: String): OperationsRequestBuilder {
        super.cursor(cursor)
        return this
    }

    override fun order(order: Order): OperationsRequestBuilder {
        super.order(order)
        return this
    }


    override suspend fun callAsync(): RequestResult<Page<OperationResponse>> {
        return runCatching {
            val extension = if(forAccount){
                "accounts"
            }else if(forLedger){
                "ledgers"
            }else if(forTransaction){
                "transactions"
            }
            else{
                urlExtension
            }
            val url = buildUrl(extension)
            println(url)
            client.get(url).body()
        }
    }

}





//object OperationResponseSerializer : KSerializer<OperationResponse> {
//    override val descriptor: SerialDescriptor =
//        buildClassSerialDescriptor("operation") {
//            element("type_i", PrimitiveSerialDescriptor("type_i", PrimitiveKind.INT))
//        }
//
//    private fun typeToSerializer(type: Int) : KSerializer<OperationResponse>{
//        return when(type){
//            1 -> PaymentResponse.serializer()
//            else -> throw NotImplementedError()
//        } as KSerializer<OperationResponse>
//    }
//
//    override fun deserialize(decoder: Decoder): OperationResponse {
//        var type: Int? = null
//
//        PolymorphicSerializer
//        decoder.decodeStructure(descriptor) {
//
//            while(true){
//                when(val decodedIndex = decodeElementIndex(descriptor)){
//                    0 -> {
//                        type = decoder.decodeInt()
//                        break
//                    }
//                    else -> continue
//                }
//            }
//        }
//        require(type != null)
//        return typeToSerializer(type!!).deserialize(decoder)
//    }
//
//
//    override fun serialize(encoder: Encoder, value: OperationResponse) {
//        TODO("Not yet implemented")
//    }
//}