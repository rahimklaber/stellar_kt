package me.rahimklaber.stellar.horizon.operations

import io.ktor.utils.io.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import me.rahimklaber.stellar.horizon.Page
import me.rahimklaber.stellar.horizon.accountResponses1
import me.rahimklaber.stellar.horizon.operations.testresponses.accountMergeResponseJson1
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountMergeResponseTest {
    val json = Json
    @Test
    fun accountMergeResponseShouldDeserializeCorrectly(){
        val accountMergeResponse = json.decodeFromString<AccountMergeResponse>(accountMergeResponseJson1)
        val jsonMap = json.decodeFromString<Map<String,JsonElement>>(accountMergeResponseJson1)

        assertEquals(
            jsonMap["id"]!!.jsonPrimitive.content,
            accountMergeResponse.id
        )

    }
}