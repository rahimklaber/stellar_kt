package me.rahimklaber.stellar.horizon

import Server
import arrow.core.Either
import arrow.core.computations.either
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.*
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
                server.client.get(links.next)
            }
        }
    }
    @Serializable(with = LinksSerializer::class)
    data class Links(val self: String, val next: String, val prev: String)
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
                when(decodeElementIndex(descriptor)){
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

class HrefSerializer(): KSerializer<String>{

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("hrefobj"){
            element("href", PrimitiveSerialDescriptor("href",PrimitiveKind.STRING))
        }

    override fun deserialize(decoder: Decoder): String =
        decoder.decodeStructure(descriptor){
            var href = ""
            while (true){
                when(decodeElementIndex(descriptor)){
                    0 -> href = decoder.decodeString()
                    DECODE_DONE -> break
                    else -> {
                        continue
                    }
                }
            }
            href
        }

    override fun serialize(encoder: Encoder, value: String) {
        TODO("Not yet implemented")
    }
}

class LinksSerializer(): KSerializer<Page.Links>{
    val hrefSerializer = HrefSerializer()
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("links"){
            element("self",hrefSerializer.descriptor)
            element("prev",hrefSerializer.descriptor)
            element("next",hrefSerializer.descriptor)
        }

    override fun deserialize(decoder: Decoder): Page.Links =
        decoder.decodeStructure(descriptor){
            var self : String? = null
            var prev : String? = null
            var next : String? = null
            while (true){
                when(decodeElementIndex(descriptor)){
                    0 -> self = decoder.decodeSerializableValue(hrefSerializer)
                    1 -> prev = decoder.decodeSerializableValue(hrefSerializer)
                    2 -> next = decoder.decodeSerializableValue(hrefSerializer)
                    DECODE_DONE -> break
                    else -> continue
                }
            }
            require(self != null)
            require(next != null)
            require(prev != null)
            Page.Links(self,next,prev)
        }

    override fun serialize(encoder: Encoder, value: Page.Links) {
        TODO("Not yet implemented")
    }
}



class PageSerializer<T>(val tSerializer: KSerializer<T>): KSerializer<Page<T>>{
    val delegatedEmbeddedSerializer = EmbeddedSerializer(tSerializer)
    val delegatedLinkSerializer = Page.Links.serializer()
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("page"){
            element("_links",delegatedLinkSerializer.descriptor)
            element("_embedded", delegatedEmbeddedSerializer.descriptor)
        }

    override fun deserialize(decoder: Decoder): Page<T> =
        decoder.decodeStructure(descriptor){
            var records : List<T>? = null
            var links : Page.Links? = null
            while (true){
                when(decodeElementIndex(descriptor)){
                    0 -> links = decoder.decodeSerializableValue(delegatedLinkSerializer)
                    1 -> records = decoder.decodeSerializableValue(delegatedEmbeddedSerializer)._records
                    DECODE_DONE -> break
                    else -> continue
                }
            }
            require(records != null)
            require(links != null)
            Page(records, links)
        }

    override fun serialize(encoder: Encoder, value: Page<T>) {
        TODO("Not yet implemented")
    }
}
