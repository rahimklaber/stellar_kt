// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * LedgerKey's original definition in the XDR file is:
 * ```
 * union LedgerKey switch (LedgerEntryType type)
{
case ACCOUNT:
struct
{
AccountID accountID;
} account;

case TRUSTLINE:
struct
{
AccountID accountID;
TrustLineAsset asset;
} trustLine;

case OFFER:
struct
{
AccountID sellerID;
int64 offerID;
} offer;

case DATA:
struct
{
AccountID accountID;
string64 dataName;
} data;

case CLAIMABLE_BALANCE:
struct
{
ClaimableBalanceID balanceID;
} claimableBalance;

case LIQUIDITY_POOL:
struct
{
PoolID liquidityPoolID;
} liquidityPool;
case CONTRACT_DATA:
struct
{
SCAddress contract;
SCVal key;
ContractDataDurability durability;
} contractData;
case CONTRACT_CODE:
struct
{
Hash hash;
} contractCode;
case CONFIG_SETTING:
struct
{
ConfigSettingID configSettingID;
} configSetting;
case TTL:
struct
{
// Hash of the LedgerKey that is associated with this TTLEntry
Hash keyHash;
} ttl;
};
 * ```
 */
sealed class LedgerKey(val type: LedgerEntryType) : XdrElement {
    fun accountOrNull(): Account? = if (this is Account) this else null
    data class Account(
        val account: LedgerKeyAccount,
    ) : LedgerKey(LedgerEntryType.ACCOUNT) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            account.encode(stream)
        }
    }

    fun trustLineOrNull(): Trustline? = if (this is Trustline) this else null
    data class Trustline(
        val trustLine: LedgerKeyTrustLine,
    ) : LedgerKey(LedgerEntryType.TRUSTLINE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            trustLine.encode(stream)
        }
    }

    fun offerOrNull(): Offer? = if (this is Offer) this else null
    data class Offer(
        val offer: LedgerKeyOffer,
    ) : LedgerKey(LedgerEntryType.OFFER) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            offer.encode(stream)
        }
    }

    fun dataOrNull(): Data? = if (this is Data) this else null
    data class Data(
        val data: LedgerKeyData,
    ) : LedgerKey(LedgerEntryType.DATA) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            data.encode(stream)
        }
    }

    fun claimableBalanceOrNull(): ClaimableBalance? = if (this is ClaimableBalance) this else null
    data class ClaimableBalance(
        val claimableBalance: LedgerKeyClaimableBalance,
    ) : LedgerKey(LedgerEntryType.CLAIMABLE_BALANCE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            claimableBalance.encode(stream)
        }
    }

    fun liquidityPoolOrNull(): LiquidityPool? = if (this is LiquidityPool) this else null
    data class LiquidityPool(
        val liquidityPool: LedgerKeyLiquidityPool,
    ) : LedgerKey(LedgerEntryType.LIQUIDITY_POOL) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            liquidityPool.encode(stream)
        }
    }

    fun contractDataOrNull(): ContractData? = if (this is ContractData) this else null
    data class ContractData(
        val contractData: LedgerKeyContractData,
    ) : LedgerKey(LedgerEntryType.CONTRACT_DATA) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            contractData.encode(stream)
        }
    }

    fun contractCodeOrNull(): ContractCode? = if (this is ContractCode) this else null
    data class ContractCode(
        val contractCode: LedgerKeyContractCode,
    ) : LedgerKey(LedgerEntryType.CONTRACT_CODE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            contractCode.encode(stream)
        }
    }

    fun configSettingOrNull(): ConfigSetting? = if (this is ConfigSetting) this else null
    data class ConfigSetting(
        val configSetting: LedgerKeyConfigSetting,
    ) : LedgerKey(LedgerEntryType.CONFIG_SETTING) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            configSetting.encode(stream)
        }
    }

    fun ttlOrNull(): Ttl? = if (this is Ttl) this else null
    data class Ttl(
        val ttl: LedgerKeyTtl,
    ) : LedgerKey(LedgerEntryType.TTL) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            ttl.encode(stream)
        }
    }

    companion object : XdrElementDecoder<LedgerKey> {
        override fun decode(stream: XdrInputStream): LedgerKey {
            return when (val type = LedgerEntryType.decode(stream)) {
                LedgerEntryType.ACCOUNT -> {
                    val account = LedgerKeyAccount.decode(stream)
                    Account(account)
                }

                LedgerEntryType.TRUSTLINE -> {
                    val trustLine = LedgerKeyTrustLine.decode(stream)
                    Trustline(trustLine)
                }

                LedgerEntryType.OFFER -> {
                    val offer = LedgerKeyOffer.decode(stream)
                    Offer(offer)
                }

                LedgerEntryType.DATA -> {
                    val data = LedgerKeyData.decode(stream)
                    Data(data)
                }

                LedgerEntryType.CLAIMABLE_BALANCE -> {
                    val claimableBalance = LedgerKeyClaimableBalance.decode(stream)
                    ClaimableBalance(claimableBalance)
                }

                LedgerEntryType.LIQUIDITY_POOL -> {
                    val liquidityPool = LedgerKeyLiquidityPool.decode(stream)
                    LiquidityPool(liquidityPool)
                }

                LedgerEntryType.CONTRACT_DATA -> {
                    val contractData = LedgerKeyContractData.decode(stream)
                    ContractData(contractData)
                }

                LedgerEntryType.CONTRACT_CODE -> {
                    val contractCode = LedgerKeyContractCode.decode(stream)
                    ContractCode(contractCode)
                }

                LedgerEntryType.CONFIG_SETTING -> {
                    val configSetting = LedgerKeyConfigSetting.decode(stream)
                    ConfigSetting(configSetting)
                }

                LedgerEntryType.TTL -> {
                    val ttl = LedgerKeyTtl.decode(stream)
                    Ttl(ttl)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }

    /**
     * LedgerKeyAccount's original definition in the XDR file is:
     * ```
     * struct
    {
    AccountID accountID;
    }
     * ```
     */
    data class LedgerKeyAccount(
        val accountID: AccountID,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            accountID.encode(stream)
        }

        companion object : XdrElementDecoder<LedgerKeyAccount> {
            override fun decode(stream: XdrInputStream): LedgerKeyAccount {
                val accountID = AccountID.decode(stream)
                return LedgerKeyAccount(
                    accountID,
                )
            }
        }

    }

    /**
     * LedgerKeyTrustLine's original definition in the XDR file is:
     * ```
     * struct
    {
    AccountID accountID;
    TrustLineAsset asset;
    }
     * ```
     */
    data class LedgerKeyTrustLine(
        val accountID: AccountID,
        val asset: TrustLineAsset,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            accountID.encode(stream)
            asset.encode(stream)
        }

        companion object : XdrElementDecoder<LedgerKeyTrustLine> {
            override fun decode(stream: XdrInputStream): LedgerKeyTrustLine {
                val accountID = AccountID.decode(stream)
                val asset = TrustLineAsset.decode(stream)
                return LedgerKeyTrustLine(
                    accountID,
                    asset,
                )
            }
        }

    }

    /**
     * LedgerKeyOffer's original definition in the XDR file is:
     * ```
     * struct
    {
    AccountID sellerID;
    int64 offerID;
    }
     * ```
     */
    data class LedgerKeyOffer(
        val sellerID: AccountID,
        val offerID: Int64,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            sellerID.encode(stream)
            offerID.encode(stream)
        }

        companion object : XdrElementDecoder<LedgerKeyOffer> {
            override fun decode(stream: XdrInputStream): LedgerKeyOffer {
                val sellerID = AccountID.decode(stream)
                val offerID = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
                return LedgerKeyOffer(
                    sellerID,
                    offerID,
                )
            }
        }

    }

    /**
     * LedgerKeyData's original definition in the XDR file is:
     * ```
     * struct
    {
    AccountID accountID;
    string64 dataName;
    }
     * ```
     */
    data class LedgerKeyData(
        val accountID: AccountID,
        val dataName: String64,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            accountID.encode(stream)
            dataName.encode(stream)
        }

        companion object : XdrElementDecoder<LedgerKeyData> {
            override fun decode(stream: XdrInputStream): LedgerKeyData {
                val accountID = AccountID.decode(stream)
                val dataName = String64.decode(stream)
                return LedgerKeyData(
                    accountID,
                    dataName,
                )
            }
        }

    }

    /**
     * LedgerKeyClaimableBalance's original definition in the XDR file is:
     * ```
     * struct
    {
    ClaimableBalanceID balanceID;
    }
     * ```
     */
    data class LedgerKeyClaimableBalance(
        val balanceID: ClaimableBalanceID,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            balanceID.encode(stream)
        }

        companion object : XdrElementDecoder<LedgerKeyClaimableBalance> {
            override fun decode(stream: XdrInputStream): LedgerKeyClaimableBalance {
                val balanceID = ClaimableBalanceID.decode(stream)
                return LedgerKeyClaimableBalance(
                    balanceID,
                )
            }
        }

    }

    /**
     * LedgerKeyLiquidityPool's original definition in the XDR file is:
     * ```
     * struct
    {
    PoolID liquidityPoolID;
    }
     * ```
     */
    data class LedgerKeyLiquidityPool(
        val liquidityPoolID: PoolID,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            liquidityPoolID.encode(stream)
        }

        companion object : XdrElementDecoder<LedgerKeyLiquidityPool> {
            override fun decode(stream: XdrInputStream): LedgerKeyLiquidityPool {
                val liquidityPoolID = PoolID.decode(stream)
                return LedgerKeyLiquidityPool(
                    liquidityPoolID,
                )
            }
        }

    }

    /**
     * LedgerKeyContractData's original definition in the XDR file is:
     * ```
     * struct
    {
    SCAddress contract;
    SCVal key;
    ContractDataDurability durability;
    }
     * ```
     */
    data class LedgerKeyContractData(
        val contract: SCAddress,
        val key: SCVal,
        val durability: ContractDataDurability,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            contract.encode(stream)
            key.encode(stream)
            durability.encode(stream)
        }

        companion object : XdrElementDecoder<LedgerKeyContractData> {
            override fun decode(stream: XdrInputStream): LedgerKeyContractData {
                val contract = SCAddress.decode(stream)
                val key = SCVal.decode(stream)
                val durability = ContractDataDurability.decode(stream)
                return LedgerKeyContractData(
                    contract,
                    key,
                    durability,
                )
            }
        }

    }

    /**
     * LedgerKeyContractCode's original definition in the XDR file is:
     * ```
     * struct
    {
    Hash hash;
    }
     * ```
     */
    data class LedgerKeyContractCode(
        val hash: Hash,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            hash.encode(stream)
        }

        companion object : XdrElementDecoder<LedgerKeyContractCode> {
            override fun decode(stream: XdrInputStream): LedgerKeyContractCode {
                val hash = Hash.decode(stream)
                return LedgerKeyContractCode(
                    hash,
                )
            }
        }

    }

    /**
     * LedgerKeyConfigSetting's original definition in the XDR file is:
     * ```
     * struct
    {
    ConfigSettingID configSettingID;
    }
     * ```
     */
    data class LedgerKeyConfigSetting(
        val configSettingID: ConfigSettingID,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            configSettingID.encode(stream)
        }

        companion object : XdrElementDecoder<LedgerKeyConfigSetting> {
            override fun decode(stream: XdrInputStream): LedgerKeyConfigSetting {
                val configSettingID = ConfigSettingID.decode(stream)
                return LedgerKeyConfigSetting(
                    configSettingID,
                )
            }
        }

    }

    /**
     * LedgerKeyTtl's original definition in the XDR file is:
     * ```
     * struct
    {
    // Hash of the LedgerKey that is associated with this TTLEntry
    Hash keyHash;
    }
     * ```
     */
    data class LedgerKeyTtl(
        val keyHash: Hash,
    ) : XdrElement {
        override fun encode(stream: XdrOutputStream) {
            keyHash.encode(stream)
        }

        companion object : XdrElementDecoder<LedgerKeyTtl> {
            override fun decode(stream: XdrInputStream): LedgerKeyTtl {
                val keyHash = Hash.decode(stream)
                return LedgerKeyTtl(
                    keyHash,
                )
            }
        }

    }
}
