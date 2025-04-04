// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ChangeTrustResult's original definition in the XDR file is:
 * ```
 * union ChangeTrustResult switch (ChangeTrustResultCode code)
{
case CHANGE_TRUST_SUCCESS:
void;
case CHANGE_TRUST_MALFORMED:
case CHANGE_TRUST_NO_ISSUER:
case CHANGE_TRUST_INVALID_LIMIT:
case CHANGE_TRUST_LOW_RESERVE:
case CHANGE_TRUST_SELF_NOT_ALLOWED:
case CHANGE_TRUST_TRUST_LINE_MISSING:
case CHANGE_TRUST_CANNOT_DELETE:
case CHANGE_TRUST_NOT_AUTH_MAINTAIN_LIABILITIES:
void;
};
 * ```
 */
sealed class ChangeTrustResult(val type: ChangeTrustResultCode) : XdrElement {
    data object ChangeTrustSuccess : ChangeTrustResult(ChangeTrustResultCode.CHANGE_TRUST_SUCCESS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ChangeTrustMalformed : ChangeTrustResult(ChangeTrustResultCode.CHANGE_TRUST_MALFORMED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ChangeTrustNoIssuer : ChangeTrustResult(ChangeTrustResultCode.CHANGE_TRUST_NO_ISSUER) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ChangeTrustInvalidLimit : ChangeTrustResult(ChangeTrustResultCode.CHANGE_TRUST_INVALID_LIMIT) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ChangeTrustLowReserve : ChangeTrustResult(ChangeTrustResultCode.CHANGE_TRUST_LOW_RESERVE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ChangeTrustSelfNotAllowed : ChangeTrustResult(ChangeTrustResultCode.CHANGE_TRUST_SELF_NOT_ALLOWED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ChangeTrustTrustLineMissing : ChangeTrustResult(ChangeTrustResultCode.CHANGE_TRUST_TRUST_LINE_MISSING) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ChangeTrustCannotDelete : ChangeTrustResult(ChangeTrustResultCode.CHANGE_TRUST_CANNOT_DELETE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ChangeTrustNotAuthMaintainLiabilities : ChangeTrustResult(ChangeTrustResultCode.CHANGE_TRUST_NOT_AUTH_MAINTAIN_LIABILITIES) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    companion object : XdrElementDecoder<ChangeTrustResult> {
        override fun decode(stream: XdrInputStream): ChangeTrustResult {
            return when (val type = ChangeTrustResultCode.decode(stream)) {
                ChangeTrustResultCode.CHANGE_TRUST_SUCCESS -> ChangeTrustSuccess
                ChangeTrustResultCode.CHANGE_TRUST_MALFORMED -> ChangeTrustMalformed
                ChangeTrustResultCode.CHANGE_TRUST_NO_ISSUER -> ChangeTrustNoIssuer
                ChangeTrustResultCode.CHANGE_TRUST_INVALID_LIMIT -> ChangeTrustInvalidLimit
                ChangeTrustResultCode.CHANGE_TRUST_LOW_RESERVE -> ChangeTrustLowReserve
                ChangeTrustResultCode.CHANGE_TRUST_SELF_NOT_ALLOWED -> ChangeTrustSelfNotAllowed
                ChangeTrustResultCode.CHANGE_TRUST_TRUST_LINE_MISSING -> ChangeTrustTrustLineMissing
                ChangeTrustResultCode.CHANGE_TRUST_CANNOT_DELETE -> ChangeTrustCannotDelete
                ChangeTrustResultCode.CHANGE_TRUST_NOT_AUTH_MAINTAIN_LIABILITIES -> ChangeTrustNotAuthMaintainLiabilities
                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
