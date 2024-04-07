package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union OperationResult switch (OperationResultCode code)
//{
//case opINNER:
//    union switch (OperationType type)
//    {
//    case CREATE_ACCOUNT:
//        CreateAccountResult createAccountResult;
//    case PAYMENT:
//        PaymentResult paymentResult;
//    case PATH_PAYMENT_STRICT_RECEIVE:
//        PathPaymentStrictReceiveResult pathPaymentStrictReceiveResult;
//    case MANAGE_SELL_OFFER:
//        ManageSellOfferResult manageSellOfferResult;
//    case CREATE_PASSIVE_SELL_OFFER:
//        ManageSellOfferResult createPassiveSellOfferResult;
//    case SET_OPTIONS:
//        SetOptionsResult setOptionsResult;
//    case CHANGE_TRUST:
//        ChangeTrustResult changeTrustResult;
//    case ALLOW_TRUST:
//        AllowTrustResult allowTrustResult;
//    case ACCOUNT_MERGE:
//        AccountMergeResult accountMergeResult;
//    case INFLATION:
//        InflationResult inflationResult;
//    case MANAGE_DATA:
//        ManageDataResult manageDataResult;
//    case BUMP_SEQUENCE:
//        BumpSequenceResult bumpSeqResult;
//    case MANAGE_BUY_OFFER:
//        ManageBuyOfferResult manageBuyOfferResult;
//    case PATH_PAYMENT_STRICT_SEND:
//        PathPaymentStrictSendResult pathPaymentStrictSendResult;
//    case CREATE_CLAIMABLE_BALANCE:
//        CreateClaimableBalanceResult createClaimableBalanceResult;
//    case CLAIM_CLAIMABLE_BALANCE:
//        ClaimClaimableBalanceResult claimClaimableBalanceResult;
//    case BEGIN_SPONSORING_FUTURE_RESERVES:
//        BeginSponsoringFutureReservesResult beginSponsoringFutureReservesResult;
//    case END_SPONSORING_FUTURE_RESERVES:
//        EndSponsoringFutureReservesResult endSponsoringFutureReservesResult;
//    case REVOKE_SPONSORSHIP:
//        RevokeSponsorshipResult revokeSponsorshipResult;
//    case CLAWBACK:
//        ClawbackResult clawbackResult;
//    case CLAWBACK_CLAIMABLE_BALANCE:
//        ClawbackClaimableBalanceResult clawbackClaimableBalanceResult;
//    case SET_TRUST_LINE_FLAGS:
//        SetTrustLineFlagsResult setTrustLineFlagsResult;
//    case LIQUIDITY_POOL_DEPOSIT:
//        LiquidityPoolDepositResult liquidityPoolDepositResult;
//    case LIQUIDITY_POOL_WITHDRAW:
//        LiquidityPoolWithdrawResult liquidityPoolWithdrawResult;
//    case INVOKE_HOST_FUNCTION:
//        InvokeHostFunctionResult invokeHostFunctionResult;
//    case EXTEND_FOOTPRINT_TTL:
//        ExtendFootprintTTLResult extendFootprintTTLResult;
//    case RESTORE_FOOTPRINT:
//        RestoreFootprintResult restoreFootprintResult;
//    }
//    tr;
//case opBAD_AUTH:
//case opNO_ACCOUNT:
//case opNOT_SUPPORTED:
//case opTOO_MANY_SUBENTRIES:
//case opEXCEEDED_WORK_LIMIT:
//case opTOO_MANY_SPONSORING:
//    void;
//};
///////////////////////////////////////////////////////////////////////////
sealed class OperationResult(val code: OperationResultCode): XdrElement {
    override fun encode(stream: XdrStream) {
        code.encode(stream)
    }
    sealed class OpInner(val type: OperationType, code: OperationResultCode = OperationResultCode.opINNER): OperationResult(code){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            type.encode(stream)
        }
    }

    data class InvokeHostFunction(val invokeHostFunctionResult: InvokeHostFunctionResult): OpInner(OperationType.INVOKE_HOST_FUNCTION){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            invokeHostFunctionResult.encode(stream)
        }
    }

    companion object: XdrElementDecoder<OperationResult> {
        override fun decode(stream: XdrStream): OperationResult {
            val code = OperationResultCode.decode(stream)

            return when(code){
                OperationResultCode.opINNER -> {
                    val operationType = OperationType.decode(stream)

                    when(operationType){
                        OperationType.INVOKE_HOST_FUNCTION -> {
                            InvokeHostFunction(InvokeHostFunctionResult.decode(stream))
                        }
                        else -> TODO()
                    }
                }
                else -> TODO()
            }
        }
    }

}