package me.rahimklaber.stellar.horizon.operations

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import me.rahimklaber.stellar.horizon.HrefSerializer
import me.rahimklaber.stellar.horizon.PageLinksSerializer

@Serializable()
data class Links(
    @Serializable(with = HrefSerializer::class)
    val self: String,
    @Serializable(with = HrefSerializer::class)
    val transaction: String,
    @Serializable(with = HrefSerializer::class)
    val effects: String,
    @Serializable(with = HrefSerializer::class)
    val succeeds: String?,
    @Serializable(with = HrefSerializer::class)
    val precedes: String?
)