package me.rahimklaber.stellar.base.xdr

import me.rahimklaber.stellar.base.Crypto
import me.rahimklaber.stellar.base.Network
import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeContract

@OptIn(ExperimentalStdlibApi::class)
fun <T : XdrElement> XdrElementDecoder<T>.fromHex(hex: String): T {
    val stream = xdrStream()
    stream.writeBytes(hex.hexToByteArray())
    return decode(stream)
}

fun Asset.contractId(network: Network) = run {
    val preimage = ContractIDPreimage.FromAsset(this)

    val hashIDPreimage = HashIDPreimage.ContractId(
        HashIDPreimage.HashIDPreimageContractID(
            Hash(network.networkId),
            preimage
        )
    )

    val hashed = Crypto.sha256(hashIDPreimage.toXdrBytes())

    StrKey.encodeContract(hashed)
}