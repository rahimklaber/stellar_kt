package me.rahimklaber.stellar.horizon

import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure

@Serializable(with = PageSerializer::class)
data class Page<T>(@SerialName("_records") val records: List<T>, val links: Links) {
    @Serializable
    data class Links(
        @Serializable(with = HrefSerializer::class) val self: String,
        @Serializable(with = HrefSerializer::class) val next: String? = null,
        @Serializable(with = HrefSerializer::class) val prev: String? = null,
    )
}

/**
 * Get the next page.
 */
suspend inline fun <reified T> Page<T>.next(server: Server): Page<T> {
    return server.client.get(links.next ?: throw Exception("Cannot get next page.")).body()
}

/**
 * Get the previous page.
 */
suspend inline fun <reified T> Page<T>.prev(server: Server): Page<T> {
    return server.client.get(links.prev ?: throw Exception("Cannot get previous page.")).body()
}


@Serializable(with = EmbeddedSerializer::class)
class Embedded<T>(val _records: List<T>)

class EmbeddedSerializer<T>(val tSerializer: KSerializer<T>) : KSerializer<Embedded<T>> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("page") {
            element("records", ListSerializer(tSerializer).descriptor)
        }

    override fun deserialize(decoder: Decoder): Embedded<T> =
        decoder.decodeStructure(descriptor) {
            var records: List<T>? = null
            while (true) {
                when (decodeElementIndex(descriptor)) {
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

object HrefSerializer : KSerializer<String> {

    @Serializable
    data class HrefObj(
        val href: String,
        val templated: Boolean? = null,
    )

    override val descriptor: SerialDescriptor = HrefObj.serializer().descriptor

    override fun deserialize(decoder: Decoder): String {
        val href = HrefObj.serializer().deserialize(decoder)
        return href.href
    }

    override fun serialize(encoder: Encoder, value: String) {
        HrefObj.serializer().serialize(encoder, HrefObj(value))
    }
}



class PageSerializer<T>(val tSerializer: KSerializer<T>) : KSerializer<Page<T>> {
    val delegatedEmbeddedSerializer = EmbeddedSerializer(tSerializer)
    val delegatedLinkSerializer = Page.Links.serializer()
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("page") {
            element("_links", delegatedLinkSerializer.descriptor)
            element("_embedded", delegatedEmbeddedSerializer.descriptor)
        }

    override fun deserialize(decoder: Decoder): Page<T> =
        decoder.decodeStructure(descriptor) {
            var records: List<T>? = null
            var links: Page.Links? = null
            while (true) {
                when (decodeElementIndex(descriptor)) {
                    0 -> links = decoder.decodeSerializableValue(delegatedLinkSerializer)
                    1 -> records =
                        decoder.decodeSerializableValue(delegatedEmbeddedSerializer)._records
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
