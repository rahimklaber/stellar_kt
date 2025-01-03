package me.rahimklaber.stellar.base.xdr

/**
 * /* The LedgerHeader is the highest level structure representing the
 *  * state of a ledger, cryptographically linked to previous ledgers.
 *  */
 * struct LedgerHeader
 * {
 *     uint32 ledgerVersion;    // the protocol version of the ledger
 *     Hash previousLedgerHash; // hash of the previous ledger header
 *     StellarValue scpValue;   // what consensus agreed to
 *     Hash txSetResultHash;    // the TransactionResultSet that led to this ledger
 *     Hash bucketListHash;     // hash of the ledger state
 *
 *     uint32 ledgerSeq; // sequence number of this ledger
 *
 *     int64 totalCoins; // total number of stroops in existence.
 *                       // 10,000,000 stroops in 1 XLM
 *
 *     int64 feePool;       // fees burned since last inflation run
 *     uint32 inflationSeq; // inflation sequence number
 *
 *     uint64 idPool; // last used global ID, used for generating objects
 *
 *     uint32 baseFee;     // base fee per operation in stroops
 *     uint32 baseReserve; // account base reserve in stroops
 *
 *     uint32 maxTxSetSize; // maximum size a transaction set can be
 *
 *     Hash skipList[4]; // hashes of ledgers in the past. allows you to jump back
 *                       // in time without walking the chain back ledger by ledger
 *                       // each slot contains the oldest ledger that is mod of
 *                       // either 50  5000  50000 or 500000 depending on index
 *                       // skipList[0] mod(50), skipList[1] mod(5000), etc
 *
 *     // reserved for future use
 *     union switch (int v)
 *     {
 *     case 0:
 *         void;
 *     case 1:
 *         LedgerHeaderExtensionV1 v1;
 *     }
 *     ext;
 * };
 */
data class LedgerHeader(
    val ledgerVersion: UInt,
    val previousLedgerHash: Hash,
    val scpValue: StellarValue,
    val txSetResultHash: Hash,
    val bucketListHash: Hash,
    val ledgerSeq: UInt,
    val totalCoins: Long,
    val feePool: Long,
    val inflationSeq: UInt,
    val idPool: ULong,
    val baseFee: UInt,
    val baseReserve: UInt,
    val maxTxSetSize: UInt,
    val skipList: List<Hash>,

    val ext: Ext
): XdrElement{
    override fun encode(stream: XdrStream) {
        error("not implemented")
//        stream.writeInt(ledgerVersion.toInt())
//        previousLedgerHash.encode(stream)
//        scpValue.encode(stream)
//        txSetResultHash.encode(stream)
//        bucketListHash.encode(stream)
//        stream.writeInt(ledgerSeq.toInt())
//        stream.writeLong(totalCoins)
//        stream.writelo(feePool)
//        stream.writeInt(inflationSeq.toInt())
//        stream.writeULong(idPool)
//        stream.writeInt(baseFee.toInt())
//        stream.writeInt(baseReserve.toInt())
//        stream.writeInt(maxTxSetSize.toInt())
//
//        //skipList
//        stream.writeInt(4)
//        skipList.forEach{it.encode(stream)}
//
//        stream.writeInt(discriminant)
//        v1Extension.encode(stream)
    }

    companion object: XdrElementDecoder<LedgerHeader> {
        override fun decode(stream: XdrStream): LedgerHeader {

            val ledgerVersion = stream.readInt().toUInt()
            val previousLedgerHash = Hash.decode(stream)
            val scpValue = StellarValue.decode(stream)
            val txSetResultHash = Hash.decode(stream)
            val bucketListHash =  Hash.decode(stream)
            val ledgerSeq = stream.readInt().toUInt()
            val totalCoins = stream.readLong()
            val feePool = stream.readLong()
            val inflationSeq = stream.readInt().toUInt()
            val idPool = stream.readULong()
            val baseFee = stream.readInt().toUInt()
            val baseReserve = stream.readInt().toUInt()
            val maxTxSetSize = stream.readInt().toUInt()


            val skipList = run{
                val list =  mutableListOf<Hash>()

                repeat(4){
                    list.add(Hash.decode(stream))
                }

                list
            }
            val ext = Ext.decode(stream)


            return LedgerHeader(
                ledgerVersion,
                previousLedgerHash,
                scpValue,
                txSetResultHash,
                bucketListHash,
                ledgerSeq,
                totalCoins,
                feePool,
                inflationSeq,
                idPool,
                baseFee,
                baseReserve,
                maxTxSetSize,
                skipList,
                ext
            )
        }
    }

    data class Ext(val v: Int, val v1: LedgerHeaderExtensionV1? = null): XdrElement{
        override fun encode(stream: XdrStream) {
            stream.writeInt(v)
            v1?.encode(stream)
        }

        companion object: XdrElementDecoder<Ext>{
            override fun decode(stream: XdrStream): Ext {
                val v = stream.readInt()

                val v1 = when(v){
                    0 -> null
                    1 -> LedgerHeaderExtensionV1.decode(stream)
                    else -> xdrDecodeError("could not decode LedgerHeader todo...")
                }

                return Ext(
                    v,
                    v1
                )
            }

        }

    }

}

data class LedgerHeaderExtensionV1(val flags: UInt, val discriminant: Int) : XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(flags.toInt())
        stream.writeInt(discriminant)
    }

    companion object: XdrElementDecoder<LedgerHeaderExtensionV1> {
        override fun decode(stream: XdrStream): LedgerHeaderExtensionV1 {
            return LedgerHeaderExtensionV1(
                stream.readInt().toUInt(),
                stream.readInt()
            )
        }
    }

}
