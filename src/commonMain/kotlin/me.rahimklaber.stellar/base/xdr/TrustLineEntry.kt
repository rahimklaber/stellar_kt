// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * TrustLineEntry's original definition in the XDR file is:
 * ```
 * struct TrustLineEntry
{
AccountID accountID;  // account this trustline belongs to
TrustLineAsset asset; // type of asset (with issuer)
int64 balance;        // how much of this asset the user has.
// Asset defines the unit for this;

int64 limit;  // balance cannot be above this
uint32 flags; // see TrustLineFlags

// reserved for future use
union switch (int v)
{
case 0:
void;
case 1:
struct
{
Liabilities liabilities;

union switch (int v)
{
case 0:
void;
case 2:
TrustLineEntryExtensionV2 v2;
}
ext;
} v1;
}
ext;
};
 * ```
 */
data class TrustLineEntry(
    val accountID: AccountID,
    val asset: TrustLineAsset,
    val balance: Int64,
    val limit: Int64,
    val flags: Uint32,
    val ext: TrustLineEntryExt,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        accountID.encode(stream)
        asset.encode(stream)
        balance.encode(stream)
        limit.encode(stream)
        flags.encode(stream)
        ext.encode(stream)
    }

    companion object : XdrElementDecoder<TrustLineEntry> {
        override fun decode(stream: XdrInputStream): TrustLineEntry {
            val accountID = AccountID.decode(stream)
            val asset = TrustLineAsset.decode(stream)
            val balance = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val limit = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val flags = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val ext = TrustLineEntryExt.decode(stream)
            return TrustLineEntry(
                accountID,
                asset,
                balance,
                limit,
                flags,
                ext,
            )
        }
    }

    /**
     * TrustLineEntryExt's original definition in the XDR file is:
     * ```
     * union switch (int v)
    {
    case 0:
    void;
    case 1:
    struct
    {
    Liabilities liabilities;

    union switch (int v)
    {
    case 0:
    void;
    case 2:
    TrustLineEntryExtensionV2 v2;
    }
    ext;
    } v1;
    }
     * ```
     */
    sealed class TrustLineEntryExt(val type: Int) : XdrElement {
        data object TrustLineEntryExtV0 : TrustLineEntryExt(0) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        fun v1OrNull(): TrustLineEntryExtV1? = if (this is TrustLineEntryExtV1) this else null
        data class TrustLineEntryExtV1(
            val v1: TrustLineEntryV1Anon,
        ) : TrustLineEntryExt(1) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                v1.encode(stream)
            }
        }

        companion object : XdrElementDecoder<TrustLineEntryExt> {
            override fun decode(stream: XdrInputStream): TrustLineEntryExt {
                return when (val type = Int.decode(stream)) {
                    0 -> TrustLineEntryExtV0
                    1 -> {
                        val v1 = TrustLineEntryV1Anon.decode(stream)
                        TrustLineEntryExtV1(v1)
                    }

                    else -> throw IllegalArgumentException("unknown type: $type")
                }
            }
        }

        /**
         * TrustLineEntryV1Anon's original definition in the XDR file is:
         * ```
         * struct
        {
        Liabilities liabilities;

        union switch (int v)
        {
        case 0:
        void;
        case 2:
        TrustLineEntryExtensionV2 v2;
        }
        ext;
        }
         * ```
         */
        data class TrustLineEntryV1Anon(
            val liabilities: Liabilities,
            val ext: TrustLineEntryV1AnonExt,
        ) : XdrElement {
            override fun encode(stream: XdrOutputStream) {
                liabilities.encode(stream)
                ext.encode(stream)
            }

            companion object : XdrElementDecoder<TrustLineEntryV1Anon> {
                override fun decode(stream: XdrInputStream): TrustLineEntryV1Anon {
                    val liabilities = Liabilities.decode(stream)
                    val ext = TrustLineEntryV1AnonExt.decode(stream)
                    return TrustLineEntryV1Anon(
                        liabilities,
                        ext,
                    )
                }
            }

            /**
             * TrustLineEntryV1AnonExt's original definition in the XDR file is:
             * ```
             * union switch (int v)
            {
            case 0:
            void;
            case 2:
            TrustLineEntryExtensionV2 v2;
            }
             * ```
             */
            sealed class TrustLineEntryV1AnonExt(val type: Int) : XdrElement {
                data object TrustLineEntryV1AnonExtV0 : TrustLineEntryV1AnonExt(0) {
                    override fun encode(stream: XdrOutputStream) {
                        type.encode(stream)
                    }
                }

                fun v2OrNull(): TrustLineEntryV1AnonExtV2? = if (this is TrustLineEntryV1AnonExtV2) this else null
                data class TrustLineEntryV1AnonExtV2(
                    val v2: TrustLineEntryExtensionV2,
                ) : TrustLineEntryV1AnonExt(2) {
                    override fun encode(stream: XdrOutputStream) {
                        type.encode(stream)
                        v2.encode(stream)
                    }
                }

                companion object : XdrElementDecoder<TrustLineEntryV1AnonExt> {
                    override fun decode(stream: XdrInputStream): TrustLineEntryV1AnonExt {
                        return when (val type = Int.decode(stream)) {
                            0 -> TrustLineEntryV1AnonExtV0
                            2 -> {
                                val v2 = TrustLineEntryExtensionV2.decode(stream)
                                TrustLineEntryV1AnonExtV2(v2)
                            }

                            else -> throw IllegalArgumentException("unknown type: $type")
                        }
                    }
                }
            }
        }
    }
}
