package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct ClaimableBalanceEntryExtensionV1
//{
//    union switch (int v)
//    {
//    case 0:
//        void;
//    }
//    ext;
//
//    uint32 flags; // see ClaimableBalanceFlags
//};
//
//struct ClaimableBalanceEntry
//{
//    // Unique identifier for this ClaimableBalanceEntry
//    ClaimableBalanceID balanceID;
//
//    // List of claimants with associated predicate
//    Claimant claimants<10>;
//
//    // Any asset including native
//    Asset asset;
//
//    // Amount of asset
//    int64 amount;
//
//    // reserved for future use
//    union switch (int v)
//    {
//    case 0:
//        void;
//    case 1:
//        ClaimableBalanceEntryExtensionV1 v1;
//    }
//    ext;
//};
///////////////////////////////////////////////////////////////////////////
data class ClaimableBalanceEntry(
    val balanceID: ClaimableBalanceID,
    val claimants: List<Claimant>,
    val asset: Asset,
    val amount: Long,
    val discriminant: Int,
    val extensionV1: ClaimableBalanceEntryExtensionV1?
): XdrElement {
    override fun encode(stream: XdrStream) {
        balanceID.encode(stream)
        stream.writeInt(claimants.size)
        claimants.forEach{
            it.encode(stream)
        }
        asset.encode(stream)
        stream.writeLong(amount)
        stream.writeInt(discriminant)
        extensionV1?.encode(stream)

    }

    companion object: XdrElementDecoder<ClaimableBalanceEntry> {
        override fun decode(stream: XdrStream): ClaimableBalanceEntry {
            val balanceID = ClaimableBalanceID.decode(stream)
            val claimantsSize = stream.readInt()
            val claimants = mutableListOf<Claimant>()
            for (i in 0 until claimantsSize){
                claimants.add(Claimant.decode(stream))
            }
            val asset = Asset.decode(stream)
            val amount = stream.readLong()
            val discriminant = stream.readInt()
            val extensionV1 : ClaimableBalanceEntryExtensionV1? = when(discriminant){
                1 -> ClaimableBalanceEntryExtensionV1.decode(stream)
                else -> null
            }
            return ClaimableBalanceEntry(balanceID, claimants, asset, amount, discriminant, extensionV1)
        }
    }
}

data class ClaimableBalanceEntryExtensionV1(
    val discriminant: Int,
    val flags: UInt,
) : XdrElement{
    override fun encode(stream: XdrStream) {
        stream.writeInt(discriminant)
        stream.writeInt(flags.toInt())
    }

    companion object: XdrElementDecoder<ClaimableBalanceEntryExtensionV1>{
        override fun decode(stream: XdrStream): ClaimableBalanceEntryExtensionV1 {
            val discriminant = stream.readInt()
            val flags = stream.readInt().toUInt()
            return ClaimableBalanceEntryExtensionV1(discriminant,flags)
        }

    }

}