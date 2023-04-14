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
//
//union RevokeSponsorshipOp switch (RevokeSponsorshipType type)
//{
//case REVOKE_SPONSORSHIP_LEDGER_ENTRY:
//    LedgerKey ledgerKey;
//case REVOKE_SPONSORSHIP_SIGNER:
//    struct
//    {
//        AccountID accountID;
//        SignerKey signerKey;
//    } signer;
//};
///////////////////////////////////////////////////////////////////////////
sealed class RevokeSponsorshipOp(val type: RevokeSponsorshipType) : XdrElement {
    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    data class RevokeSponsorshipOpLedgerEntry(val ledgerKey: LedgerKey) :
        RevokeSponsorshipOp(RevokeSponsorshipType.REVOKE_SPONSORSHIP_LEDGER_ENTRY) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            ledgerKey.encode(stream)
        }
    }

    data class RevokeSponsorshipOpSigner(val accountID: AccountID, val signerKey: SignerKey) :
        RevokeSponsorshipOp(RevokeSponsorshipType.REVOKE_SPONSORSHIP_LEDGER_ENTRY) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            accountID.encode(stream)
            signerKey.encode(stream)
        }
    }

    companion object : XdrElementDecoder<RevokeSponsorshipOp> {
        override fun decode(stream: XdrStream): RevokeSponsorshipOp {
            return when (val type = RevokeSponsorshipType.decode(stream)) {
                RevokeSponsorshipType.REVOKE_SPONSORSHIP_LEDGER_ENTRY -> {
                    RevokeSponsorshipOpLedgerEntry(LedgerKey.decode(stream))
                }

                RevokeSponsorshipType.REVOKE_SPONSORSHIP_SIGNER -> {
                    val accountID = AccountID.decode(stream)
                    val signerKey = SignerKey.decode(stream)
                    return RevokeSponsorshipOpSigner(accountID, signerKey)
                }
            }
        }
    }
}
