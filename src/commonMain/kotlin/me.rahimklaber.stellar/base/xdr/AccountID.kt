package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// typedef PublicKey AccountID;
///////////////////////////////////////////////////////////////////////////
// TODO: Think about whether I can and should replace this with a typealias.
data class AccountID(
    val publicKey: PublicKey
) : XdrElement{
    override fun encode(stream: XdrStream) {
        publicKey.encode(stream)
    }

    companion object : XdrElementDecoder<AccountID>{
        override fun decode(stream: XdrStream): AccountID {
            val publicKey = PublicKey.decode(stream)
            return AccountID(publicKey)
        }

    }

}