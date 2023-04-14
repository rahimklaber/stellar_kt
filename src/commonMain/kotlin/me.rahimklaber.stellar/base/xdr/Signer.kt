package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct Signer
//{
//    SignerKey key;
//    uint32 weight; // really only need 1 byte
//};
///////////////////////////////////////////////////////////////////////////
data class Signer(
    val key: SignerKey,
    val weight: UInt
) : XdrElement{
    override fun encode(stream: XdrStream) {
        key.encode(stream)
        stream.writeInt(weight.toInt())
    }

    companion object: XdrElementDecoder<Signer>{
        override fun decode(stream: XdrStream): Signer {
            val key= SignerKey.decode(stream)
            val weight = stream.readInt().toUInt()
            return Signer(key,weight)
        }

    }

}