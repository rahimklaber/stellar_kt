package me.rahimklaber.stellar.base.xdr

import io.ktor.util.*
import kotlin.random.Random
import kotlin.test.Test

//todo move to common
class AccountEntryTest{

    @Test
    fun decodeTest1(){
        val entry = AccountEntry(
            accountID = AccountID(PublicKey.PublicKeyEd25519(Uint256(Random.nextBytes(32)))),
            balance = 1L,
            sequenceNumber = 1L,
            numSubEntries = 10u,
            inflationDest = null,
            flags = 0u,
            homeDomain = String32("hi".encodeToByteArray()),
            thresholds = Thresholds(byteArrayOf(0, 0, 0, 0)),
            signers = listOf(Signer(SignerKey.Ed25519SignerKey(Uint256(Random.nextBytes(32))), 10u)),
            discriminant = 1,
            accountEntryExtensionV1 = AccountEntryExtensionV1(
                liabilities = Liabilities(1L,1L),
                discriminant = 2,
                accountEntryExtensionV2 = AccountEntryExtensionV2(
                    0u,
                    0u,
                    listOf(),
                    discriminant = 3,
                    accountEntryExtensionV3 = AccountEntryExtensionV3(
                        0,
                        1u,
                        1uL
                    )
                )
            )
        )
        println(entry.homeDomain)

        val xdrStream = XdrStream()

        entry.encode(xdrStream)

        println(xdrStream.buffer.readByteArray().encodeBase64())

        val streamv2 = XdrStream()

        val v2 = AccountEntryExtensionV2(
            0u,
            0u,
            listOf(),
            discriminant = 3,
            accountEntryExtensionV3 = AccountEntryExtensionV3(
                0,
                1u,
                1uL
            )
        )
        v2.encode(streamv2)

        println("v2: ${streamv2.buffer.readByteArray().encodeBase64()}")
    }
}