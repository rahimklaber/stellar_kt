// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * StellarValue's original definition in the XDR file is:
 * ```
 * struct StellarValue
{
Hash txSetHash;      // transaction set to apply to previous ledger
TimePoint closeTime; // network close time

// upgrades to apply to the previous ledger (usually empty)
// this is a vector of encoded 'LedgerUpgrade' so that nodes can drop
// unknown steps during consensus if needed.
// see notes below on 'LedgerUpgrade' for more detail
// max size is dictated by number of upgrade types (+ room for future)
UpgradeType upgrades<6>;

// reserved for future use
union switch (StellarValueType v)
{
case STELLAR_VALUE_BASIC:
void;
case STELLAR_VALUE_SIGNED:
LedgerCloseValueSignature lcValueSignature;
}
ext;
};
 * ```
 */
data class StellarValue(
    val txSetHash: Hash,
    val closeTime: TimePoint,
    val upgrades: List<UpgradeType>,
    val ext: StellarValueExt,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        txSetHash.encode(stream)
        closeTime.encode(stream)
        val upgradesSize = upgrades.size
        stream.writeInt(upgradesSize)
        upgrades.encodeXdrElements(stream)
        ext.encode(stream)
    }

    companion object : XdrElementDecoder<StellarValue> {
        override fun decode(stream: XdrInputStream): StellarValue {
            val txSetHash = Hash.decode(stream)
            val closeTime = TimePoint.decode(stream)
            val upgradesSize = stream.readInt()
            val upgrades: List<UpgradeType> = decodeXdrElementsList(upgradesSize, stream, UpgradeType.decoder())
            val ext = StellarValueExt.decode(stream)
            return StellarValue(
                txSetHash,
                closeTime,
                upgrades,
                ext,
            )
        }
    }

    /**
     * StellarValueExt's original definition in the XDR file is:
     * ```
     * union switch (StellarValueType v)
    {
    case STELLAR_VALUE_BASIC:
    void;
    case STELLAR_VALUE_SIGNED:
    LedgerCloseValueSignature lcValueSignature;
    }
     * ```
     */
    sealed class StellarValueExt(val type: StellarValueType) : XdrElement {
        data object Basic : StellarValueExt(StellarValueType.STELLAR_VALUE_BASIC) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        fun lcValueSignatureOrNull(): Signed? = if (this is Signed) this else null
        data class Signed(
            val lcValueSignature: LedgerCloseValueSignature,
        ) : StellarValueExt(StellarValueType.STELLAR_VALUE_SIGNED) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                lcValueSignature.encode(stream)
            }
        }

        companion object : XdrElementDecoder<StellarValueExt> {
            override fun decode(stream: XdrInputStream): StellarValueExt {
                return when (val type = StellarValueType.decode(stream)) {
                    StellarValueType.STELLAR_VALUE_BASIC -> Basic
                    StellarValueType.STELLAR_VALUE_SIGNED -> {
                        val lcValueSignature = LedgerCloseValueSignature.decode(stream)
                        Signed(lcValueSignature)
                    }

                    else -> throw IllegalArgumentException("unknown type: $type")
                }
            }
        }
    }
}
