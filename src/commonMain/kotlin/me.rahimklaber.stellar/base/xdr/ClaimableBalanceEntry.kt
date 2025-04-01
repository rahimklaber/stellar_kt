// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ClaimableBalanceEntry's original definition in the XDR file is:
 * ```
 * struct ClaimableBalanceEntry
{
// Unique identifier for this ClaimableBalanceEntry
ClaimableBalanceID balanceID;

// List of claimants with associated predicate
Claimant claimants<10>;

// Any asset including native
Asset asset;

// Amount of asset
int64 amount;

// reserved for future use
union switch (int v)
{
case 0:
void;
case 1:
ClaimableBalanceEntryExtensionV1 v1;
}
ext;
};
 * ```
 */
data class ClaimableBalanceEntry(
    val balanceID: ClaimableBalanceID,
    val claimants: List<Claimant>,
    val asset: Asset,
    val amount: Int64,
    val ext: ClaimableBalanceEntryExt,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        balanceID.encode(stream)
        val claimantsSize = claimants.size
        stream.writeInt(claimantsSize)
        claimants.encodeXdrElements(stream)
        asset.encode(stream)
        amount.encode(stream)
        ext.encode(stream)
    }

    companion object : XdrElementDecoder<ClaimableBalanceEntry> {
        override fun decode(stream: XdrInputStream): ClaimableBalanceEntry {
            val balanceID = ClaimableBalanceID.decode(stream)
            val claimantsSize = stream.readInt()
            val claimants: List<Claimant> = decodeXdrElementsList(claimantsSize, stream, Claimant.decoder())
            val asset = Asset.decode(stream)
            val amount = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val ext = ClaimableBalanceEntryExt.decode(stream)
            return ClaimableBalanceEntry(
                balanceID,
                claimants,
                asset,
                amount,
                ext,
            )
        }
    }

    /**
     * ClaimableBalanceEntryExt's original definition in the XDR file is:
     * ```
     * union switch (int v)
    {
    case 0:
    void;
    case 1:
    ClaimableBalanceEntryExtensionV1 v1;
    }
     * ```
     */
    sealed class ClaimableBalanceEntryExt(val type: Int) : XdrElement {
        data object ClaimableBalanceEntryExtV0 : ClaimableBalanceEntryExt(0) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        fun v1OrNull(): ClaimableBalanceEntryExtV1? = if (this is ClaimableBalanceEntryExtV1) this else null
        data class ClaimableBalanceEntryExtV1(
            val v1: ClaimableBalanceEntryExtensionV1,
        ) : ClaimableBalanceEntryExt(1) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                v1.encode(stream)
            }
        }

        companion object : XdrElementDecoder<ClaimableBalanceEntryExt> {
            override fun decode(stream: XdrInputStream): ClaimableBalanceEntryExt {
                return when (val type = Int.decode(stream)) {
                    0 -> ClaimableBalanceEntryExtV0
                    1 -> {
                        val v1 = ClaimableBalanceEntryExtensionV1.decode(stream)
                        ClaimableBalanceEntryExtV1(v1)
                    }

                    else -> throw IllegalArgumentException("unknown type: $type")
                }
            }
        }
    }
}
