package me.rahimklaber.stellar.base.xdr

import io.ktor.util.*
import kotlinx.io.readByteArray
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class DataEntryTest {

    @Test
    fun test1(){
        val dataEntry = DataEntry(
            accountID = AccountID(PublicKey.PublicKeyEd25519(Uint256(Random.Default.nextBytes(32)))),
            dataName = String64("HI".encodeToByteArray()),
            dataValue = DataValue("World".encodeToByteArray()),
            discriminant = 0
        )
        val stream = XdrStream()

        dataEntry.encode(stream)

        val xddrbytes  = stream.buffer.readByteArray()

        println(xddrbytes.encodeBase64())

        val streamForDecode = XdrStream()
        dataEntry.encode(streamForDecode)
        val decoded = DataEntry.decode(streamForDecode)

        assertEquals(dataEntry,decoded)
    }

    @Test
    fun test2(){
        val dataValue = DataValue("World".encodeToByteArray())
        val stream = XdrStream()

        dataValue

        val xddrbytes  = stream.buffer.readByteArray()

        println(xddrbytes.encodeBase64())

        val streamForDecode = XdrStream()
        dataValue.encode(streamForDecode)
        val decoded = DataValue.decode(streamForDecode)

        assertEquals(dataValue,decoded)
    }
}