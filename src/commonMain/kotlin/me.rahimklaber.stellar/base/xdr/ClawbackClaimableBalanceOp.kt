package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* Claws back a claimable balance
//    Threshold: med
//    Result: ClawbackClaimableBalanceResult
//*/
//struct ClawbackClaimableBalanceOp
//{
//    ClaimableBalanceID balanceID;
//};
///////////////////////////////////////////////////////////////////////////
data class ClawbackClaimableBalanceOp(
    val balanceID: ClaimableBalanceID
): XdrElement {
    override fun encode(stream: XdrStream) {
        balanceID.encode(stream)
    }

     companion object: XdrElementDecoder<ClawbackClaimableBalanceOp> {
         override fun decode(stream: XdrStream): ClawbackClaimableBalanceOp {
             return ClawbackClaimableBalanceOp(ClaimableBalanceID.decode(stream))
         }
     }
}
