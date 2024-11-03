package me.rahimklaber.stellar.base.xdr

public sealed class LedgerUpgrade(
    public val type: LedgerUpgradeType,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    public data class UpgradeVersion(
        public val newLedgerVersion: UInt,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_VERSION) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(newLedgerVersion.toInt())
        }
    }

    public data class UpgradeBaseFee(
        public val newBaseFee: UInt,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_BASE_FEE) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(newBaseFee.toInt())
        }
    }

    public data class UpgradeMaxTxSetSize(
        public val newMaxTxSetSize: UInt,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_MAX_TX_SET_SIZE) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(newMaxTxSetSize.toInt())
        }
    }

    public data class UpgradeBaseReserve(
        public val newBaseReserve: UInt,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_BASE_RESERVE) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(newBaseReserve.toInt())
        }
    }

    public data class UpgradeFlags(
        public val newFlags: UInt,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_FLAGS) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(newFlags.toInt())
        }
    }

    public data class UpgradeConfig(
        public val newConfig: ConfigUpgradeSetKey,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_CONFIG) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            newConfig.encode(stream)
        }
    }

    public data class UpgradeMaxSorobanTxSetSize(
        public val newMaxSorobanTxSetSize: UInt,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_MAX_SOROBAN_TX_SET_SIZE) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(newMaxSorobanTxSetSize.toInt())
        }
    }

    public companion object : XdrElementDecoder<LedgerUpgrade> {
        override fun decode(stream: XdrStream): LedgerUpgrade {
            return when(val type = LedgerUpgradeType.decode(stream)){
                LedgerUpgradeType.LEDGER_UPGRADE_VERSION -> {
                    val newLedgerVersion =stream.readInt().toUInt()
                    UpgradeVersion(newLedgerVersion)
                }
                LedgerUpgradeType.LEDGER_UPGRADE_BASE_FEE -> {
                    val newBaseFee =stream.readInt().toUInt()
                    UpgradeBaseFee(newBaseFee)
                }
                LedgerUpgradeType.LEDGER_UPGRADE_MAX_TX_SET_SIZE -> {
                    val newMaxTxSetSize =stream.readInt().toUInt()
                    UpgradeMaxTxSetSize(newMaxTxSetSize)
                }
                LedgerUpgradeType.LEDGER_UPGRADE_BASE_RESERVE -> {
                    val newBaseReserve =stream.readInt().toUInt()
                    UpgradeBaseReserve(newBaseReserve)
                }
                LedgerUpgradeType.LEDGER_UPGRADE_FLAGS -> {
                    val newFlags =stream.readInt().toUInt()
                    UpgradeFlags(newFlags)
                }
                LedgerUpgradeType.LEDGER_UPGRADE_CONFIG -> {
                    val newConfig = ConfigUpgradeSetKey.decode(stream)
                    UpgradeConfig(newConfig)
                }
                LedgerUpgradeType.LEDGER_UPGRADE_MAX_SOROBAN_TX_SET_SIZE -> {
                    val newMaxSorobanTxSetSize = stream.readInt().toUInt()
                    UpgradeMaxSorobanTxSetSize(newMaxSorobanTxSetSize)
                }
            }
        }
    }
}