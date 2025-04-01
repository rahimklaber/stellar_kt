// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * ManageOfferSuccessResult's original definition in the XDR file is:
 * ```
 * struct ManageOfferSuccessResult
{
// offers that got claimed while creating this offer
ClaimAtom offersClaimed<>;

union switch (ManageOfferEffect effect)
{
case MANAGE_OFFER_CREATED:
case MANAGE_OFFER_UPDATED:
OfferEntry offer;
case MANAGE_OFFER_DELETED:
void;
}
offer;
};
 * ```
 */
data class ManageOfferSuccessResult(
    val offersClaimed: List<ClaimAtom>,
    val offer: ManageOfferSuccessResultOffer,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        val offersClaimedSize = offersClaimed.size
        stream.writeInt(offersClaimedSize)
        offersClaimed.encodeXdrElements(stream)
        offer.encode(stream)
    }

    companion object : XdrElementDecoder<ManageOfferSuccessResult> {
        override fun decode(stream: XdrInputStream): ManageOfferSuccessResult {
            val offersClaimedSize = stream.readInt()
            val offersClaimed: List<ClaimAtom> = decodeXdrElementsList(offersClaimedSize, stream, ClaimAtom.decoder())
            val offer = ManageOfferSuccessResultOffer.decode(stream)
            return ManageOfferSuccessResult(
                offersClaimed,
                offer,
            )
        }
    }

    /**
     * ManageOfferSuccessResultOffer's original definition in the XDR file is:
     * ```
     * union switch (ManageOfferEffect effect)
    {
    case MANAGE_OFFER_CREATED:
    case MANAGE_OFFER_UPDATED:
    OfferEntry offer;
    case MANAGE_OFFER_DELETED:
    void;
    }
     * ```
     */
    sealed class ManageOfferSuccessResultOffer(val type: ManageOfferEffect) : XdrElement {
        data class ManageOfferCreated(
            val offer: OfferEntry,
        ) : ManageOfferSuccessResultOffer(ManageOfferEffect.MANAGE_OFFER_CREATED) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                offer.encode(stream)
            }
        }

        data class ManageOfferUpdated(
            val offer: OfferEntry,
        ) : ManageOfferSuccessResultOffer(ManageOfferEffect.MANAGE_OFFER_UPDATED) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
                offer.encode(stream)
            }
        }

        data object ManageOfferDeleted : ManageOfferSuccessResultOffer(ManageOfferEffect.MANAGE_OFFER_DELETED) {
            override fun encode(stream: XdrOutputStream) {
                type.encode(stream)
            }
        }

        companion object : XdrElementDecoder<ManageOfferSuccessResultOffer> {
            override fun decode(stream: XdrInputStream): ManageOfferSuccessResultOffer {
                return when (val type = ManageOfferEffect.decode(stream)) {
                    ManageOfferEffect.MANAGE_OFFER_CREATED -> {
                        val offer = OfferEntry.decode(stream)
                        ManageOfferCreated(offer)
                    }

                    ManageOfferEffect.MANAGE_OFFER_UPDATED -> {
                        val offer = OfferEntry.decode(stream)
                        ManageOfferUpdated(offer)
                    }

                    ManageOfferEffect.MANAGE_OFFER_DELETED -> ManageOfferDeleted
                    else -> throw IllegalArgumentException("unknown type: $type")
                }
            }
        }
    }
}
