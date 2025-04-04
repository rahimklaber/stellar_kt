// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * AccountMergeResult's original definition in the XDR file is:
 * ```
 * union AccountMergeResult switch (AccountMergeResultCode code)
{
case ACCOUNT_MERGE_SUCCESS:
int64 sourceAccountBalance; // how much got transferred from source account
case ACCOUNT_MERGE_MALFORMED:
case ACCOUNT_MERGE_NO_ACCOUNT:
case ACCOUNT_MERGE_IMMUTABLE_SET:
case ACCOUNT_MERGE_HAS_SUB_ENTRIES:
case ACCOUNT_MERGE_SEQNUM_TOO_FAR:
case ACCOUNT_MERGE_DEST_FULL:
case ACCOUNT_MERGE_IS_SPONSOR:
void;
};
 * ```
 */
sealed class AccountMergeResult(val type: AccountMergeResultCode) : XdrElement {
    fun sourceAccountBalanceOrNull(): AccountMergeSuccess? = if (this is AccountMergeSuccess) this else null
    data class AccountMergeSuccess(
        val sourceAccountBalance: Int64,
    ) : AccountMergeResult(AccountMergeResultCode.ACCOUNT_MERGE_SUCCESS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            sourceAccountBalance.encode(stream)
        }
    }

    data object AccountMergeMalformed : AccountMergeResult(AccountMergeResultCode.ACCOUNT_MERGE_MALFORMED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object AccountMergeNoAccount : AccountMergeResult(AccountMergeResultCode.ACCOUNT_MERGE_NO_ACCOUNT) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object AccountMergeImmutableSet : AccountMergeResult(AccountMergeResultCode.ACCOUNT_MERGE_IMMUTABLE_SET) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object AccountMergeHasSubEntries : AccountMergeResult(AccountMergeResultCode.ACCOUNT_MERGE_HAS_SUB_ENTRIES) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object AccountMergeSeqnumTooFar : AccountMergeResult(AccountMergeResultCode.ACCOUNT_MERGE_SEQNUM_TOO_FAR) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object AccountMergeDestFull : AccountMergeResult(AccountMergeResultCode.ACCOUNT_MERGE_DEST_FULL) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object AccountMergeIsSponsor : AccountMergeResult(AccountMergeResultCode.ACCOUNT_MERGE_IS_SPONSOR) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    companion object : XdrElementDecoder<AccountMergeResult> {
        override fun decode(stream: XdrInputStream): AccountMergeResult {
            return when (val type = AccountMergeResultCode.decode(stream)) {
                AccountMergeResultCode.ACCOUNT_MERGE_SUCCESS -> {
                    val sourceAccountBalance = Int64.decode(stream)
                    AccountMergeSuccess(sourceAccountBalance)
                }

                AccountMergeResultCode.ACCOUNT_MERGE_MALFORMED -> AccountMergeMalformed
                AccountMergeResultCode.ACCOUNT_MERGE_NO_ACCOUNT -> AccountMergeNoAccount
                AccountMergeResultCode.ACCOUNT_MERGE_IMMUTABLE_SET -> AccountMergeImmutableSet
                AccountMergeResultCode.ACCOUNT_MERGE_HAS_SUB_ENTRIES -> AccountMergeHasSubEntries
                AccountMergeResultCode.ACCOUNT_MERGE_SEQNUM_TOO_FAR -> AccountMergeSeqnumTooFar
                AccountMergeResultCode.ACCOUNT_MERGE_DEST_FULL -> AccountMergeDestFull
                AccountMergeResultCode.ACCOUNT_MERGE_IS_SPONSOR -> AccountMergeIsSponsor
                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
