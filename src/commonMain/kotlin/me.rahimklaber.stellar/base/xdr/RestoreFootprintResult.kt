// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * RestoreFootprintResult's original definition in the XDR file is:
 * ```
 * union RestoreFootprintResult switch (RestoreFootprintResultCode code)
{
case RESTORE_FOOTPRINT_SUCCESS:
void;
case RESTORE_FOOTPRINT_MALFORMED:
case RESTORE_FOOTPRINT_RESOURCE_LIMIT_EXCEEDED:
case RESTORE_FOOTPRINT_INSUFFICIENT_REFUNDABLE_FEE:
void;
};
 * ```
 */
sealed class RestoreFootprintResult(val type: RestoreFootprintResultCode) : XdrElement {
    data object RestoreFootprintSuccess : RestoreFootprintResult(RestoreFootprintResultCode.RESTORE_FOOTPRINT_SUCCESS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object RestoreFootprintMalformed : RestoreFootprintResult(RestoreFootprintResultCode.RESTORE_FOOTPRINT_MALFORMED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object RestoreFootprintResourceLimitExceeded : RestoreFootprintResult(RestoreFootprintResultCode.RESTORE_FOOTPRINT_RESOURCE_LIMIT_EXCEEDED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object RestoreFootprintInsufficientRefundableFee :
        RestoreFootprintResult(RestoreFootprintResultCode.RESTORE_FOOTPRINT_INSUFFICIENT_REFUNDABLE_FEE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    companion object : XdrElementDecoder<RestoreFootprintResult> {
        override fun decode(stream: XdrInputStream): RestoreFootprintResult {
            return when (val type = RestoreFootprintResultCode.decode(stream)) {
                RestoreFootprintResultCode.RESTORE_FOOTPRINT_SUCCESS -> RestoreFootprintSuccess
                RestoreFootprintResultCode.RESTORE_FOOTPRINT_MALFORMED -> RestoreFootprintMalformed
                RestoreFootprintResultCode.RESTORE_FOOTPRINT_RESOURCE_LIMIT_EXCEEDED -> RestoreFootprintResourceLimitExceeded
                RestoreFootprintResultCode.RESTORE_FOOTPRINT_INSUFFICIENT_REFUNDABLE_FEE -> RestoreFootprintInsufficientRefundableFee
                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
