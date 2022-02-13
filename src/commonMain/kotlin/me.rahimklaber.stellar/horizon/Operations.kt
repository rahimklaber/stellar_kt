package me.rahimklaber.stellar.horizon


import io.ktor.client.*
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
import me.rahimklaber.stellar.horizon.operations.PaymentResponse


//class OperationsRequestBuilder(client: HttpClient, horizonUrl: String) :
//    RequestBuilder<TransactionResponse>(client, horizonUrl, "transactions") {
//    override suspend fun callAsync(): RequestResult<Page<>> {
//        TODO("Not yet implemented")
//    }
//
//}





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