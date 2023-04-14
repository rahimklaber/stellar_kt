package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct SetOptionsOp
//{
//    AccountID* inflationDest; // sets the inflation destination
//
//    uint32* clearFlags; // which flags to clear
//    uint32* setFlags;   // which flags to set
//
//    // account threshold manipulation
//    uint32* masterWeight; // weight of the master account
//    uint32* lowThreshold;
//    uint32* medThreshold;
//    uint32* highThreshold;
//
//    string32* homeDomain; // sets the home domain
//
//    // Add, update or remove a signer for the account
//    // signer is deleted if the weight is 0
//    Signer* signer;
//};
///////////////////////////////////////////////////////////////////////////
data class SetOptionsOp(
    val inflationDest: AccountID?,
    val clearFlags: UInt?,
    val setFlags: UInt?,
    val masterWeight: UInt?,
    val lowThreshold: UInt?,
    val medThreshold: UInt?,
    val highThreshold: UInt?,
    val homeDomain: String32?,
    val signer: Signer?
) : XdrElement {
    override fun encode(stream: XdrStream) {
        inflationDest?.encode(stream)
        if (clearFlags != null){
            stream.writeInt(1)
            stream.writeInt(clearFlags.toInt())
        }else{
            stream.writeInt(0)
        }
        if(setFlags != null){
            stream.writeInt(1)
            stream.writeInt(setFlags.toInt())
        }else{
            stream.writeInt(0)
        }
        if (masterWeight != null){
            stream.writeInt(1)
            stream.writeInt(masterWeight.toInt())
        }else{
            stream.writeInt(0)
        }
        if (lowThreshold != null){
            stream.writeInt(1)
            stream.writeInt(lowThreshold.toInt())
        }else{
            stream.writeInt(0)
        }
        if (medThreshold != null){
            stream.writeInt(1)
            stream.writeInt(medThreshold.toInt())
        }else{
            stream.writeInt(0)
        }
        if (highThreshold != null){
            stream.writeInt(1)
            stream.writeInt(highThreshold.toInt())
        }else{
            stream.writeInt(0)
        }
        if (homeDomain != null){
            stream.writeInt(1)
            homeDomain.encode(stream)
        }else{
            stream.writeInt(0)
        }
        if (signer != null){
            stream.writeInt(1)
            signer.encode(stream)
        }else{
            stream.writeInt(0)
        }
    }

    companion object: XdrElementDecoder<SetOptionsOp> {
        override fun decode(stream: XdrStream): SetOptionsOp {
            val inflationDest = AccountID.decodeNullable(stream)

            val clearFlags = stream.readIntNullable()?.toUInt()
            val setFlags = stream.readIntNullable()?.toUInt()

            val masterWeight = stream.readIntNullable()?.toUInt()

            val lowThreshold = stream.readIntNullable()?.toUInt()
            val medThreshold = stream.readIntNullable()?.toUInt()
            val highThreshold = stream.readIntNullable()?.toUInt()

            val homeDomain = String32.decodeNullable(stream)
            val signer = Signer.decodeNullable(stream)

            return SetOptionsOp(inflationDest, clearFlags, setFlags, masterWeight, lowThreshold, medThreshold, highThreshold, homeDomain, signer)
        }
    }
}
