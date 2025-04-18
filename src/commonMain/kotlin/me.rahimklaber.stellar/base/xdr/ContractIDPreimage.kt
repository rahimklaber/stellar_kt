// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ContractIDPreimage's original definition in the XDR file is:
 * ```
 * union ContractIDPreimage switch (ContractIDPreimageType type)
{
case CONTRACT_ID_PREIMAGE_FROM_ADDRESS:
struct
{
SCAddress address;
uint256 salt;
} fromAddress;
case CONTRACT_ID_PREIMAGE_FROM_ASSET:
Asset fromAsset;
};
 * ```
 */
sealed class ContractIDPreimage(val type: ContractIDPreimageType) : XdrElement {
    fun fromAddressOrNull(): FromAddress? = if (this is FromAddress) this else null
    data class FromAddress(
        val fromAddress: ContractIDPreimageFromAddress,
    ) : ContractIDPreimage(ContractIDPreimageType.CONTRACT_ID_PREIMAGE_FROM_ADDRESS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            fromAddress.encode(stream)
        }
    }

    fun fromAssetOrNull(): FromAsset? = if (this is FromAsset) this else null
    data class FromAsset(
        val fromAsset: Asset,
    ) : ContractIDPreimage(ContractIDPreimageType.CONTRACT_ID_PREIMAGE_FROM_ASSET) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            fromAsset.encode(stream)
        }
    }

    companion object : XdrElementDecoder<ContractIDPreimage> {
        override fun decode(stream: XdrInputStream): ContractIDPreimage {
            return when (val type = ContractIDPreimageType.decode(stream)) {
                ContractIDPreimageType.CONTRACT_ID_PREIMAGE_FROM_ADDRESS -> {
                    val fromAddress = ContractIDPreimageFromAddress.decode(stream)
                    FromAddress(fromAddress)
                }

                ContractIDPreimageType.CONTRACT_ID_PREIMAGE_FROM_ASSET -> {
                    val fromAsset = Asset.decode(stream)
                    FromAsset(fromAsset)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }

    /**
     * ContractIDPreimageFromAddress's original definition in the XDR file is:
     * ```
     * struct
    {
    SCAddress address;
    uint256 salt;
    }
     * ```
     */
    data class ContractIDPreimageFromAddress(
        val address: SCAddress,
        val salt: Uint256,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            address.encode(stream)
            salt.encode(stream)
        }

        companion object : XdrElementDecoder<ContractIDPreimageFromAddress> {
            override fun decode(stream: XdrInputStream): ContractIDPreimageFromAddress {
                val address = SCAddress.decode(stream)
                val salt = Uint256.decode(stream)
                return ContractIDPreimageFromAddress(
                    address,
                    salt,
                )
            }
        }

    }
}
