// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * UpgradeEntryMeta's original definition in the XDR file is:
 * ```
 * struct UpgradeEntryMeta
{
LedgerUpgrade upgrade;
LedgerEntryChanges changes;
};
 * ```
 */
data class UpgradeEntryMeta(
    val upgrade: LedgerUpgrade,
    val changes: LedgerEntryChanges,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        upgrade.encode(stream)
        changes.encode(stream)
    }

    companion object : XdrElementDecoder<UpgradeEntryMeta> {
        override fun decode(stream: XdrInputStream): UpgradeEntryMeta {
            val upgrade = LedgerUpgrade.decode(stream)
            val changes = LedgerEntryChanges.decode(stream)
            return UpgradeEntryMeta(
                upgrade,
                changes,
            )
        }
    }
}
