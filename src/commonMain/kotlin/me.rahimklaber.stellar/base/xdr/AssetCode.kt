// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * AssetCode's original definition in the XDR file is:
 * ```
 * union AssetCode switch (AssetType type)
{
case ASSET_TYPE_CREDIT_ALPHANUM4:
AssetCode4 assetCode4;

case ASSET_TYPE_CREDIT_ALPHANUM12:
AssetCode12 assetCode12;

// add other asset types here in the future
};
 * ```
 */
sealed class AssetCode(val type: AssetType) : XdrElement {
    fun assetCode4OrNull(): CreditAlphanum4? = if (this is CreditAlphanum4) this else null
    data class CreditAlphanum4(
        val assetCode4: AssetCode4,
    ) : AssetCode(AssetType.ASSET_TYPE_CREDIT_ALPHANUM4) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            assetCode4.encode(stream)
        }
    }

    fun assetCode12OrNull(): CreditAlphanum12? = if (this is CreditAlphanum12) this else null
    data class CreditAlphanum12(
        val assetCode12: AssetCode12,
    ) : AssetCode(AssetType.ASSET_TYPE_CREDIT_ALPHANUM12) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            assetCode12.encode(stream)
        }
    }

    companion object : XdrElementDecoder<AssetCode> {
        override fun decode(stream: XdrInputStream): AssetCode {
            return when (val type = AssetType.decode(stream)) {
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM4 -> {
                    val assetCode4 = AssetCode4.decode(stream)
                    CreditAlphanum4(assetCode4)
                }

                AssetType.ASSET_TYPE_CREDIT_ALPHANUM12 -> {
                    val assetCode12 = AssetCode12.decode(stream)
                    CreditAlphanum12(assetCode12)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
