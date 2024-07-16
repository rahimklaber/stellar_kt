package me.rahimklaber.stellar.base.xdr

import me.rahimklaber.stellar.base.xdr.soroban.SCVal

/**
 * struct SorobanTransactionMeta
 * {
 *     SorobanTransactionMetaExt ext;
 *
 *     ContractEvent events<>;             // custom events populated by the
 *                                         // contracts themselves.
 *     SCVal returnValue;                  // return value of the host fn invocation
 *
 *     // Diagnostics events that are not hashed.
 *     // This will contain all contract and diagnostic events. Even ones
 *     // that were emitted in a failed contract call.
 *     DiagnosticEvent diagnosticEvents<>;
 * };
 *
 */
data class SorobanTransactionMeta(
    val sorobanTransactionMetaExt: SorobanTransactionMetaExt,
    val events: List<ContractEvent>,
    val returnValue: SCVal,
    val diagnosticEvents: List<DiagnosticEvent>
): XdrElement {
    override fun encode(stream: XdrStream) {
        sorobanTransactionMetaExt.encode(stream)

        events.encode(stream)
        returnValue.encode(stream)

        diagnosticEvents.encode(stream)

    }

    companion object: XdrElementDecoder<SorobanTransactionMeta> {
        override fun decode(stream: XdrStream): SorobanTransactionMeta {
            val metaExt = SorobanTransactionMetaExt.decode(stream)

            val events = decodeXdrElementList(stream, ContractEvent::decode)
            val returnValue = SCVal.decode(stream)

            val diagnosticEvents = decodeXdrElementList(stream, DiagnosticEvent::decode)

            return SorobanTransactionMeta(metaExt, events, returnValue, diagnosticEvents)
        }
    }

}

/**
 * union SorobanTransactionMetaExt switch (int v)
 * {
 * case 0:
 *     void;
 * case 1:
 *     SorobanTransactionMetaExtV1 v1;
 * };
 *
 */
sealed class SorobanTransactionMetaExt(val discriminant: Int): XdrElement{
    data object None : SorobanTransactionMetaExt(0){
        override fun encode(stream: XdrStream) {
            stream.writeInt(0)
        }
    }
    data class V1(
        val v1: SorobanTransactionMetaExtV1
    ): SorobanTransactionMetaExt(1){
        override fun encode(stream: XdrStream) {
            stream.writeInt(1)
            v1.encode(stream)
        }
    }

    companion object: XdrElementDecoder<SorobanTransactionMetaExt> {
        override fun decode(stream: XdrStream): SorobanTransactionMetaExt {
            return when(val value = stream.readInt()){
                0 -> None
                1 -> V1(SorobanTransactionMetaExtV1.decode(stream))
                else -> throw IllegalArgumentException("Could not decode SorobanTransactionMetaExt for $value")
            }
        }
    }
}

/**
 * struct SorobanTransactionMetaExtV1
 * {
 *     ExtensionPoint ext;
 *
 *     // The following are the components of the overall Soroban resource fee
 *     // charged for the transaction.
 *     // The following relation holds:
 *     // `resourceFeeCharged = totalNonRefundableResourceFeeCharged + totalRefundableResourceFeeCharged`
 *     // where `resourceFeeCharged` is the overall fee charged for the
 *     // transaction. Also, `resourceFeeCharged` <= `sorobanData.resourceFee`
 *     // i.e.we never charge more than the declared resource fee.
 *     // The inclusion fee for charged the Soroban transaction can be found using
 *     // the following equation:
 *     // `result.feeCharged = resourceFeeCharged + inclusionFeeCharged`.
 *
 *     // Total amount (in stroops) that has been charged for non-refundable
 *     // Soroban resources.
 *     // Non-refundable resources are charged based on the usage declared in
 *     // the transaction envelope (such as `instructions`, `readBytes` etc.) and
 *     // is charged regardless of the success of the transaction.
 *     int64 totalNonRefundableResourceFeeCharged;
 *     // Total amount (in stroops) that has been charged for refundable
 *     // Soroban resource fees.
 *     // Currently this comprises the rent fee (`rentFeeCharged`) and the
 *     // fee for the events and return value.
 *     // Refundable resources are charged based on the actual resources usage.
 *     // Since currently refundable resources are only used for the successful
 *     // transactions, this will be `0` for failed transactions.
 *     int64 totalRefundableResourceFeeCharged;
 *     // Amount (in stroops) that has been charged for rent.
 *     // This is a part of `totalNonRefundableResourceFeeCharged`.
 *     int64 rentFeeCharged;
 * };
 */
data class SorobanTransactionMetaExtV1(
    val totalNonRefundableResourceFeeCharged: Long,
    val totalRefundableResourceFeeCharged: Long,
    val rentFeeCharged: Long,
): XdrElement {
    override fun encode(stream: XdrStream) {
        stream.writeInt(0) //ext :(

        stream.writeLong(totalNonRefundableResourceFeeCharged)
        stream.writeLong(totalRefundableResourceFeeCharged)
        stream.writeLong(rentFeeCharged)
    }

    companion object: XdrElementDecoder<SorobanTransactionMetaExtV1> {
        override fun decode(stream: XdrStream): SorobanTransactionMetaExtV1 {
            stream.readInt() // 0 ext point

            return SorobanTransactionMetaExtV1(stream.readLong(), stream.readLong(), stream.readLong())
        }
    }
}
