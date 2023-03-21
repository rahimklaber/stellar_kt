package me.rahimklaber.stellar.base.xdr

import io.ktor.util.*
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class TrustLineAssetTest {

    @Test
    fun test1(){
        val asset = TrustLineAsset.LiquidityPool(
            PoolID(Random.nextBytes(32))
        )

        val stream = XdrStream()

        asset.encode(stream)
        val xdrBytes = stream.buffer.readByteArray()

        val decodeStream = XdrStream()
        decodeStream.writeBytes(xdrBytes)

        val decodedAsset = TrustLineAsset.decode(decodeStream)

        assertEquals(asset,decodedAsset)
    }
}