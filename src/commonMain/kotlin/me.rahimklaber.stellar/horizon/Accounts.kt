package me.rahimklaber.stellar.horizon

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import me.rahimklaber.stellar.base.Account
import me.rahimklaber.stellar.base.Asset


class AccountRequestBuilder(client: HttpClient, horizonUrl: String) :
    RequestBuilder<AccountResponse>(client, horizonUrl, "accounts") {
    suspend fun account(accountId: String): AccountResponse {
        addPath(accountId)
       return client.get(buildUrl()).body()
    }

    /**
     * Filter accounts by signer. All accounts which are returned have [signer] as a signer.
     *
     * @param signer signer to filter accounts by.
     * @see <a href="https://www.stellar.org/developers/horizon/reference/endpoints/accounts.html">Accounts</a>
     */
    fun forSigner(signer: String): AccountRequestBuilder {
        check(checkQueryParam(ASSET_PARAMETER_NAME) == null) { "Cannot set both asset and signer" }
        check(checkQueryParam(SPONSOR_PARAMETER_NAME) == null) { "Cannot set both sponsor and signer" }

        addQueryParam(SIGNER_PARAMETER_NAME, signer)
        return this
    }

    /**
     * Filter accounts by asset. All accounts which are returned have a trustline to [asset].
     *
     * @param asset asset to filter accounts by.
     * <a href="https://www.stellar.org/developers/horizon/reference/endpoints/accounts.html">Accounts</a>
     */
    fun forAsset(asset: Asset): AccountRequestBuilder {
        check(checkQueryParam(SIGNER_PARAMETER_NAME) == null) { "Cannot set both asset and signer" }
        check(checkQueryParam(SPONSOR_PARAMETER_NAME) == null) { "Cannot set both asset and sponsor" }
        addAssetQueryParam(asset)
        return this
    }

    /**
     * Filter accounts by sponsor. All accounts which are returned are sponsored by [sponsor].
     *
     * @param sponsor sponsor to filter accounts by.
     * <a href="https://www.stellar.org/developers/horizon/reference/endpoints/accounts.html">Accounts</a>
     */
    fun forSponsor(sponsor: String): AccountRequestBuilder {
        check(checkQueryParam(ASSET_PARAMETER_NAME) == null) { "Cannot set both asset and sponsor" }
        check(checkQueryParam(SIGNER_PARAMETER_NAME) == null) { "Cannot set both sponsor and signer" }
        addQueryParam(SPONSOR_PARAMETER_NAME, sponsor)
        return this
    }

    override fun limit(limit: Int): AccountRequestBuilder {
        super.limit(limit)
        return this
    }

    override fun cursor(cursor: String): AccountRequestBuilder {
        super.cursor(cursor)
        return this
    }

    override fun order(order: Order): AccountRequestBuilder {
        super.order(order)
        return this
    }


    override suspend fun call(): Page<AccountResponse> {
        return client.get(buildUrl()).body()
    }

    companion object {
        private const val ASSET_PARAMETER_NAME = "asset"
        private const val SIGNER_PARAMETER_NAME = "signer"
        private const val SPONSOR_PARAMETER_NAME = "sponsor"
    }
}

@Serializable
data class AccountResponse(
    override val id: String,
    @SerialName("_links")
    val links: Links,
    @SerialName("account_id")
    val accountId: String,
    @SerialName("paging_token")
    override val pagingToken: String,
    val sequence: Long,
    @SerialName("subentry_count")
    val subentryCount: Long,
    @SerialName("home_domain")
    val homeDomain: String? = null,
    @SerialName("last_modified_ledger")
    val lastModifiedLedger: Long,
    @SerialName("num_sponsoring")
    val numSponsoring: Long,
    @SerialName("num_sponsored")
    val numSponsored: Long,
    val sponsor: String? = null,
    val thresholds: Thresholds,
    val flags: Flags,
    val balances: Array<Balance>,
    val signers: Array<Signer>,
    val data: Map<String, String>,
): Response {
    @Serializable(with = AccountLinksSerializer::class)
    data class Links(
        val self: String,
        val transactions: String,
        val operations: String,
        val payments: String,
        val effects: String,
        val offers: String,
        val trades: String,
        val data: String
    )
}

fun AccountResponse.toAccount(): Account {
    return Account(accountId, sequence)
}

class AccountLinksSerializer : KSerializer<AccountResponse.Links> {
    val hrefSerializer = HrefSerializer
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("_links") {
            element("self", hrefSerializer.descriptor)
            element("transactions", hrefSerializer.descriptor)
            element("operations", hrefSerializer.descriptor)
            element("payments", hrefSerializer.descriptor)
            element("effects", hrefSerializer.descriptor)
            element("offers", hrefSerializer.descriptor)
            element("trades", hrefSerializer.descriptor)
            element("data", hrefSerializer.descriptor)
        }
    //Todo: Should I deal with templates? Don't think so.
    override fun deserialize(decoder: Decoder): AccountResponse.Links =
        decoder.decodeStructure(descriptor) {
            var self: String? = null
            var transactions : String?  = null
            var operations : String?  = null
            var payments : String?  = null
            var effects : String?  = null
            var offers : String?  = null
            var trades : String?  = null
            var data : String?  = null
            while (true) {
                when (decodeElementIndex(descriptor)) {
                    0 -> self = decoder.decodeSerializableValue(hrefSerializer)
                    1 -> transactions = decoder.decodeSerializableValue(hrefSerializer).split("{")[0]
                    2 -> operations = decoder.decodeSerializableValue(hrefSerializer).split("{")[0]
                    3 -> payments = decoder.decodeSerializableValue(hrefSerializer).split("{")[0]
                    4 -> effects = decoder.decodeSerializableValue(hrefSerializer).split("{")[0]
                    5 -> offers = decoder.decodeSerializableValue(hrefSerializer).split("{")[0]
                    6 -> trades = decoder.decodeSerializableValue(hrefSerializer).split("{")[0]
                    7 -> data = decoder.decodeSerializableValue(hrefSerializer).split("{")[0]
                    CompositeDecoder.DECODE_DONE -> break
                    else -> continue
                }
            }
            require(self != null)
            require(transactions != null)
            require(operations != null)
            require(payments != null)
            require(effects != null)
            require(offers != null)
            require(trades != null)
            require(data != null)
            AccountResponse.Links(self, transactions, operations,payments, effects, offers, trades, data)
        }

    override fun serialize(encoder: Encoder, value: AccountResponse.Links) {
        TODO("Not yet implemented")
    }
}


@Serializable
data class Signer(
    val weight: Long,
    val sponsor: String? = null,
    val key: String,
    val type: String,
)

@Serializable
data class Balance(
    val balance: String,
    @SerialName("buying_liabilities") val buyingLiabilities: String,
    @SerialName("selling_liabilities") val sellingLiabilities: String,
    val limit: Double? = null, /*move to bigdecimal*/
    @SerialName("asset_type") val assetType: String,
    @SerialName("asset_code") val asset_code: String? = null,
    @SerialName("asset_issuer") val assetIssuer: String? = null,
    val sponsor: String? = null
)

@Serializable
data class Flags(
    @SerialName("auth_immutable") val authImmutable: Boolean,
    @SerialName("auth_required") val authRequired: Boolean,
    @SerialName("auth_revocable") val authRevocable: Boolean,
    @SerialName("auth_clawback_enabled") val authClawbackEnabled: Boolean,
)

@Serializable
data class Thresholds(
    @SerialName("low_threshold") val low: Long,
    @SerialName("med_threshold") val med: Long,
    @SerialName("high_threshold") val high: Long
)

