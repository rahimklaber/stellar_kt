package horizon

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.native.concurrent.SharedImmutable

class AccountRequestBuilder(client: HttpClient, horizonUrl: String) :
    RequestBuilder<AccountResponse>(client, horizonUrl, "accounts") {
    fun forAccount(accountId: String): AccountRequestBuilder {
        return addPath(accountId) as AccountRequestBuilder
    }

    override suspend fun callAsync(): AccountResponse {
        return client.get(buildUrl())
    }
}

@Serializable
data class AccountResponse(
    val id: String,
    @SerialName("account_id") val accountId: String,
    val sequence: Long,
    @SerialName("subentry_count") val subentry_count: Long,
    @SerialName("home_domain") val homeDomain: String? = null,
    @SerialName("last_modified_ledger") val lastModifiedLedger: Long,
    @SerialName("num_sponsoring") val numSponsoring: Long,
    @SerialName("num_sponsored") val numSponsored: Long,
    val sponsor: String? = null,
    val thresholds: Thresholds,
    val flags: Flags,
    val balances : Array<Balance>,
    val signers : Array<Signer>,
    val data : Map<String,String>
)
@Serializable
data class Signer(
    val weight : Long,
    val sponsor: String? = null,
    val key: String,
    val type: String,
)

@Serializable
data class Balance(
    val balance: String,
    @SerialName("buying_liabilities") val buyingLiabilities : String,
    @SerialName("selling_liabilities") val sellingLiabilities : String,
    val limit : Double? = null, /*move to bigdecimal*/
    @SerialName("asset_type") val assetType : String,
    @SerialName("asset_code") val asset_code : String? = null,
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
    @SerialName("high_threshold")val high: Long
)

