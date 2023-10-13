package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.encodeToMuxedAccountXDR
import me.rahimklaber.stellar.base.xdr.DataValue
import me.rahimklaber.stellar.base.xdr.ManageDataOp
import me.rahimklaber.stellar.base.xdr.String64

data class ManageData(
    override val sourceAccount: String? = null,
    val name: String,
    val value: ByteArray?
) : Operation{

    constructor(sourceAccount: String? = null, name: String, data: String) : this(sourceAccount, name, data.encodeToByteArray())

    override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
        return me.rahimklaber.stellar.base.xdr.Operation.ManageData(
            sourceAccount = sourceAccount?.let { StrKey.encodeToMuxedAccountXDR(it) },
            ManageDataOp(
                dataName = String64(name.encodeToByteArray()),
                dataValue = value?.let { DataValue(it) }
            )
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ManageData

        if (name != other.name) return false
        if (value != null) {
            if (other.value == null) return false
            if (!value.contentEquals(other.value)) return false
        } else if (other.value != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (value?.contentHashCode() ?: 0)
        return result
    }
}