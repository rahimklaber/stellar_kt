package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// struct AccountEntryExtensionV3
//{
//    // We can use this to add more fields, or because it is first, to
//    // change AccountEntryExtensionV3 into a union.
//    ExtensionPoint ext;
//
//    // Ledger number at which `seqNum` took on its present value.
//    uint32 seqLedger;
//
//    // Time at which `seqNum` took on its present value.
//    TimePoint seqTime;
//};
//
//struct AccountEntryExtensionV2
//{
//    uint32 numSponsored;
//    uint32 numSponsoring;
//    SponsorshipDescriptor signerSponsoringIDs<MAX_SIGNERS>;
//
//    union switch (int v)
//    {
//    case 0:
//        void;
//    case 3:
//        AccountEntryExtensionV3 v3;
//    }
//    ext;
//};
//
//struct AccountEntryExtensionV1
//{
//    Liabilities liabilities;
//
//    union switch (int v)
//    {
//    case 0:
//        void;
//    case 2:
//        AccountEntryExtensionV2 v2;
//    }
//    ext;
//};
//
///* AccountEntry
//    Main entry representing a user in Stellar. All transactions are
//    performed using an account.
//    Other ledger entries created require an account.
//*/
//struct AccountEntry
//{
//    AccountID accountID;      // master public key for this account
//    int64 balance;            // in stroops
//    SequenceNumber seqNum;    // last sequence number used for this account
//    uint32 numSubEntries;     // number of sub-entries this account has
//                              // drives the reserve
//    AccountID* inflationDest; // Account to vote for during inflation
//    uint32 flags;             // see AccountFlags
//
//    string32 homeDomain; // can be used for reverse federation and memo lookup
//
//    // fields used for signatures
//    // thresholds stores unsigned bytes: [weight of master|low|medium|high]
//    Thresholds thresholds;
//
//    Signer signers<MAX_SIGNERS>; // possible signers for this account
//
//    // reserved for future use
//    union switch (int v)
//    {
//    case 0:
//        void;
//    case 1:
//        AccountEntryExtensionV1 v1;
//    }
//    ext;
//};
///////////////////////////////////////////////////////////////////////////

data class AccountEntry(
    val accountID: AccountID,
    val balance: Long,
    val sequenceNumber: SequenceNumber,
    val numSubEntries: UInt,
    val inflationDest: AccountID?,
    val flags: UInt,
    val homeDomain: String32,
    val thresholds: Thresholds,
    val signers: List<Signer>,
    val discriminant: Int,
    val accountEntryExtensionV1: AccountEntryExtensionV1?
) : XdrElement{
    //todo add getters for the AccountEntryV1
    //val liabilities: Liabilities
    //  get() = accountEntryExtensionV1.liabilities
    override fun encode(stream: XdrStream) {
        accountID.encode(stream)
        stream.writeLong(balance)
        stream.writeLong(sequenceNumber)
        stream.writeInt(numSubEntries.toInt())
        inflationDest.encodeNullable(stream)
        stream.writeInt(flags.toInt())
        homeDomain.encode(stream)
        thresholds.encode(stream)
        stream.writeInt(signers.size)
        signers.forEach { it.encode(stream) }
        stream.writeInt(discriminant)
        accountEntryExtensionV1?.encode(stream)
    }
    companion object : XdrElementDecoder<AccountEntry>{
        override fun decode(stream: XdrStream): AccountEntry {
            val accountID = AccountID.decode(stream)
            val balance = stream.readLong()
            val sequence = stream.readLong()
            val numSubEntries = stream.readInt().toUInt()
            val inflationDest = run{
                val discrimator = stream.readInt()
                if (discrimator == 0){
                    null
                }else {
                    AccountID.decode(stream)
                }
            }
            val flags = stream.readInt().toUInt()
            val homeDomain = String32.decode(stream)
            val thesholds = Thresholds.decode(stream)
            val signer_size = stream.readInt()
            val signers = mutableListOf<Signer>().also {
                for(i in 0 until signer_size){
                    it.add(Signer.decode(stream))
                }
            }
            val discriminant = stream.readInt()
            val ext = if (discriminant == 0){
                null
            }else{
                AccountEntryExtensionV1.decode(stream)
            }
            return AccountEntry(
                accountID,
                balance,
                sequence,
                numSubEntries,
                inflationDest,
                flags,
                homeDomain,
                thesholds,
                signers,
                discriminant,
                ext
            )
        }

    }
}

//todo add checks for that when discrimant == 0 that the ext is null
// or don't use discrimant and rely on whether ext is null or not
data class AccountEntryExtensionV1(
    val liabilities: Liabilities,
    val discriminant: Int,
    val accountEntryExtensionV2: AccountEntryExtensionV2?
) : XdrElement{
    override fun encode(stream: XdrStream) {
        liabilities.encode(stream)
        stream.writeInt(discriminant)
        accountEntryExtensionV2?.encode(stream)
    }

    companion object : XdrElementDecoder<AccountEntryExtensionV1>{
        override fun decode(stream: XdrStream): AccountEntryExtensionV1 {
            val liabilities = Liabilities.decode(stream)
            val discriminant = stream.readInt()
            val accountEntryExtensionV2 =  if(discriminant == 0){
                null
            }else{
                AccountEntryExtensionV2.decode(stream)
            }
            return AccountEntryExtensionV1(liabilities,discriminant,accountEntryExtensionV2)
        }

    }

}

data class AccountEntryExtensionV2(
    val numSponsored: UInt,
    val numSponsoring: UInt,
    val signerSponsoringIds: List<SponsorshipDescriptor>,
    val discriminant: Int,
    val accountEntryExtensionV3: AccountEntryExtensionV3?
) : XdrElement{
    init {
        require(signerSponsoringIds.size <= MAX_SIGNERS){
            "amount of sponsoringIds must be smaller than the max amount of singers"
        }
    }

    override fun encode(stream: XdrStream) {
        stream.writeInt(numSponsored.toInt())
        stream.writeInt(numSponsoring.toInt())
        stream.writeInt(signerSponsoringIds.size)
        signerSponsoringIds.forEach {
            it.encode(stream)
        }
        stream.writeInt(discriminant)
        accountEntryExtensionV3?.encode(stream)
    }

    companion object : XdrElementDecoder<AccountEntryExtensionV2>{
        override fun decode(stream: XdrStream): AccountEntryExtensionV2 {
            val numSponsored = stream.readInt().toUInt()
            val numSponsoring = stream.readInt().toUInt()

            val signerSponsoringIdsSize = stream.readInt()
            val signerSponsoringIds = mutableListOf<SponsorshipDescriptor>()

            for (i in 0..< signerSponsoringIdsSize){
                signerSponsoringIds.add(SponsorshipDescriptor.decode(stream))
            }

            val discriminant = stream.readInt()
            val accountEntryExtensionV3 =  if (discriminant == 0)
                null
            else
                AccountEntryExtensionV3.decode(stream)

            return AccountEntryExtensionV2(numSponsored,numSponsoring,signerSponsoringIds,discriminant,accountEntryExtensionV3)
        }

    }
}

data class AccountEntryExtensionV3(
    val extensionPoint: Int,
    val seqLedger: UInt,
    val seqTime: TimePoint
) : XdrElement{
    override fun encode(stream: XdrStream) {
        stream.writeInt(extensionPoint)
        stream.writeInt(seqLedger.toInt())
        stream.writeLong(seqTime.toLong())
    }

    companion object: XdrElementDecoder<AccountEntryExtensionV3>{
        override fun decode(stream: XdrStream): AccountEntryExtensionV3 {
            val extensionPoint = stream.readInt()
            val seqLedger = stream.readInt().toUInt()
            val seqTime = stream.readLong().toULong()

            return AccountEntryExtensionV3(extensionPoint, seqLedger, seqTime)

        }

    }

}