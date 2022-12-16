package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum AccountFlags
//{ // masks for each flag
//
//    // Flags set on issuer accounts
//    // TrustLines are created with authorized set to "false" requiring
//    // the issuer to set it for each TrustLine
//    AUTH_REQUIRED_FLAG = 0x1,
//    // If set, the authorized flag in TrustLines can be cleared
//    // otherwise, authorization cannot be revoked
//    AUTH_REVOCABLE_FLAG = 0x2,
//    // Once set, causes all AUTH_* flags to be read-only
//    AUTH_IMMUTABLE_FLAG = 0x4,
//    // Trustlines are created with clawback enabled set to "true",
//    // and claimable balances created from those trustlines are created
//    // with clawback enabled set to "true"
//    AUTH_CLAWBACK_ENABLED_FLAG = 0x8
//};
///////////////////////////////////////////////////////////////////////////
enum class AccountFlags(val value: Int) : XdrElement {
    AUTH_REQUIRED_FLAG(0x1),
    AUTH_REVOCABLE_FLAG(0x2),
    AUTH_IMMUTABLE_FLAG(0x4),
    AUTH_CLAWBACK_ENABLED_FLAG(0x8);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object : XdrElementDecoder<AccountFlags> {
        override fun decode(stream: XdrStream): AccountFlags {
            // values are always multiples of 4, or padded otherwise
            // so we are reading "int" but it is actually a padded byte

            return when (val value = stream.readInt()) {
                AUTH_REQUIRED_FLAG.value -> AUTH_REQUIRED_FLAG
                AUTH_REVOCABLE_FLAG.value -> AUTH_REVOCABLE_FLAG
                AUTH_IMMUTABLE_FLAG.value -> AUTH_IMMUTABLE_FLAG
                AUTH_CLAWBACK_ENABLED_FLAG.value -> AUTH_CLAWBACK_ENABLED_FLAG
                else -> throw IllegalArgumentException("cannot decode AccountFlag for input $value")
            }
        }

    }
}