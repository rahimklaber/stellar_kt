package me.rahimklaber.stellar.base.xdr

public enum class LedgerUpgradeType(
    public val `value`: Int,
) : XdrElement {
    LEDGER_UPGRADE_VERSION(1),
    LEDGER_UPGRADE_BASE_FEE(2),
    LEDGER_UPGRADE_MAX_TX_SET_SIZE(3),
    LEDGER_UPGRADE_BASE_RESERVE(4),
    LEDGER_UPGRADE_FLAGS(5),
    LEDGER_UPGRADE_CONFIG(6),
    LEDGER_UPGRADE_MAX_SOROBAN_TX_SET_SIZE(7),
    ;

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    public companion object : XdrElementDecoder<LedgerUpgradeType> {
        override fun decode(stream: XdrStream): LedgerUpgradeType {
            val value = stream.readInt()
            return entries.find { it.value == value } ?: xdrDecodeError("Could not find enum case")
        }
    }
}