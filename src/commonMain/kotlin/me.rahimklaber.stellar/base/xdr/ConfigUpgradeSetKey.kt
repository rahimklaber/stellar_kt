package me.rahimklaber.stellar.base.xdr

public data class ConfigUpgradeSetKey(
    public val contractID: Hash,
    public val contentHash: Hash,
) : XdrElement {
    override fun encode(stream: XdrStream) {
        contractID.encode(stream)
        contentHash.encode(stream)
    }

    public companion object : XdrElementDecoder<ConfigUpgradeSetKey> {
        override fun decode(stream: XdrStream): ConfigUpgradeSetKey {
            val contractID = Hash.decode(stream)
            val contentHash = Hash.decode(stream)
            return ConfigUpgradeSetKey(contractID,contentHash)
        }
    }
}