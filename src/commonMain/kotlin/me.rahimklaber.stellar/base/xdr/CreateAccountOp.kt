// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * CreateAccountOp's original definition in the XDR file is:
 * ```
 * struct CreateAccountOp
{
AccountID destination; // account to create
int64 startingBalance; // amount they end up with
};
 * ```
 */
data class CreateAccountOp(
    val destination: AccountID,
    val startingBalance: Int64,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        destination.encode(stream)
        startingBalance.encode(stream)
    }

    companion object : XdrElementDecoder<CreateAccountOp> {
        override fun decode(stream: XdrInputStream): CreateAccountOp {
            val destination = AccountID.decode(stream)
            val startingBalance = me.rahimklaber.stellar.base.xdr.Int64.decode(stream)
            return CreateAccountOp(
                destination,
                startingBalance,
            )
        }
    }
}
