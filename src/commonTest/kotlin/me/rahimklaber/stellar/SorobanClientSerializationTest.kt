package me.rahimklaber.stellar

import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SorobanClientSerializationTest {

    private fun result(body: String): JsonObject =
        json.decodeFromString(JsonObject.serializer(), body)["result"] as JsonObject

    @Test
    fun decodesGetEventsWithoutPagingToken() {
        val body = """
            {"jsonrpc":"2.0","id":1,"result":{
              "events":[{
                "type":"contract",
                "ledger":3727845,
                "ledgerClosedAt":"2026-07-21T18:01:10Z",
                "contractId":"CDLZFC3SYJYDZT7K67VZ75HPJVIEUVNIXF47ZG2FB2RMQQVU2HHGCYSC",
                "id":"0016010972359577600-0000000001",
                "transactionIndex":5,
                "operationIndex":0,
                "inSuccessfulContractCall":true,
                "topic":["AAAADwAAAAh0cmFuc2Zlcg=="],
                "value":"AAAACgAAAAAAAAAAAAAAALLQXgA=",
                "txHash":"a5c9247b77eb04c0d857934a2e988c408167976c8acbdf3d8acf64c44deb3beb"
              }],
              "latestLedger":3730843,
              "oldestLedger":3609884,
              "latestLedgerCloseTime":"1784671886",
              "oldestLedgerCloseTime":"1784066056",
              "cursor":"0016010972359577600-0000000008"
            }}
        """.trimIndent()

        val response = json.decodeFromJsonElement<GetEventsResponse>(result(body))

        assertEquals(1, response.events.size)
        assertEquals("0016010972359577600-0000000001", response.events.first().id)
        assertEquals(3609884, response.oldestLedger)
        assertEquals("0016010972359577600-0000000008", response.cursor)
    }

    @Test
    fun decodesGetVersionInfoWithCamelCaseFields() {
        val body = """
            {"jsonrpc":"2.0","id":1,"result":{
              "version":"29.0.0-b2b701685c79aee17fe4eb22dbd08a5dfd11594d",
              "commitHash":"b2b701685c79aee17fe4eb22dbd08a5dfd11594d",
              "buildTimestamp":"2026-09-22T14:52:44",
              "captiveCoreVersion":"stellar-core 29.0.0 (4eb83337380a29ad0907f4e32196ce97b8dc7649)",
              "protocolVersion":28
            }}
        """.trimIndent()

        val response = json.decodeFromJsonElement<GetVersionInfoResponse>(result(body))

        assertEquals("b2b701685c79aee17fe4eb22dbd08a5dfd11594d", response.commitHash)
        assertEquals(28, response.protocolVersion)
    }

    @Test
    fun decodesGetTransactionsWithNumericTimestamps() {
        val body = """
            {"jsonrpc":"2.0","id":1,"result":{
              "transactions":[{
                "status":"SUCCESS",
                "txHash":"173d6022f8db9654f31c5aa53785385b913ed32baf8ee72f984b97a97bb0909b",
                "applicationOrder":1,
                "feeBump":false,
                "envelopeXdr":"AAA=",
                "resultXdr":"AAA=",
                "resultMetaXdr":"AAA=",
                "ledger":337272,
                "createdAt":1751955324
              }],
              "latestLedger":337272,
              "latestLedgerCloseTimestamp":1751955324,
              "oldestLedger":1,
              "oldestLedgerCloseTimestamp":1751000000,
              "cursor":"337272"
            }}
        """.trimIndent()

        val response = json.decodeFromJsonElement<GetTransactionsResponse>(result(body))

        val tx = response.transactions.first()
        assertEquals(1751955324L, tx.createdAt)
        assertEquals(1751955324L, response.latestLedgerCloseTime)
    }

    @Test
    fun decodesGetLedgersWithNumericTimestamps() {
        val body = """
            {"jsonrpc":"2.0","id":1,"result":{
              "ledgers":[{
                "hash":"434de11b427aa4b6f8cda259ac2111a6aa148d2ab6b4c7affe864e94a9f4bd80",
                "sequence":36233,
                "ledgerCloseTime":"1734032457",
                "headerXdr":"AAA=",
                "metadataXdr":"AAA="
              }],
              "latestLedger":36379,
              "latestLedgerCloseTime":1734033188,
              "oldestLedger":29312,
              "oldestLedgerCloseTime":1733997822,
              "cursor":"36234"
            }}
        """.trimIndent()

        val response = json.decodeFromJsonElement<GetLedgersResponse>(result(body))

        assertEquals(1734033188L, response.latestLedgerCloseTime)
        assertEquals("1734032457", response.ledgers.first().ledgerCloseTime)
    }

    @Test
    fun decodesLatestLedgerWithProtocolVersion() {
        val body = """
            {"jsonrpc":"2.0","id":1,"result":{
              "id":"0a00a9cf845f7af7cff09c66f8ae6480e9971e6e2c7fa4afd8d6266ee13c987b",
              "protocolVersion":27,
              "sequence":3730795,
              "closeTime":"1784671645"
            }}
        """.trimIndent()

        val response = json.decodeFromJsonElement<LatestLedgerResponse>(result(body))

        assertEquals(27, response.protocolVersion)
        assertNull(response.headerXdr)
    }
}
