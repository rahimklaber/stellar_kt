package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("set_options")
data class SetOptionsResponse(
    override val id: String, //long?
    @SerialName("paging_token") override val pagingToken: String,
    @SerialName("transaction_hash") override val transactionHash: String,
    @SerialName("transaction_successful") override val transactionSuccessful: Boolean,
    @SerialName("source_account") override val sourceAccount: String,
    @SerialName("created_at") override val createdAt: String,
    @SerialName("type_i") override val typeI: Int,
    @SerialName("type") override val type: String,
    @SerialName("signer_key") val signerKey: String? = null,
    @SerialName("signer_weight") val signerWeight: Int? = null,
    @SerialName("master_key_weight") val masterKeyWeight: Int? = null,
    @SerialName("low_threshold") val lowThreshold: Int? = null,
    @SerialName("med_threshold") val medThreshold: Int? = null,
    @SerialName("high_threshold") val highThreshold: Int? = null,
    @SerialName("home_domain") val homeDomain: String?,
    @SerialName("set_flags") val setFlags: List<Int>? = null,
    @SerialName("set_flags_s") val setFlagsS: List<String>? = null,
    @SerialName("clear_flags") val clearFlags: List<Int>? = null,
    @SerialName("clear_flags_s") val clearFlagsS: List<String>? = null, override val links: OperationResponse.Links,
) : OperationResponse