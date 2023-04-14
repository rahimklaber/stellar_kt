package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// // Source or destination of a payment operation
//union MuxedAccount switch (CryptoKeyType type)
//{
//case KEY_TYPE_ED25519:
//    uint256 ed25519;
//case KEY_TYPE_MUXED_ED25519:
//    struct
//    {
//        uint64 id;
//        uint256 ed25519;
//    } med25519;
//};
///////////////////////////////////////////////////////////////////////////
sealed class MuxedAccount(val type: CryptoKeyType): XdrElement{
    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    data class Ed25519(val ed25519: Uint256): MuxedAccount(CryptoKeyType.KEY_TYPE_ED25519){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            ed25519.encode(stream)
        }
    }
    data class MuxedEd25519(val id: ULong, val ed25519: Uint256): MuxedAccount(CryptoKeyType.KEY_TYPE_MUXED_ED25519){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeLong(id.toLong())
            ed25519.encode(stream)
        }
    }


    companion object: XdrElementDecoder<MuxedAccount> {
        override fun decode(stream: XdrStream): MuxedAccount {
            return when(val type = CryptoKeyType.decode(stream)){
                CryptoKeyType.KEY_TYPE_ED25519 -> {
                    Ed25519(Uint256.decode(stream))
                }
                CryptoKeyType.KEY_TYPE_MUXED_ED25519 -> {
                    MuxedEd25519(stream.readLong().toULong(),Uint256.decode(stream))
                }
                else -> throw IllegalArgumentException("Could not decode MuxedAccount for type: $type")
            }
        }
    }
}

