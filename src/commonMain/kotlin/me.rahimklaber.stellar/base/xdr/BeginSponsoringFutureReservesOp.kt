package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* BeginSponsoringFutureReserves
//    Establishes the is-sponsoring-future-reserves-for relationship between
//    the source account and sponsoredID
//    Threshold: med
//    Result: BeginSponsoringFutureReservesResult
//*/
//struct BeginSponsoringFutureReservesOp
//{
//    AccountID sponsoredID;
//};
///////////////////////////////////////////////////////////////////////////
data class BeginSponsoringFutureReservesOp(
    val sponsoredID: AccountID,
): XdrElement {
    override fun encode(stream: XdrStream) {
        sponsoredID.encode(stream)
    }

    companion object: XdrElementDecoder<BeginSponsoringFutureReservesOp> {
        override fun decode(stream: XdrStream): BeginSponsoringFutureReservesOp {
            val sponsoredID = AccountID.decode(stream)
            return BeginSponsoringFutureReservesOp(sponsoredID)
        }
    }
}