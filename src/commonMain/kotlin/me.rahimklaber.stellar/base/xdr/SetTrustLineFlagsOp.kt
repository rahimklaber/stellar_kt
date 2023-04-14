package me.rahimklaber.stellar.base.xdr

import me.rahimklaber.stellar.horizon.operations.SetTrustLineFlags

///////////////////////////////////////////////////////////////////////////
// /* SetTrustLineFlagsOp
//   Updates the flags of an existing trust line.
//   This is called by the issuer of the related asset.
//   Threshold: low
//   Result: SetTrustLineFlagsResult
//*/
//struct SetTrustLineFlagsOp
//{
//    AccountID trustor;
//    Asset asset;
//
//    uint32 clearFlags; // which flags to clear
//    uint32 setFlags;   // which flags to set
//};
///////////////////////////////////////////////////////////////////////////
data class SetTrustLineFlagsOp(
    val trustor: AccountID,
    val asset: Asset,
    val clearFlags: UInt,
    val setFlags: UInt,
): XdrElement {
    override fun encode(stream: XdrStream) {
        trustor.encode(stream)
        asset.encode(stream)
        stream.writeInt(clearFlags.toInt())
        stream.writeInt(setFlags.toInt())
    }

    companion object: XdrElementDecoder<SetTrustLineFlagsOp> {
        override fun decode(stream: XdrStream): SetTrustLineFlagsOp {
            val trustor = AccountID.decode(stream)
            val asset = Asset.decode(stream)
            val clearFlags = stream.readInt().toUInt()
            val setFlags = stream.readInt().toUInt()
            return SetTrustLineFlagsOp(trustor, asset, clearFlags, setFlags)
        }
    }
}
