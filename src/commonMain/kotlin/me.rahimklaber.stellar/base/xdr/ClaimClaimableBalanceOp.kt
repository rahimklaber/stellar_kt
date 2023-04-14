package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Claims a claimable balance entry
//    Threshold: low
//    Result: ClaimClaimableBalanceResult
//*/
//struct ClaimClaimableBalanceOp
//{
//    ClaimableBalanceID balanceID;
//};
///////////////////////////////////////////////////////////////////////////
data class ClaimClaimableBalanceOp(
    val balanceID: ClaimableBalanceID,
): XdrElement {
    override fun encode(stream: XdrStream) {
        balanceID.encode(stream)
    }

    companion object: XdrElementDecoder<ClaimClaimableBalanceOp> {
        override fun decode(stream: XdrStream): ClaimClaimableBalanceOp {
            val balanceID = ClaimableBalanceID.decode(stream)
            return ClaimClaimableBalanceOp(balanceID)
        }
    }
}