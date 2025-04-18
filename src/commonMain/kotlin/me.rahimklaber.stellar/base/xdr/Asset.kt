// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * Asset's original definition in the XDR file is:
 * ```
 * union Asset switch (AssetType type)
{
case ASSET_TYPE_NATIVE: // Not credit
void;

case ASSET_TYPE_CREDIT_ALPHANUM4:
AlphaNum4 alphaNum4;

case ASSET_TYPE_CREDIT_ALPHANUM12:
AlphaNum12 alphaNum12;

// add other asset types here in the future
};
 * ```
 */
sealed class Asset(val type: AssetType) : XdrElement {
    data object Native : Asset(AssetType.ASSET_TYPE_NATIVE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    fun alphaNum4OrNull(): CreditAlphanum4? = if (this is CreditAlphanum4) this else null
    data class CreditAlphanum4(
        val alphaNum4: AlphaNum4,
    ) : Asset(AssetType.ASSET_TYPE_CREDIT_ALPHANUM4) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            alphaNum4.encode(stream)
        }
    }

    fun alphaNum12OrNull(): CreditAlphanum12? = if (this is CreditAlphanum12) this else null
    data class CreditAlphanum12(
        val alphaNum12: AlphaNum12,
    ) : Asset(AssetType.ASSET_TYPE_CREDIT_ALPHANUM12) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            alphaNum12.encode(stream)
        }
    }

    companion object : XdrElementDecoder<Asset> {
        override fun decode(stream: XdrInputStream): Asset {
            return when (val type = AssetType.decode(stream)) {
                AssetType.ASSET_TYPE_NATIVE -> Native
                AssetType.ASSET_TYPE_CREDIT_ALPHANUM4 -> {
                    val alphaNum4 = AlphaNum4.decode(stream)
                    CreditAlphanum4(alphaNum4)
                }

                AssetType.ASSET_TYPE_CREDIT_ALPHANUM12 -> {
                    val alphaNum12 = AlphaNum12.decode(stream)
                    CreditAlphanum12(alphaNum12)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
