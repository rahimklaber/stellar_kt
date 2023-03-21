package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// 
///////////////////////////////////////////////////////////////////////////
data class SponsorshipDescriptor(
    val accountID: AccountID?
) : XdrElement{
    override fun encode(stream: XdrStream) {
        if(accountID != null){
            stream.writeInt(1)
            accountID.encode(stream)
        }else{
            stream.writeInt(0)
        }
    }

    companion object: XdrElementDecoder<SponsorshipDescriptor>{
        override fun decode(stream: XdrStream): SponsorshipDescriptor {
            val discriminator = stream.readInt()
            val accountID =  if (discriminator == 1)
                AccountID.decode(stream)
            else
                null
            return SponsorshipDescriptor(accountID)
        }

    }

}