package me.rahimklaber.stellar.base.xdr

/**
 * // this represents a single upgrade that was performed as part of a ledger
 * // upgrade
 * struct UpgradeEntryMeta
 * {
 *     LedgerUpgrade upgrade;
 *     LedgerEntryChanges changes;
 * };
 */
data class UpgradeEntryMeta(
    val upgrade: LedgerUpgrade,
    val changes: LedgerEntryChanges,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        upgrade.encode(stream)
        changes.encode(stream)
    }

    companion object : XdrElementDecoder<UpgradeEntryMeta> {
        override fun decode(stream: XdrStream): UpgradeEntryMeta {
            val upgrade = LedgerUpgrade.decode(stream)
            val changes = decodeXdrElementList(stream, LedgerEntryChange::decode)
            return UpgradeEntryMeta(upgrade, changes)
        }
    }
}