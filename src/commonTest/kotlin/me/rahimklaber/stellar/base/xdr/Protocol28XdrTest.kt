package me.rahimklaber.stellar.base.xdr

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class Protocol28XdrTest {

    private fun hash(seed: Int) = Hash(ByteArray(32) { seed.toByte() })

    private fun signature() = LedgerCloseValueSignature(
        nodeID = NodeID(PublicKey.Ed25519(ByteArray(32) { 7 }.toUint256())),
        signature = Signature(byteArrayOf(1, 2, 3)),
    )

    private fun <T : XdrElement> roundTrip(decoder: XdrElementDecoder<T>, element: T): T {
        val base64 = element.toXdrBase64()
        val decoded = decoder.fromXdrBase64(base64)
        assertEquals(base64, decoded.toXdrBase64())
        return decoded
    }

    @Test
    fun encodesEmptyTxSetStellarValueType() {
        assertEquals("AAAAAg==", StellarValueType.STELLAR_VALUE_EMPTY_TX_SET.toXdrBase64())
        assertEquals(
            StellarValueType.STELLAR_VALUE_EMPTY_TX_SET,
            StellarValueType.fromXdrBase64("AAAAAg=="),
        )
    }

    @Test
    fun roundTripsStellarValueWithEmptyTxSetExt() {
        val proposedValue = StellarValue.StellarValueExt.StellarValueExtProposedValue(
            txSetHash = hash(0),
            previousLedgerHash = hash(3),
            previousLedgerVersion = 20u,
            lcValueSignature = signature(),
        )
        val value = StellarValue(
            txSetHash = hash(1),
            closeTime = TimePoint(1234uL),
            upgrades = listOf(UpgradeType(byteArrayOf())),
            ext = StellarValue.StellarValueExt.EmptyTxSet(proposedValue),
        )

        val decoded = roundTrip(StellarValue, value)

        val ext = assertIs<StellarValue.StellarValueExt.EmptyTxSet>(decoded.ext)
        assertEquals(20u, ext.proposedValue.previousLedgerVersion)
        assertTrue(ext.proposedValue.txSetHash.value.contentEquals(hash(0).value))
        assertTrue(ext.proposedValue.previousLedgerHash.value.contentEquals(hash(3).value))
    }

    @Test
    fun encodesContractExecutableExternalRefType() {
        assertEquals("AAAAAg==", ContractExecutableType.CONTRACT_EXECUTABLE_EXTERNAL_REF.toXdrBase64())
        assertEquals(
            ContractExecutableType.CONTRACT_EXECUTABLE_EXTERNAL_REF,
            ContractExecutableType.fromXdrBase64("AAAAAg=="),
        )
    }

    @Test
    fun roundTripsContractExecutableExternalRef() {
        val executable = ContractExecutable.ExternalRef(
            ContractExecutableExternalRef(
                executableOwner = SCAddress.Contract(ContractID(hash(9))),
                tag = SCString("owner-tag"),
            )
        )

        val decoded = roundTrip(ContractExecutable, executable)

        val externalRef = assertIs<ContractExecutable.ExternalRef>(decoded).externalRef
        assertEquals(SCString("owner-tag"), externalRef.tag)
        val owner = assertIs<SCAddress.Contract>(externalRef.executableOwner)
        assertTrue(owner.contractId.value.value.contentEquals(hash(9).value))
    }

    @Test
    fun encodesExecutableTagSCValType() {
        assertEquals("AAAAFg==", SCValType.SCV_EXECUTABLE_TAG.toXdrBase64())
        assertEquals(SCValType.SCV_EXECUTABLE_TAG, SCValType.fromXdrBase64("AAAAFg=="))
    }

    @Test
    fun roundTripsExecutableTagSCVal() {
        val decoded = roundTrip(SCVal, SCVal.ExecutableTag(SCString("some-tag")))

        val executableTag = assertIs<SCVal.ExecutableTag>(decoded).executableTag
        assertEquals(SCString("some-tag"), executableTag)
    }
}
