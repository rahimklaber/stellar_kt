// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * RevokeSponsorshipType's original definition in the XDR file is:
 * ```
 * enum RevokeSponsorshipType
{
REVOKE_SPONSORSHIP_LEDGER_ENTRY = 0,
REVOKE_SPONSORSHIP_SIGNER = 1
};
 * ```
 */
enum class RevokeSponsorshipType(val value: Int) : XdrElement {
    REVOKE_SPONSORSHIP_LEDGER_ENTRY(0),
    REVOKE_SPONSORSHIP_SIGNER(1);

    companion object : XdrElementDecoder<RevokeSponsorshipType> {
        override fun decode(stream: XdrInputStream): RevokeSponsorshipType {
            return when (val value = stream.readInt()) {
                0 -> REVOKE_SPONSORSHIP_LEDGER_ENTRY
                1 -> REVOKE_SPONSORSHIP_SIGNER
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
