package me.rahimklaber.stellar.base.xdr

import io.ktor.util.*
import kotlin.random.Random
import kotlin.test.Test

class TrustLineEntryTest {

    @Test
    fun test1(){
        val entry = TrustLineEntry(
            accountID = AccountID(PublicKey.PublicKeyEd25519(Uint256(Random.nextBytes(32)))),
            asset = TrustLineAsset.LiquidityPool(PoolID(Random.nextBytes(32))),
            balance = 1L,
            limit = 1L,
            flags = 0u,
            discriminant = 1,
            trustLineExtension = TrustLineExtension(
                Liabilities(1L,1L),
                0,
                null
            )
        )

        val stream = XdrStream()
        entry.encode(stream)

        println(entry)

        println(stream.buffer.readByteArray().encodeBase64())
    }


}