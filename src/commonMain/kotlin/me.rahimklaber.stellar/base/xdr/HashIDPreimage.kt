package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union HashIDPreimage switch (EnvelopeType type)
//{
//case ENVELOPE_TYPE_OP_ID:
//    struct
//    {
//        AccountID sourceAccount;
//        SequenceNumber seqNum;
//        uint32 opNum;
//    } operationID;
//case ENVELOPE_TYPE_POOL_REVOKE_OP_ID:
//    struct
//    {
//        AccountID sourceAccount;
//        SequenceNumber seqNum;
//        uint32 opNum;
//        PoolID liquidityPoolID;
//        Asset asset;
//    } revokeID;
//case ENVELOPE_TYPE_CONTRACT_ID:
//    struct
//    {
//        Hash networkID;
//        ContractIDPreimage contractIDPreimage;
//    } contractID;
//case ENVELOPE_TYPE_SOROBAN_AUTHORIZATION:
//    struct
//    {
//        Hash networkID;
//        int64 nonce;
//        uint32 signatureExpirationLedger;
//        SorobanAuthorizedInvocation invocation;
//    } sorobanAuthorization;
//};
///////////////////////////////////////////////////////////////////////////
sealed class HashIDPreimage(val type: EnvelopeType): XdrElement {

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    data class OperationID(
        val sourceAccount: AccountID,
        val seqNum: SequenceNumber,
        val opNum: UInt
    ) : HashIDPreimage(EnvelopeType.ENVELOPE_TYPE_OP_ID){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            sourceAccount.encode(stream)
            stream.writeLong(seqNum)
            stream.writeInt(opNum.toInt())
        }
    }

    data class RevokeId(
        val sourceAccount: AccountID,
        val seqNum: SequenceNumber,
        val opNum: UInt,
        val liquidityPoolID: PoolID,
        val asset: Asset
    ) : HashIDPreimage(EnvelopeType.ENVELOPE_TYPE_POOL_REVOKE_OP_ID){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            sourceAccount.encode(stream)
            stream.writeLong(seqNum)
            stream.writeInt(opNum.toInt())
            liquidityPoolID.encode(stream)
            asset.encode(stream)
        }
    }

    data class ContractId(
        val networkId: Hash,
        val contractIDPreimage: ContractIDPreimage,
    ) : HashIDPreimage(EnvelopeType.ENVELOPE_TYPE_CONTRACT_ID) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            networkId.encode(stream)
            contractIDPreimage.encode(stream)
        }
    }

    data class SorobanAuthorization(
        val networkId: Hash,
        val nonce: Long,
        val signatureExpirationLedger: UInt,
        val invocation: SorobanAuthorizedInvocation,
    ) : HashIDPreimage(EnvelopeType.ENVELOPE_TYPE_SOROBAN_AUTHORIZATION) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            networkId.encode(stream)
            stream.writeLong(nonce)
            stream.writeInt(signatureExpirationLedger.toInt())
            invocation.encode(stream)
        }
    }

    companion object: XdrElementDecoder<HashIDPreimage>{
        override fun decode(stream: XdrStream): HashIDPreimage {
            return when(val type = EnvelopeType.decode(stream)){
                EnvelopeType.ENVELOPE_TYPE_OP_ID -> {
                    val sourceAccount = AccountID.decode(stream)
                    val seqNum = stream.readLong()
                    val opNum = stream.readInt().toUInt()
                    OperationID(sourceAccount, seqNum, opNum)
                }
                EnvelopeType.ENVELOPE_TYPE_POOL_REVOKE_OP_ID -> {
                    val sourceAccount = AccountID.decode(stream)
                    val seqNum = stream.readLong()
                    val opNum = stream.readInt().toUInt()
                    val liquidityPoolID = PoolID.decode(stream)
                    val asset = Asset.decode(stream)
                    RevokeId(sourceAccount, seqNum, opNum, liquidityPoolID, asset)
                }

                EnvelopeType.ENVELOPE_TYPE_CONTRACT_ID -> {
                    ContractId(
                        Hash.decode(stream),
                        ContractIDPreimage.decode(stream)
                    )
                }

                EnvelopeType.ENVELOPE_TYPE_SOROBAN_AUTHORIZATION -> {
                    SorobanAuthorization(
                        Hash.decode(stream),
                        stream.readLong(),
                        stream.readInt().toUInt(),
                        SorobanAuthorizedInvocation.decode(stream)
                    )
                }

                else -> throw IllegalArgumentException("Could not decode HashIDPreimage for type $type")
            }
        }

    }

}