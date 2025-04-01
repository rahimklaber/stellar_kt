// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * LedgerEntry's original definition in the XDR file is:
 * ```
 * struct LedgerEntry
{
uint32 lastModifiedLedgerSeq; // ledger the LedgerEntry was last changed

union switch (LedgerEntryType type)
{
case ACCOUNT:
AccountEntry account;
case TRUSTLINE:
TrustLineEntry trustLine;
case OFFER:
OfferEntry offer;
case DATA:
DataEntry data;
case CLAIMABLE_BALANCE:
ClaimableBalanceEntry claimableBalance;
case LIQUIDITY_POOL:
LiquidityPoolEntry liquidityPool;
case CONTRACT_DATA:
ContractDataEntry contractData;
case CONTRACT_CODE:
ContractCodeEntry contractCode;
case CONFIG_SETTING:
ConfigSettingEntry configSetting;
case TTL:
TTLEntry ttl;
}
data;

// reserved for future use
union switch (int v)
{
case 0:
void;
case 1:
LedgerEntryExtensionV1 v1;
}
ext;
};
 * ```
 */
data class LedgerEntry(
    val lastModifiedLedgerSeq: Uint32,
    val data: LedgerEntryData,
    val ext: LedgerEntryExt,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        lastModifiedLedgerSeq.encode(stream)
        data.encode(stream)
        ext.encode(stream)
    }

    companion object : XdrElementDecoder<LedgerEntry> {
        override fun decode(stream: XdrInputStream): LedgerEntry {
            val lastModifiedLedgerSeq = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val data = LedgerEntryData.decode(stream)
            val ext = LedgerEntryExt.decode(stream)
            return LedgerEntry(
                lastModifiedLedgerSeq,
                data,
                ext,
            )
        }
    }

    /**
     * LedgerEntryData's original definition in the XDR file is:
     * ```
     * union switch (LedgerEntryType type)
    {
    case ACCOUNT:
    AccountEntry account;
    case TRUSTLINE:
    TrustLineEntry trustLine;
    case OFFER:
    OfferEntry offer;
    case DATA:
    DataEntry data;
    case CLAIMABLE_BALANCE:
    ClaimableBalanceEntry claimableBalance;
    case LIQUIDITY_POOL:
    LiquidityPoolEntry liquidityPool;
    case CONTRACT_DATA:
    ContractDataEntry contractData;
    case CONTRACT_CODE:
    ContractCodeEntry contractCode;
    case CONFIG_SETTING:
    ConfigSettingEntry configSetting;
    case TTL:
    TTLEntry ttl;
    }
     * ```
     */
    sealed class LedgerEntryData(val type: LedgerEntryType) : XdrElement {
        fun accountOrNull(): Account? = if (this is Account) this else null
        data class Account(
            val account: AccountEntry,
        ) : LedgerEntryData(LedgerEntryType.ACCOUNT) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                account.encode(stream)
            }
        }

        fun trustLineOrNull(): Trustline? = if (this is Trustline) this else null
        data class Trustline(
            val trustLine: TrustLineEntry,
        ) : LedgerEntryData(LedgerEntryType.TRUSTLINE) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                trustLine.encode(stream)
            }
        }

        fun offerOrNull(): Offer? = if (this is Offer) this else null
        data class Offer(
            val offer: OfferEntry,
        ) : LedgerEntryData(LedgerEntryType.OFFER) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                offer.encode(stream)
            }
        }

        fun dataOrNull(): Data? = if (this is Data) this else null
        data class Data(
            val data: DataEntry,
        ) : LedgerEntryData(LedgerEntryType.DATA) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                data.encode(stream)
            }
        }

        fun claimableBalanceOrNull(): ClaimableBalance? = if (this is ClaimableBalance) this else null
        data class ClaimableBalance(
            val claimableBalance: ClaimableBalanceEntry,
        ) : LedgerEntryData(LedgerEntryType.CLAIMABLE_BALANCE) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                claimableBalance.encode(stream)
            }
        }

        fun liquidityPoolOrNull(): LiquidityPool? = if (this is LiquidityPool) this else null
        data class LiquidityPool(
            val liquidityPool: LiquidityPoolEntry,
        ) : LedgerEntryData(LedgerEntryType.LIQUIDITY_POOL) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                liquidityPool.encode(stream)
            }
        }

        fun contractDataOrNull(): ContractData? = if (this is ContractData) this else null
        data class ContractData(
            val contractData: ContractDataEntry,
        ) : LedgerEntryData(LedgerEntryType.CONTRACT_DATA) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                contractData.encode(stream)
            }
        }

        fun contractCodeOrNull(): ContractCode? = if (this is ContractCode) this else null
        data class ContractCode(
            val contractCode: ContractCodeEntry,
        ) : LedgerEntryData(LedgerEntryType.CONTRACT_CODE) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                contractCode.encode(stream)
            }
        }

        fun configSettingOrNull(): ConfigSetting? = if (this is ConfigSetting) this else null
        data class ConfigSetting(
            val configSetting: ConfigSettingEntry,
        ) : LedgerEntryData(LedgerEntryType.CONFIG_SETTING) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                configSetting.encode(stream)
            }
        }

        fun ttlOrNull(): Ttl? = if (this is Ttl) this else null
        data class Ttl(
            val ttl: TTLEntry,
        ) : LedgerEntryData(LedgerEntryType.TTL) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                ttl.encode(stream)
            }
        }

        companion object : XdrElementDecoder<LedgerEntryData> {
            override fun decode(stream: XdrInputStream): LedgerEntryData {
                return when (val type = LedgerEntryType.decode(stream)) {
                    LedgerEntryType.ACCOUNT -> {
                        val account = AccountEntry.decode(stream)
                        Account(account)
                    }

                    LedgerEntryType.TRUSTLINE -> {
                        val trustLine = TrustLineEntry.decode(stream)
                        Trustline(trustLine)
                    }

                    LedgerEntryType.OFFER -> {
                        val offer = OfferEntry.decode(stream)
                        Offer(offer)
                    }

                    LedgerEntryType.DATA -> {
                        val data = DataEntry.decode(stream)
                        Data(data)
                    }

                    LedgerEntryType.CLAIMABLE_BALANCE -> {
                        val claimableBalance = ClaimableBalanceEntry.decode(stream)
                        ClaimableBalance(claimableBalance)
                    }

                    LedgerEntryType.LIQUIDITY_POOL -> {
                        val liquidityPool = LiquidityPoolEntry.decode(stream)
                        LiquidityPool(liquidityPool)
                    }

                    LedgerEntryType.CONTRACT_DATA -> {
                        val contractData = ContractDataEntry.decode(stream)
                        ContractData(contractData)
                    }

                    LedgerEntryType.CONTRACT_CODE -> {
                        val contractCode = ContractCodeEntry.decode(stream)
                        ContractCode(contractCode)
                    }

                    LedgerEntryType.CONFIG_SETTING -> {
                        val configSetting = ConfigSettingEntry.decode(stream)
                        ConfigSetting(configSetting)
                    }

                    LedgerEntryType.TTL -> {
                        val ttl = TTLEntry.decode(stream)
                        Ttl(ttl)
                    }

                    else -> throw IllegalArgumentException("unknown type: $type")
                }
            }
        }
    }

    /**
     * LedgerEntryExt's original definition in the XDR file is:
     * ```
     * union switch (int v)
    {
    case 0:
    void;
    case 1:
    LedgerEntryExtensionV1 v1;
    }
     * ```
     */
    sealed class LedgerEntryExt(val type: Int) : XdrElement {
        data object LedgerEntryExtV0 : LedgerEntryExt(0) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        fun v1OrNull(): LedgerEntryExtV1? = if (this is LedgerEntryExtV1) this else null
        data class LedgerEntryExtV1(
            val v1: LedgerEntryExtensionV1,
        ) : LedgerEntryExt(1) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                v1.encode(stream)
            }
        }

        companion object : XdrElementDecoder<LedgerEntryExt> {
            override fun decode(stream: XdrInputStream): LedgerEntryExt {
                return when (val type = Int.decode(stream)) {
                    0 -> LedgerEntryExtV0
                    1 -> {
                        val v1 = LedgerEntryExtensionV1.decode(stream)
                        LedgerEntryExtV1(v1)
                    }

                    else -> throw IllegalArgumentException("unknown type: $type")
                }
            }
        }
    }
}
