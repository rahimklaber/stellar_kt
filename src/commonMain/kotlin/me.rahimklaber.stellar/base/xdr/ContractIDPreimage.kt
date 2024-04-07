package me.rahimklaber.stellar.base.xdr

import me.rahimklaber.stellar.base.xdr.soroban.ScAddress

sealed class ContractIDPreimage(val type: ContractIDPreimageType): XdrElement {

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    data class PreimageFromAddress(val address: ScAddress, val salt: Uint256): ContractIDPreimage(ContractIDPreimageType.CONTRACT_ID_PREIMAGE_FROM_ADDRESS){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            address.encode(stream)
            salt.encode(stream)
        }
    }
    data class PreimageFromAsset(val asset: Asset): ContractIDPreimage(ContractIDPreimageType.CONTRACT_ID_PREIMAGE_FROM_ASSET){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            asset.encode(stream)
        }
    }

    companion object: XdrElementDecoder<ContractIDPreimage> {
        override fun decode(stream: XdrStream): ContractIDPreimage {
            val type = ContractIDPreimageType.decode(stream)

            return when(type){
                ContractIDPreimageType.CONTRACT_ID_PREIMAGE_FROM_ADDRESS -> PreimageFromAddress(ScAddress.decode(stream), Uint256.decode(stream))
                ContractIDPreimageType.CONTRACT_ID_PREIMAGE_FROM_ASSET -> PreimageFromAsset(Asset.decode(stream))
            }
        }
    }


}