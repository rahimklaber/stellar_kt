// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * AccountEntry's original definition in the XDR file is:
 * ```
 * struct AccountEntry
{
AccountID accountID;      // master public key for this account
int64 balance;            // in stroops
SequenceNumber seqNum;    // last sequence number used for this account
uint32 numSubEntries;     // number of sub-entries this account has
// drives the reserve
AccountID* inflationDest; // Account to vote for during inflation
uint32 flags;             // see AccountFlags

string32 homeDomain; // can be used for reverse federation and memo lookup

// fields used for signatures
// thresholds stores unsigned bytes: [weight of master|low|medium|high]
Thresholds thresholds;

Signer signers<MAX_SIGNERS>; // possible signers for this account

// reserved for future use
union switch (int v)
{
case 0:
void;
case 1:
AccountEntryExtensionV1 v1;
}
ext;
};
 * ```
 */
data class AccountEntry(
    val accountID: AccountID,
    val balance: Int64,
    val seqNum: SequenceNumber,
    val numSubEntries: Uint32,
    val inflationDest: AccountID?,
    val flags: Uint32,
    val homeDomain: String32,
    val thresholds: Thresholds,
    val signers: List<Signer>,
    val ext: AccountEntryExt,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        accountID.encode(stream)
        balance.encode(stream)
        seqNum.encode(stream)
        numSubEntries.encode(stream)
        if (inflationDest != null) {
            stream.writeInt(1)
            inflationDest.encode(stream)
        } else {
            stream.writeInt(0)
        }
        flags.encode(stream)
        homeDomain.encode(stream)
        thresholds.encode(stream)
        val signersSize = signers.size
        stream.writeInt(signersSize)
        signers.encodeXdrElements(stream)
        ext.encode(stream)
    }

    companion object : XdrElementDecoder<AccountEntry> {
        override fun decode(stream: XdrInputStream): AccountEntry {
            val accountID = AccountID.decode(stream)
            val balance = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            val seqNum = SequenceNumber.decode(stream)
            val numSubEntries = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val inflationDestPresent = stream.readInt()
            val inflationDest = if (inflationDestPresent != 0) {
                AccountID.decode(stream)
            } else {
                null
            }
            val flags = me.rahimklaber.stellar.base.xdr.Uint32.decode(stream)
            val homeDomain = String32.decode(stream)
            val thresholds = Thresholds.decode(stream)
            val signersSize = stream.readInt()
            val signers: List<Signer> = decodeXdrElementsList(signersSize, stream, Signer.decoder())
            val ext = AccountEntryExt.decode(stream)
            return AccountEntry(
                accountID,
                balance,
                seqNum,
                numSubEntries,
                inflationDest,
                flags,
                homeDomain,
                thresholds,
                signers,
                ext,
            )
        }
    }

    /**
     * AccountEntryExt's original definition in the XDR file is:
     * ```
     * union switch (int v)
    {
    case 0:
    void;
    case 1:
    AccountEntryExtensionV1 v1;
    }
     * ```
     */
    sealed class AccountEntryExt(val type: Int) : XdrElement {
        data object AccountEntryExtV0 : AccountEntryExt(0) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        fun v1OrNull(): AccountEntryExtV1? = if (this is AccountEntryExtV1) this else null
        data class AccountEntryExtV1(
            val v1: AccountEntryExtensionV1,
        ) : AccountEntryExt(1) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                v1.encode(stream)
            }
        }

        companion object : XdrElementDecoder<AccountEntryExt> {
            override fun decode(stream: XdrInputStream): AccountEntryExt {
                return when (val type = Int.decode(stream)) {
                    0 -> AccountEntryExtV0
                    1 -> {
                        val v1 = AccountEntryExtensionV1.decode(stream)
                        AccountEntryExtV1(v1)
                    }

                    else -> throw IllegalArgumentException("unknown type: $type")
                }
            }
        }
    }
}
