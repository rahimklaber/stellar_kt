package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* RevokeSponsorship
//    If source account is not sponsored or is sponsored by the owner of the
//    specified entry or sub-entry, then attempt to revoke the sponsorship.
//    If source account is sponsored, then attempt to transfer the sponsorship
//    to the sponsor of source account.
//    Threshold: med
//    Result: RevokeSponsorshipResult
//*/
//enum RevokeSponsorshipType
//{
//    REVOKE_SPONSORSHIP_LEDGER_ENTRY = 0,
//    REVOKE_SPONSORSHIP_SIGNER = 1
//};
///////////////////////////////////////////////////////////////////////////
enum class RevokeSponsorshipType(val value: Int): XdrElement {
    REVOKE_SPONSORSHIP_LEDGER_ENTRY( 0),
    REVOKE_SPONSORSHIP_SIGNER( 1);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<RevokeSponsorshipType> {
        override fun decode(stream: XdrStream): RevokeSponsorshipType {
            return when(val value = stream.readInt()){
                REVOKE_SPONSORSHIP_LEDGER_ENTRY.value ->  REVOKE_SPONSORSHIP_LEDGER_ENTRY
                REVOKE_SPONSORSHIP_SIGNER.value -> REVOKE_SPONSORSHIP_SIGNER
                else -> throw IllegalArgumentException("Could not decode RevokeSponsorshipType for value: $value")
            }
        }
    }
}