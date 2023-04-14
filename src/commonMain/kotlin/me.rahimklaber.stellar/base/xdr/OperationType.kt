package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum OperationType
//{
//    CREATE_ACCOUNT = 0,
//    PAYMENT = 1,
//    PATH_PAYMENT_STRICT_RECEIVE = 2,
//    MANAGE_SELL_OFFER = 3,
//    CREATE_PASSIVE_SELL_OFFER = 4,
//    SET_OPTIONS = 5,
//    CHANGE_TRUST = 6,
//    ALLOW_TRUST = 7,
//    ACCOUNT_MERGE = 8,
//    INFLATION = 9,
//    MANAGE_DATA = 10,
//    BUMP_SEQUENCE = 11,
//    MANAGE_BUY_OFFER = 12,
//    PATH_PAYMENT_STRICT_SEND = 13,
//    CREATE_CLAIMABLE_BALANCE = 14,
//    CLAIM_CLAIMABLE_BALANCE = 15,
//    BEGIN_SPONSORING_FUTURE_RESERVES = 16,
//    END_SPONSORING_FUTURE_RESERVES = 17,
//    REVOKE_SPONSORSHIP = 18,
//    CLAWBACK = 19,
//    CLAWBACK_CLAIMABLE_BALANCE = 20,
//    SET_TRUST_LINE_FLAGS = 21,
//    LIQUIDITY_POOL_DEPOSIT = 22,
//    LIQUIDITY_POOL_WITHDRAW = 23
//};
///////////////////////////////////////////////////////////////////////////
enum class OperationType(val value: Int): XdrElement {
    CREATE_ACCOUNT(0),
    PAYMENT(1),
    PATH_PAYMENT_STRICT_RECEIVE(2),
    MANAGE_SELL_OFFER(3),
    CREATE_PASSIVE_SELL_OFFER(4),
    SET_OPTIONS(5),
    CHANGE_TRUST(6),
    ALLOW_TRUST(7),
    ACCOUNT_MERGE(8),
    INFLATION(9),
    MANAGE_DATA(10),
    BUMP_SEQUENCE(11),
    MANAGE_BUY_OFFER(12),
    PATH_PAYMENT_STRICT_SEND(13),
    CREATE_CLAIMABLE_BALANCE(14),
    CLAIM_CLAIMABLE_BALANCE(15),
    BEGIN_SPONSORING_FUTURE_RESERVES(16),
    END_SPONSORING_FUTURE_RESERVES(17),
    REVOKE_SPONSORSHIP(18),
    CLAWBACK(19),
    CLAWBACK_CLAIMABLE_BALANCE(20),
    SET_TRUST_LINE_FLAGS(21),
    LIQUIDITY_POOL_DEPOSIT(22),
    LIQUIDITY_POOL_WITHDRAW(23);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<OperationType> {
        override fun decode(stream: XdrStream): OperationType {
            return when(val value = stream.readInt()){
                CREATE_ACCOUNT.value -> CREATE_ACCOUNT
                PAYMENT.value -> PAYMENT
                PATH_PAYMENT_STRICT_RECEIVE.value -> PATH_PAYMENT_STRICT_RECEIVE
                MANAGE_SELL_OFFER.value -> MANAGE_SELL_OFFER
                CREATE_PASSIVE_SELL_OFFER.value -> CREATE_PASSIVE_SELL_OFFER
                SET_OPTIONS.value -> SET_OPTIONS
                CHANGE_TRUST.value -> CHANGE_TRUST
                ALLOW_TRUST.value -> ALLOW_TRUST
                ACCOUNT_MERGE.value -> ACCOUNT_MERGE
                INFLATION.value -> INFLATION
                MANAGE_DATA.value -> MANAGE_DATA
                BUMP_SEQUENCE.value -> BUMP_SEQUENCE
                MANAGE_BUY_OFFER.value -> MANAGE_BUY_OFFER
                PATH_PAYMENT_STRICT_SEND.value -> PATH_PAYMENT_STRICT_SEND
                CREATE_CLAIMABLE_BALANCE.value -> CREATE_CLAIMABLE_BALANCE
                CLAIM_CLAIMABLE_BALANCE.value -> CLAIM_CLAIMABLE_BALANCE
                BEGIN_SPONSORING_FUTURE_RESERVES.value -> BEGIN_SPONSORING_FUTURE_RESERVES
                END_SPONSORING_FUTURE_RESERVES.value -> END_SPONSORING_FUTURE_RESERVES
                REVOKE_SPONSORSHIP.value -> REVOKE_SPONSORSHIP
                CLAWBACK.value -> CLAWBACK
                CLAWBACK_CLAIMABLE_BALANCE.value -> CLAWBACK_CLAIMABLE_BALANCE
                SET_TRUST_LINE_FLAGS.value -> SET_TRUST_LINE_FLAGS
                LIQUIDITY_POOL_DEPOSIT.value -> LIQUIDITY_POOL_DEPOSIT
                LIQUIDITY_POOL_WITHDRAW.value -> LIQUIDITY_POOL_WITHDRAW
                else -> throw IllegalArgumentException("Could not decode OperationType for value: $value")
            }
        }
    }

}

