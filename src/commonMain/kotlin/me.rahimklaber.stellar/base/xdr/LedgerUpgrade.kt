// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * LedgerUpgrade's original definition in the XDR file is:
 * ```
 * union LedgerUpgrade switch (LedgerUpgradeType type)
{
case LEDGER_UPGRADE_VERSION:
uint32 newLedgerVersion; // update ledgerVersion
case LEDGER_UPGRADE_BASE_FEE:
uint32 newBaseFee; // update baseFee
case LEDGER_UPGRADE_MAX_TX_SET_SIZE:
uint32 newMaxTxSetSize; // update maxTxSetSize
case LEDGER_UPGRADE_BASE_RESERVE:
uint32 newBaseReserve; // update baseReserve
case LEDGER_UPGRADE_FLAGS:
uint32 newFlags; // update flags
case LEDGER_UPGRADE_CONFIG:
// Update arbitrary `ConfigSetting` entries identified by the key.
ConfigUpgradeSetKey newConfig;
case LEDGER_UPGRADE_MAX_SOROBAN_TX_SET_SIZE:
// Update ConfigSettingContractExecutionLanesV0.ledgerMaxTxCount without
// using `LEDGER_UPGRADE_CONFIG`.
uint32 newMaxSorobanTxSetSize;
};
 * ```
 */
sealed class LedgerUpgrade(val type: LedgerUpgradeType) : XdrElement {
    fun newLedgerVersionOrNull(): Version? = if (this is Version) this else null
    data class Version(
        val newLedgerVersion: Uint32,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_VERSION) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            newLedgerVersion.encode(stream)
        }
    }

    fun newBaseFeeOrNull(): BaseFee? = if (this is BaseFee) this else null
    data class BaseFee(
        val newBaseFee: Uint32,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_BASE_FEE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            newBaseFee.encode(stream)
        }
    }

    fun newMaxTxSetSizeOrNull(): MaxTxSetSize? = if (this is MaxTxSetSize) this else null
    data class MaxTxSetSize(
        val newMaxTxSetSize: Uint32,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_MAX_TX_SET_SIZE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            newMaxTxSetSize.encode(stream)
        }
    }

    fun newBaseReserveOrNull(): BaseReserve? = if (this is BaseReserve) this else null
    data class BaseReserve(
        val newBaseReserve: Uint32,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_BASE_RESERVE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            newBaseReserve.encode(stream)
        }
    }

    fun newFlagsOrNull(): Flags? = if (this is Flags) this else null
    data class Flags(
        val newFlags: Uint32,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_FLAGS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            newFlags.encode(stream)
        }
    }

    fun newConfigOrNull(): Config? = if (this is Config) this else null
    data class Config(
        val newConfig: ConfigUpgradeSetKey,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_CONFIG) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            newConfig.encode(stream)
        }
    }

    fun newMaxSorobanTxSetSizeOrNull(): MaxSorobanTxSetSize? = if (this is MaxSorobanTxSetSize) this else null
    data class MaxSorobanTxSetSize(
        val newMaxSorobanTxSetSize: Uint32,
    ) : LedgerUpgrade(LedgerUpgradeType.LEDGER_UPGRADE_MAX_SOROBAN_TX_SET_SIZE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            newMaxSorobanTxSetSize.encode(stream)
        }
    }

    companion object : XdrElementDecoder<LedgerUpgrade> {
        override fun decode(stream: XdrInputStream): LedgerUpgrade {
            return when (val type = LedgerUpgradeType.decode(stream)) {
                LedgerUpgradeType.LEDGER_UPGRADE_VERSION -> {
                    val newLedgerVersion = Uint32.decode(stream)
                    Version(newLedgerVersion)
                }

                LedgerUpgradeType.LEDGER_UPGRADE_BASE_FEE -> {
                    val newBaseFee = Uint32.decode(stream)
                    BaseFee(newBaseFee)
                }

                LedgerUpgradeType.LEDGER_UPGRADE_MAX_TX_SET_SIZE -> {
                    val newMaxTxSetSize = Uint32.decode(stream)
                    MaxTxSetSize(newMaxTxSetSize)
                }

                LedgerUpgradeType.LEDGER_UPGRADE_BASE_RESERVE -> {
                    val newBaseReserve = Uint32.decode(stream)
                    BaseReserve(newBaseReserve)
                }

                LedgerUpgradeType.LEDGER_UPGRADE_FLAGS -> {
                    val newFlags = Uint32.decode(stream)
                    Flags(newFlags)
                }

                LedgerUpgradeType.LEDGER_UPGRADE_CONFIG -> {
                    val newConfig = ConfigUpgradeSetKey.decode(stream)
                    Config(newConfig)
                }

                LedgerUpgradeType.LEDGER_UPGRADE_MAX_SOROBAN_TX_SET_SIZE -> {
                    val newMaxSorobanTxSetSize = Uint32.decode(stream)
                    MaxSorobanTxSetSize(newMaxSorobanTxSetSize)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
