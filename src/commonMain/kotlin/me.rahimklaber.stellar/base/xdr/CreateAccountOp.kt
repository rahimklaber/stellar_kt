package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* CreateAccount
//Creates and funds a new account with the specified starting balance.
//Threshold: med
//Result: CreateAccountResult
//*/
//struct CreateAccountOp
//{
//    AccountID destination; // account to create
//    int64 startingBalance; // amount they end up with
//};
///////////////////////////////////////////////////////////////////////////
data class CreateAccountOp(
    val destination: AccountID,
    val startingBalance: Long
) : XdrElement{
    override fun encode(stream: XdrStream) {
        destination.encode(stream)
        stream.writeLong(startingBalance)
    }

    companion object: XdrElementDecoder<CreateAccountOp> {
        override fun decode(stream: XdrStream): CreateAccountOp {
            return CreateAccountOp(AccountID.decode(stream), stream.readLong())
        }
    }
}
