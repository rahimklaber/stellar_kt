package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* TrustLineEntry
//    A trust line represents a specific trust relationship with
//    a credit/issuer (limit, authorization)
//    as well as the balance.
//*/
//
//enum TrustLineFlags
//{
//    // issuer has authorized account to perform transactions with its credit
//    AUTHORIZED_FLAG = 1,
//    // issuer has authorized account to maintain and reduce liabilities for its
//    // credit
//    AUTHORIZED_TO_MAINTAIN_LIABILITIES_FLAG = 2,
//    // issuer has specified that it may clawback its credit, and that claimable
//    // balances created with its credit may also be clawed back
//    TRUSTLINE_CLAWBACK_ENABLED_FLAG = 4
//};
///////////////////////////////////////////////////////////////////////////
enum class TrustLineFlags(val value: Int) : XdrElement {
    AUTHORIZED_FLAG(1),
    AUTHORIZED_TO_MAINTAIN_LIABILITIES_FLAG(2),
    TRUSTLINE_CLAWBACK_ENABLED_FLAG(4);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<TrustLineFlags>{
        override fun decode(stream: XdrStream): TrustLineFlags {
            return when(val value = stream.readInt()){
                AUTHORIZED_FLAG.value -> AUTHORIZED_FLAG
                AUTHORIZED_TO_MAINTAIN_LIABILITIES_FLAG.value -> AUTHORIZED_TO_MAINTAIN_LIABILITIES_FLAG
                TRUSTLINE_CLAWBACK_ENABLED_FLAG.value -> TRUSTLINE_CLAWBACK_ENABLED_FLAG
                else -> throw IllegalArgumentException("Could not decode TruslineFlags for value: $value")
            }
        }

    }
}