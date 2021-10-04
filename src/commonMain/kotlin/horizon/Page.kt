package horizon

import Server
import arrow.core.Either
import arrow.core.computations.either
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure

@Serializable(with = PageSerializer::class)
data class Page<T>(@SerialName("_records") val records: List<T>, val links: Links) {

    suspend fun next(server: Server): Either<Exception, Page<T>> {
        return if (links.next == null) {
            Either.Left(Exception("Cannot get next page")) // Todo: get next using api call
        } else {
            either{
                server.client.get(links.next.url)
            }
        }
    }
    @Serializable()
    class Links(val url: String, val self: Links?, val next: Links?, val prev: Links?)
}

@Serializable(with = EmbeddedSerializer::class)
class Embedded<T>(val _records : List<T>)

class EmbeddedSerializer<T>(val tSerializer: KSerializer<T>): KSerializer<Embedded<T>>{
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("page"){
            element("records", ListSerializer(tSerializer).descriptor)
        }

    override fun deserialize(decoder: Decoder): Embedded<T> =
        decoder.decodeStructure(descriptor){
            var records : List<T>? = null
            while (true){
                when(val index = decodeElementIndex(descriptor)){
                    0 -> records = decoder.decodeSerializableValue(ListSerializer(tSerializer))
                    DECODE_DONE -> break
                    else -> continue
                }
            }
            Embedded(records as List<T>)
        }


    override fun serialize(encoder: Encoder, value: Embedded<T>) {
        TODO("Not yet implemented")
    }
}


class PageSerializer<T>(val tSerializer: KSerializer<T>): KSerializer<Page<T>>{
    val delegatedEmbeddedSerializer = EmbeddedSerializer(tSerializer)
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("page"){
            element("_embedded", delegatedEmbeddedSerializer.descriptor)
        }

    override fun deserialize(decoder: Decoder): Page<T> =
        decoder.decodeStructure(descriptor){
            var records : List<T>? = null
            while (true){
                when(val index = decodeElementIndex(descriptor)){
                    0 -> records = decoder.decodeSerializableValue(delegatedEmbeddedSerializer)._records
                    DECODE_DONE -> break
                    else -> {
                        println(index);
                        continue
                    }
                }
            }
            Page(records as List<T>,Page.Links("",null,null,null))
        }

    override fun serialize(encoder: Encoder, value: Page<T>) {
        TODO("Not yet implemented")
    }
}
