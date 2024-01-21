package me.rahimklaber.stellar.base.xdr

import me.rahimklaber.stellar.base.xdr.soroban.SCVal
import me.rahimklaber.stellar.base.xdr.soroban.ScAddress

data class SorobanAddressCredentials(
    val address: ScAddress,
    val nonce: Long,
    val signatureExpirationLedger: UInt,
    val signature: SCVal
): XdrElement {
    override fun encode(stream: XdrStream) {
        address.encode(stream)
        stream.writeLong(nonce)
        stream.writeInt(signatureExpirationLedger.toInt())
        signature.encode(stream)
    }

    companion object: XdrElementDecoder<SorobanAddressCredentials> {
        override fun decode(stream: XdrStream): SorobanAddressCredentials {
            return SorobanAddressCredentials(
                ScAddress.decode(stream),
                stream.readLong(),
                stream.readInt().toUInt(),
                SCVal.decode(stream)
            )
        }
    }
}