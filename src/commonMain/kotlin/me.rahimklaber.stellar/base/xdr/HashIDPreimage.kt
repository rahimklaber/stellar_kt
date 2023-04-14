package me.rahimklaber.stellar.base.xdr

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
                else -> throw IllegalArgumentException("Could not decode HashIDPreimage for type $type")
            }
        }

    }

}