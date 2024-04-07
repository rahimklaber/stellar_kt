package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* An operation is the lowest unit of work that a transaction does */
//struct Operation
//{
//    // sourceAccount is the account used to run the operation
//    // if not set, the runtime defaults to "sourceAccount" specified at
//    // the transaction level
//    MuxedAccount* sourceAccount;
//
//    union switch (OperationType type)
//    {
//    case CREATE_ACCOUNT:
//        CreateAccountOp createAccountOp;
//    case PAYMENT:
//        PaymentOp paymentOp;
//    case PATH_PAYMENT_STRICT_RECEIVE:
//        PathPaymentStrictReceiveOp pathPaymentStrictReceiveOp;
//    case MANAGE_SELL_OFFER:
//        ManageSellOfferOp manageSellOfferOp;
//    case CREATE_PASSIVE_SELL_OFFER:
//        CreatePassiveSellOfferOp createPassiveSellOfferOp;
//    case SET_OPTIONS:
//        SetOptionsOp setOptionsOp;
//    case CHANGE_TRUST:
//        ChangeTrustOp changeTrustOp;
//    case ALLOW_TRUST:
//        AllowTrustOp allowTrustOp;
//    case ACCOUNT_MERGE:
//        MuxedAccount destination;
//    case INFLATION:
//        void;
//    case MANAGE_DATA:
//        ManageDataOp manageDataOp;
//    case BUMP_SEQUENCE:
//        BumpSequenceOp bumpSequenceOp;
//    case MANAGE_BUY_OFFER:
//        ManageBuyOfferOp manageBuyOfferOp;
//    case PATH_PAYMENT_STRICT_SEND:
//        PathPaymentStrictSendOp pathPaymentStrictSendOp;
//    case CREATE_CLAIMABLE_BALANCE:
//        CreateClaimableBalanceOp createClaimableBalanceOp;
//    case CLAIM_CLAIMABLE_BALANCE:
//        ClaimClaimableBalanceOp claimClaimableBalanceOp;
//    case BEGIN_SPONSORING_FUTURE_RESERVES:
//        BeginSponsoringFutureReservesOp beginSponsoringFutureReservesOp;
//    case END_SPONSORING_FUTURE_RESERVES:
//        void;
//    case REVOKE_SPONSORSHIP:
//        RevokeSponsorshipOp revokeSponsorshipOp;
//    case CLAWBACK:
//        ClawbackOp clawbackOp;
//    case CLAWBACK_CLAIMABLE_BALANCE:
//        ClawbackClaimableBalanceOp clawbackClaimableBalanceOp;
//    case SET_TRUST_LINE_FLAGS:
//        SetTrustLineFlagsOp setTrustLineFlagsOp;
//    case LIQUIDITY_POOL_DEPOSIT:
//        LiquidityPoolDepositOp liquidityPoolDepositOp;
//    case LIQUIDITY_POOL_WITHDRAW:
//        LiquidityPoolWithdrawOp liquidityPoolWithdrawOp;
//    case INVOKE_HOST_FUNCTION:
//        InvokeHostFunctionOp invokeHostFunctionOp;
//    case EXTEND_FOOTPRINT_TTL:
//        ExtendFootprintTTLOp extendFootprintTTLOp;
//    case RESTORE_FOOTPRINT:
//        RestoreFootprintOp restoreFootprintOp;
//    }
//    body;
//};
///////////////////////////////////////////////////////////////////////////
sealed class Operation(val type: OperationType) : XdrElement {
    abstract val sourceAccount: MuxedAccount?

    override fun encode(stream: XdrStream) {
        sourceAccount.encodeNullable(stream)
        type.encode(stream)
    }

    companion object : XdrElementDecoder<Operation> {
        override fun decode(stream: XdrStream): Operation {
            val account = MuxedAccount.decodeNullable(stream)
            return when (val type = OperationType.decode(stream)) {
                OperationType.CREATE_ACCOUNT -> {
                    val createAccountOp = CreateAccountOp.decode(stream)
                    CreateAccount(account, createAccountOp)
                }

                OperationType.PAYMENT -> {
                    val paymentOp = PaymentOp.decode(stream)
                    Payment(account, paymentOp)
                }

                OperationType.PATH_PAYMENT_STRICT_RECEIVE -> {
                    val pathPaymentStrictReceiveOp = PathPaymentStrictReceiveOp.decode(stream)
                    PathPaymentStrictReceive(account, pathPaymentStrictReceiveOp)
                }

                OperationType.MANAGE_SELL_OFFER -> {
                    val manageSellOfferOp = ManageSellOfferOp.decode(stream)
                    ManageSellOffer(account, manageSellOfferOp)
                }

                OperationType.CREATE_PASSIVE_SELL_OFFER -> {
                    val createPassiveSellOfferOp = CreatePassiveSellOfferOp.decode(stream)
                    CreatePassiveSellOffer(account, createPassiveSellOfferOp)
                }

                OperationType.SET_OPTIONS -> {
                    val setOptionsOp = SetOptionsOp.decode(stream)
                    SetOptions(account, setOptionsOp)
                }

                OperationType.CHANGE_TRUST -> {
                    val changeTrustOp = ChangeTrustOp.decode(stream)
                    ChangeTrust(account, changeTrustOp)
                }

                OperationType.ALLOW_TRUST -> {
                    val allowTrustOp = AllowTrustOp.decode(stream)
                    AllowTrust(account, allowTrustOp)
                }

                OperationType.ACCOUNT_MERGE -> {
                    AccountMerge(account, MuxedAccount.decode(stream))
                }

                OperationType.INFLATION -> {
                    Inflation(account)
                }

                OperationType.MANAGE_DATA -> {
                    val manageDataOp = ManageDataOp.decode(stream)
                    ManageData(account, manageDataOp)
                }

                OperationType.BUMP_SEQUENCE -> {
                    val bumpSequenceOp = BumpSequenceOp.decode(stream)
                    BumpSequence(account, bumpSequenceOp)
                }

                OperationType.MANAGE_BUY_OFFER -> {
                    val manageBuyOfferOp = ManageBuyOfferOp.decode(stream)
                    ManageBuyOffer(account, manageBuyOfferOp)
                }

                OperationType.PATH_PAYMENT_STRICT_SEND -> {
                    val pathPaymentStrictSendOp = PathPaymentStrictSendOp.decode(stream)
                    PathPaymentStrictSend(account, pathPaymentStrictSendOp)
                }

                OperationType.CREATE_CLAIMABLE_BALANCE -> {
                    val createClaimableBalanceOp = CreateClaimableBalanceOp.decode(stream)
                    CreateClaimableBalance(account, createClaimableBalanceOp)
                }

                OperationType.CLAIM_CLAIMABLE_BALANCE -> {
                    val claimClaimableBalanceOp = ClaimClaimableBalanceOp.decode(stream)
                    ClaimClaimableBalance(account, claimClaimableBalanceOp)
                }

                OperationType.BEGIN_SPONSORING_FUTURE_RESERVES -> {
                    val beginSponsoringFutureReservesOp = BeginSponsoringFutureReservesOp.decode(stream)
                    BeginSponsoringFutureReserves(account, beginSponsoringFutureReservesOp)
                }

                OperationType.END_SPONSORING_FUTURE_RESERVES -> {
                    EndSponsoringFutureReserves(account)
                }

                OperationType.REVOKE_SPONSORSHIP -> {
                    val revokeSponsorshipOp = RevokeSponsorshipOp.decode(stream)
                    RevokeSponsorship(account, revokeSponsorshipOp)
                }

                OperationType.CLAWBACK -> {
                    val clawBackOp = ClawBackOp.decode(stream)
                    Clawback(account, clawBackOp)
                }

                OperationType.CLAWBACK_CLAIMABLE_BALANCE -> {
                    val clawBackClaimableBalanceOp = ClawbackClaimableBalanceOp.decode(stream)
                    ClawbackClaimableBalance(account, clawBackClaimableBalanceOp)
                }

                OperationType.SET_TRUST_LINE_FLAGS -> {
                    val setTrustLineFlagsOp = SetTrustLineFlagsOp.decode(stream)
                    SetTrustlineFlags(account, setTrustLineFlagsOp)
                }

                OperationType.LIQUIDITY_POOL_DEPOSIT -> {
                    val liquidityPoolDepositOp = LiquidityPoolDepositOp.decode(stream)
                    LiquidityPoolDeposit(account, liquidityPoolDepositOp)
                }

                OperationType.LIQUIDITY_POOL_WITHDRAW -> {
                    val liquidityPoolWithdrawOp = LiquidityPoolWithdrawOp.decode(stream)
                    LiquidityPoolWithdraw(account, liquidityPoolWithdrawOp)
                }

                OperationType.INVOKE_HOST_FUNCTION -> {
                    val invokeHostFunctionOp = InvokeHostFunctionOp.decode(stream)
                    InvokeHostFunction(account, invokeHostFunctionOp)
                }
                OperationType.EXTEND_FOOTPRINT_TTL -> {
                    val extendFootprintTTLOp = ExtendFootprintTTLOp.decode(stream)
                    ExtendFootPrintTTL(account, extendFootprintTTLOp)
                }
                OperationType.RESTORE_FOOTPRINT -> {
                    val restoreFootPrintOp = RestoreFootprintOp.decode(stream)
                    RestoreFootprint(account, restoreFootPrintOp)
                }
            }
        }
    }

    data class CreateAccount(override val sourceAccount: MuxedAccount?, val createAccountOp: CreateAccountOp) :
        Operation(OperationType.CREATE_ACCOUNT) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            createAccountOp.encode(stream)
        }
    }

    data class Payment(override val sourceAccount: MuxedAccount?, val paymentOp: PaymentOp) :
        Operation(OperationType.PAYMENT) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            paymentOp.encode(stream)
        }
    }

    data class PathPaymentStrictReceive(
        override val sourceAccount: MuxedAccount?, val pathPaymentStrictReceiveOp: PathPaymentStrictReceiveOp
    ) : Operation(OperationType.PATH_PAYMENT_STRICT_RECEIVE) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            pathPaymentStrictReceiveOp.encode(stream)
        }
    }

    data class ManageSellOffer(override val sourceAccount: MuxedAccount?, val manageSellOfferOp: ManageSellOfferOp) :
        Operation(OperationType.MANAGE_SELL_OFFER) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            manageSellOfferOp.encode(stream)
        }
    }

    data class CreatePassiveSellOffer(
        override val sourceAccount: MuxedAccount?, val createPassiveSellOfferOp: CreatePassiveSellOfferOp
    ) : Operation(OperationType.CREATE_PASSIVE_SELL_OFFER) {
        override fun encode(stream: XdrStream) {
            createPassiveSellOfferOp.encode(stream)
        }
    }

    data class SetOptions(override val sourceAccount: MuxedAccount?, val setOptionsOp: SetOptionsOp) :
        Operation(OperationType.SET_OPTIONS) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            setOptionsOp.encode(stream)
        }
    }

    data class ChangeTrust(override val sourceAccount: MuxedAccount?, val changeTrustOp: ChangeTrustOp) :
        Operation(OperationType.CHANGE_TRUST) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            changeTrustOp.encode(stream)
        }
    }

    data class AllowTrust(override val sourceAccount: MuxedAccount?, val allowTrustOp: AllowTrustOp) :
        Operation(OperationType.ALLOW_TRUST) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            allowTrustOp.encode(stream)
        }
    }

    data class AccountMerge(override val sourceAccount: MuxedAccount?, val destination: MuxedAccount) :
        Operation(OperationType.ACCOUNT_MERGE) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            destination.encode(stream)
        }
    }

    data class Inflation(override val sourceAccount: MuxedAccount?) : Operation(OperationType.INFLATION)
    data class ManageData(override val sourceAccount: MuxedAccount?, val manageDataOp: ManageDataOp) :
        Operation(OperationType.MANAGE_DATA) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            manageDataOp.encode(stream)
        }
    }

    data class BumpSequence(override val sourceAccount: MuxedAccount?, val bumpSequenceOp: BumpSequenceOp) :
        Operation(OperationType.BUMP_SEQUENCE) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            bumpSequenceOp.encode(stream)
        }
    }

    data class ManageBuyOffer(override val sourceAccount: MuxedAccount?, val manageBuyOfferOp: ManageBuyOfferOp) :
        Operation(OperationType.MANAGE_BUY_OFFER) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            manageBuyOfferOp.encode(stream)
        }
    }

    data class PathPaymentStrictSend(
        override val sourceAccount: MuxedAccount?, val pathPaymentStrictSendOp: PathPaymentStrictSendOp
    ) : Operation(OperationType.PATH_PAYMENT_STRICT_SEND) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            pathPaymentStrictSendOp.encode(stream)
        }
    }

    data class CreateClaimableBalance(
        override val sourceAccount: MuxedAccount?, val createClaimableBalanceOp: CreateClaimableBalanceOp
    ) : Operation(OperationType.CREATE_CLAIMABLE_BALANCE) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            createClaimableBalanceOp.encode(stream)
        }
    }

    data class ClaimClaimableBalance(
        override val sourceAccount: MuxedAccount?, val claimClaimableBalanceOp: ClaimClaimableBalanceOp
    ) : Operation(OperationType.CLAIM_CLAIMABLE_BALANCE) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            claimClaimableBalanceOp.encode(stream)
        }
    }

    data class BeginSponsoringFutureReserves(
        override val sourceAccount: MuxedAccount?, val beginSponsoringFutureReservesOp: BeginSponsoringFutureReservesOp
    ) : Operation(OperationType.BEGIN_SPONSORING_FUTURE_RESERVES) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            beginSponsoringFutureReservesOp.encode(stream)
        }
    }

    data class EndSponsoringFutureReserves(override val sourceAccount: MuxedAccount?) :
        Operation(OperationType.END_SPONSORING_FUTURE_RESERVES)

    data class RevokeSponsorship(
        override val sourceAccount: MuxedAccount?, val revokeSponsorshipOp: RevokeSponsorshipOp
    ) : Operation(OperationType.REVOKE_SPONSORSHIP) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            revokeSponsorshipOp.encode(stream)
        }
    }

    data class Clawback(override val sourceAccount: MuxedAccount?, val clawBackOp: ClawBackOp) :
        Operation(OperationType.CLAWBACK) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            clawBackOp.encode(stream)
        }
    }

    data class ClawbackClaimableBalance(
        override val sourceAccount: MuxedAccount?, val clawbackClaimableBalanceOp: ClawbackClaimableBalanceOp
    ) : Operation(OperationType.CLAWBACK_CLAIMABLE_BALANCE) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            clawbackClaimableBalanceOp.encode(stream)
        }
    }

    data class SetTrustlineFlags(
        override val sourceAccount: MuxedAccount?, val setTrustLineFlagsOp: SetTrustLineFlagsOp
    ) : Operation(OperationType.SET_TRUST_LINE_FLAGS) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            setTrustLineFlagsOp.encode(stream)
        }
    }

    data class LiquidityPoolDeposit(
        override val sourceAccount: MuxedAccount?, val liquidityPoolDepositOp: LiquidityPoolDepositOp
    ) : Operation(OperationType.LIQUIDITY_POOL_DEPOSIT) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            liquidityPoolDepositOp.encode(stream)
        }
    }

    data class LiquidityPoolWithdraw(
        override val sourceAccount: MuxedAccount?, val liquidityPoolWithdrawOp: LiquidityPoolWithdrawOp
    ) : Operation(OperationType.LIQUIDITY_POOL_WITHDRAW) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            liquidityPoolWithdrawOp.encode(stream)
        }
    }

    data class InvokeHostFunction(
        override val sourceAccount: MuxedAccount?,
        val invokeHostFunctionOp: InvokeHostFunctionOp
    ) : Operation(OperationType.INVOKE_HOST_FUNCTION) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            invokeHostFunctionOp.encode(stream)
        }
    }


    data class ExtendFootPrintTTL(
        override val sourceAccount: MuxedAccount?,
        val extendFootprintTTLOp: ExtendFootprintTTLOp,
    ) : Operation(OperationType.EXTEND_FOOTPRINT_TTL) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            extendFootprintTTLOp.encode(stream)
        }
    }

    data class RestoreFootprint(
        override val sourceAccount: MuxedAccount?,
        val restoreFootPrintOp: RestoreFootprintOp
    ) : Operation(OperationType.RESTORE_FOOTPRINT) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            restoreFootPrintOp.encode(stream)
        }
    }


}
