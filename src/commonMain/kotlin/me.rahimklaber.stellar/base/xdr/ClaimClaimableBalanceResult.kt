// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ClaimClaimableBalanceResult's original definition in the XDR file is:
 * ```
 * union ClaimClaimableBalanceResult switch (ClaimClaimableBalanceResultCode code)
{
case CLAIM_CLAIMABLE_BALANCE_SUCCESS:
void;
case CLAIM_CLAIMABLE_BALANCE_DOES_NOT_EXIST:
case CLAIM_CLAIMABLE_BALANCE_CANNOT_CLAIM:
case CLAIM_CLAIMABLE_BALANCE_LINE_FULL:
case CLAIM_CLAIMABLE_BALANCE_NO_TRUST:
case CLAIM_CLAIMABLE_BALANCE_NOT_AUTHORIZED:
void;
};
 * ```
 */
sealed class ClaimClaimableBalanceResult(val type: ClaimClaimableBalanceResultCode) : XdrElement {
    data object ClaimClaimableBalanceSuccess : ClaimClaimableBalanceResult(ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_SUCCESS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ClaimClaimableBalanceDoesNotExist :
        ClaimClaimableBalanceResult(ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_DOES_NOT_EXIST) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ClaimClaimableBalanceCannotClaim : ClaimClaimableBalanceResult(ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_CANNOT_CLAIM) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ClaimClaimableBalanceLineFull : ClaimClaimableBalanceResult(ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_LINE_FULL) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ClaimClaimableBalanceNoTrust : ClaimClaimableBalanceResult(ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_NO_TRUST) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ClaimClaimableBalanceNotAuthorized :
        ClaimClaimableBalanceResult(ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_NOT_AUTHORIZED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    companion object : XdrElementDecoder<ClaimClaimableBalanceResult> {
        override fun decode(stream: XdrInputStream): ClaimClaimableBalanceResult {
            return when (val type = ClaimClaimableBalanceResultCode.decode(stream)) {
                ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_SUCCESS -> ClaimClaimableBalanceSuccess
                ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_DOES_NOT_EXIST -> ClaimClaimableBalanceDoesNotExist
                ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_CANNOT_CLAIM -> ClaimClaimableBalanceCannotClaim
                ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_LINE_FULL -> ClaimClaimableBalanceLineFull
                ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_NO_TRUST -> ClaimClaimableBalanceNoTrust
                ClaimClaimableBalanceResultCode.CLAIM_CLAIMABLE_BALANCE_NOT_AUTHORIZED -> ClaimClaimableBalanceNotAuthorized
                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
