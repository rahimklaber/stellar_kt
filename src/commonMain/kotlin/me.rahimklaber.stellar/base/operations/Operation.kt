package me.rahimklaber.stellar.base.operations

import me.rahimklaber.stellar.base.Asset
import me.rahimklaber.stellar.base.StrKey
import me.rahimklaber.stellar.base.decodeAccountId
import me.rahimklaber.stellar.base.xdr.MuxedAccount
import me.rahimklaber.stellar.base.xdr.PaymentOp
import me.rahimklaber.stellar.base.xdr.toUint256

sealed interface Operation {
    fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation


}