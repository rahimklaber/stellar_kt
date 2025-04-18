// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ManageSellOfferResult's original definition in the XDR file is:
 * ```
 * union ManageSellOfferResult switch (ManageSellOfferResultCode code)
{
case MANAGE_SELL_OFFER_SUCCESS:
ManageOfferSuccessResult success;
case MANAGE_SELL_OFFER_MALFORMED:
case MANAGE_SELL_OFFER_SELL_NO_TRUST:
case MANAGE_SELL_OFFER_BUY_NO_TRUST:
case MANAGE_SELL_OFFER_SELL_NOT_AUTHORIZED:
case MANAGE_SELL_OFFER_BUY_NOT_AUTHORIZED:
case MANAGE_SELL_OFFER_LINE_FULL:
case MANAGE_SELL_OFFER_UNDERFUNDED:
case MANAGE_SELL_OFFER_CROSS_SELF:
case MANAGE_SELL_OFFER_SELL_NO_ISSUER:
case MANAGE_SELL_OFFER_BUY_NO_ISSUER:
case MANAGE_SELL_OFFER_NOT_FOUND:
case MANAGE_SELL_OFFER_LOW_RESERVE:
void;
};
 * ```
 */
sealed class ManageSellOfferResult(val type: ManageSellOfferResultCode) : XdrElement {
    fun successOrNull(): ManageSellOfferSuccess? = if (this is ManageSellOfferSuccess) this else null
    data class ManageSellOfferSuccess(
        val success: ManageOfferSuccessResult,
    ) : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_SUCCESS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            success.encode(stream)
        }
    }

    data object ManageSellOfferMalformed : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_MALFORMED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ManageSellOfferSellNoTrust : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_SELL_NO_TRUST) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ManageSellOfferBuyNoTrust : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_BUY_NO_TRUST) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ManageSellOfferSellNotAuthorized : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_SELL_NOT_AUTHORIZED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ManageSellOfferBuyNotAuthorized : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_BUY_NOT_AUTHORIZED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ManageSellOfferLineFull : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_LINE_FULL) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ManageSellOfferUnderfunded : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_UNDERFUNDED) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ManageSellOfferCrossSelf : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_CROSS_SELF) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ManageSellOfferSellNoIssuer : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_SELL_NO_ISSUER) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ManageSellOfferBuyNoIssuer : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_BUY_NO_ISSUER) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ManageSellOfferNotFound : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_NOT_FOUND) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    data object ManageSellOfferLowReserve : ManageSellOfferResult(ManageSellOfferResultCode.MANAGE_SELL_OFFER_LOW_RESERVE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    companion object : XdrElementDecoder<ManageSellOfferResult> {
        override fun decode(stream: XdrInputStream): ManageSellOfferResult {
            return when (val type = ManageSellOfferResultCode.decode(stream)) {
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_SUCCESS -> {
                    val success = ManageOfferSuccessResult.decode(stream)
                    ManageSellOfferSuccess(success)
                }

                ManageSellOfferResultCode.MANAGE_SELL_OFFER_MALFORMED -> ManageSellOfferMalformed
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_SELL_NO_TRUST -> ManageSellOfferSellNoTrust
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_BUY_NO_TRUST -> ManageSellOfferBuyNoTrust
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_SELL_NOT_AUTHORIZED -> ManageSellOfferSellNotAuthorized
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_BUY_NOT_AUTHORIZED -> ManageSellOfferBuyNotAuthorized
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_LINE_FULL -> ManageSellOfferLineFull
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_UNDERFUNDED -> ManageSellOfferUnderfunded
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_CROSS_SELF -> ManageSellOfferCrossSelf
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_SELL_NO_ISSUER -> ManageSellOfferSellNoIssuer
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_BUY_NO_ISSUER -> ManageSellOfferBuyNoIssuer
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_NOT_FOUND -> ManageSellOfferNotFound
                ManageSellOfferResultCode.MANAGE_SELL_OFFER_LOW_RESERVE -> ManageSellOfferLowReserve
                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
