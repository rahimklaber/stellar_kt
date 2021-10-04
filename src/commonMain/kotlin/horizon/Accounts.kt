package me.rahimklaber.sdk.horizon

import arrow.core.Either
import arrow.core.computations.either
import horizon.Page
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.rahimklaber.sdk.base.Asset

class AccountRequestBuilder(client: HttpClient, horizonUrl: String) :
    RequestBuilder<AccountResponse>(client, horizonUrl, "accounts") {
    suspend fun account(accountId: String): Either<Exception,AccountResponse> {
        addPath(accountId)
        return try {
            Either.Right(client.get(buildUrl()))
        }catch (e : Exception){
            Either.Left(e)
        }
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
    fun forAsset(asset: Asset) : AccountRequestBuilder{
        check(checkQueryParam(SIGNER_PARAMETER_NAME) == null){"Cannot set both asset and signer"}
        check(checkQueryParam(SPONSOR_PARAMETER_NAME) == null){"Cannot set both asset and sponsor"}
        addAssetQueryParam(asset)
        return this
    }

    /**
     * Filter accounts by sponsor. All accounts which are returned are sponsored by [sponsor].
     *
     * @param sponsor sponsor to filter accounts by.
     * <a href="https://www.stellar.org/developers/horizon/reference/endpoints/accounts.html">Accounts</a>
     */
    fun forSponsor(sponsor: String) : AccountRequestBuilder{
        check(checkQueryParam(ASSET_PARAMETER_NAME) == null) { "Cannot set both asset and sponsor" }
        check(checkQueryParam(SIGNER_PARAMETER_NAME) == null) { "Cannot set both sponsor and signer" }
        addQueryParam(SPONSOR_PARAMETER_NAME,sponsor)
        return  this
    }

    override fun limit(limit: Int): AccountRequestBuilder {
        super.limit(limit)
        return this
    }

    override fun cursor(cursor: String): AccountRequestBuilder {
        super.cursor(cursor)
        return this
    }


    override suspend fun callAsync(): Either<Exception, Page<AccountResponse>> {
        return try {
            Either.Right(client.get(buildUrl()))
        }catch (e : Exception){
            Either.Left(e)
        }
    }

    companion object {
        private const val ASSET_PARAMETER_NAME = "asset"
        private const val SIGNER_PARAMETER_NAME = "signer"
        private const val SPONSOR_PARAMETER_NAME = "sponsor"
    }
}

@Serializable
data class AccountResponse(
    val id: String,
    @SerialName("account_id") val accountId: String,
    val sequence: Long,
    @SerialName("subentry_count") val subentryCount: Long,
    @SerialName("home_domain") val homeDomain: String? = null,
    @SerialName("last_modified_ledger") val lastModifiedLedger: Long,
    @SerialName("num_sponsoring") val numSponsoring: Long,
    @SerialName("num_sponsored") val numSponsored: Long,
    val sponsor: String? = null,
    val thresholds: Thresholds,
    val flags: Flags,
    val balances: Array<Balance>,
    val signers: Array<Signer>,
    val data: Map<String, String>
)

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

