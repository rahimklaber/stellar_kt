package me.rahimklaber.stellar.base.xdr

/**
 * /* StellarValue is the value used by SCP to reach consensus on a given ledger
 *  */
 * struct StellarValue
 * {
 *     Hash txSetHash;      // transaction set to apply to previous ledger
 *     TimePoint closeTime; // network close time
 *
 *     // upgrades to apply to the previous ledger (usually empty)
 *     // this is a vector of encoded 'LedgerUpgrade' so that nodes can drop
 *     // unknown steps during consensus if needed.
 *     // see notes below on 'LedgerUpgrade' for more detail
 *     // max size is dictated by number of upgrade types (+ room for future)
 *     UpgradeType upgrades<6>;
 *
 *     // reserved for future use
 *     union switch (StellarValueType v)
 *     {
 *     case STELLAR_VALUE_BASIC:
 *         void;
 *     case STELLAR_VALUE_SIGNED:
 *         LedgerCloseValueSignature lcValueSignature;
 *     }
 *     ext;
 * };
 */
sealed class StellarValue(
    val type: StellarValueType,
) : XdrElement {
    abstract val txSetHash: Hash
    abstract val closeTime: TimePoint
    abstract val upgrades: List<UpgradeType>

    override fun encode(stream: XdrStream) {
        txSetHash.encode(stream)
        stream.writeULong(closeTime)
        upgrades.encode(stream)
        type.encode(stream)
    }

    data class StellarValueBasic(
        override val txSetHash: Hash,
        override val closeTime: TimePoint,
        override val upgrades: List<UpgradeType>,
    ) : StellarValue(StellarValueType.STELLAR_VALUE_BASIC)

    data class StellarValueSigned(
        override val txSetHash: Hash,
        override val closeTime: TimePoint,
        override val upgrades: List<UpgradeType>,
        val lcValueSignature: LedgerCloseValueSignature,
    ) : StellarValue(StellarValueType.STELLAR_VALUE_SIGNED) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)

            lcValueSignature.encode(stream)
        }
    }

    companion object : XdrElementDecoder<StellarValue> {
        override fun decode(stream: XdrStream): StellarValue {
            val txSetHash = Hash.decode(stream)
            val closeTime = stream.readULong()
            val upgrades = decodeXdrElementList(stream, UpgradeType::decode)

            return when (val type = StellarValueType.decode(stream)) {
                StellarValueType.STELLAR_VALUE_BASIC -> StellarValueBasic(txSetHash, closeTime, upgrades)
                StellarValueType.STELLAR_VALUE_SIGNED -> StellarValueSigned(
                    txSetHash,
                    closeTime,
                    upgrades,
                    LedgerCloseValueSignature.decode(stream)
                )
            }
        }
    }
}