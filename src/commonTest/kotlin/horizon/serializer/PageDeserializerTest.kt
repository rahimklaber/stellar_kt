package horizon.serializer

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.rahimklaber.stellar.horizon.AccountResponse
import me.rahimklaber.stellar.horizon.Page
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs


class PageSerializerTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun anAccountResponseShouldParseSuccessfully(){
        val decodeResponse = json.decodeFromString<Page<AccountResponse>>(accountResponses1)
        assertIs<Page<AccountResponse>>(decodeResponse)
    }

}