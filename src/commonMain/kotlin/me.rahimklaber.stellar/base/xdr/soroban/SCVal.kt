package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.*

///////////////////////////////////////////////////////////////////////////
// union SCVal switch (SCValType type)
//{
//
//case SCV_BOOL:
//    bool b;
//case SCV_VOID:
//    void;
//case SCV_ERROR:
//    SCError error;
//
//case SCV_U32:
//    uint32 u32;
//case SCV_I32:
//    int32 i32;
//
//case SCV_U64:
//    uint64 u64;
//case SCV_I64:
//    int64 i64;
//case SCV_TIMEPOINT:
//    TimePoint timepoint;
//case SCV_DURATION:
//    Duration duration;
//
//case SCV_U128:
//    UInt128Parts u128;
//case SCV_I128:
//    Int128Parts i128;
//
//case SCV_U256:
//    UInt256Parts u256;
//case SCV_I256:
//    Int256Parts i256;
//
//case SCV_BYTES:
//    SCBytes bytes;
//case SCV_STRING:
//    SCString str;
//case SCV_SYMBOL:
//    SCSymbol sym;
//
//// Vec and Map are recursive so need to live
//// behind an option, due to xdrpp limitations.
//case SCV_VEC:
//    SCVec *vec;
//case SCV_MAP:
//    SCMap *map;
//
//case SCV_ADDRESS:
//    SCAddress address;
//
//// Special SCVals reserved for system-constructed contract-data
//// ledger keys, not generally usable elsewhere.
//case SCV_LEDGER_KEY_CONTRACT_INSTANCE:
//    void;
//case SCV_LEDGER_KEY_NONCE:
//    SCNonceKey nonce_key;
//
//case SCV_CONTRACT_INSTANCE:
//    SCContractInstance instance;
//};
///////////////////////////////////////////////////////////////////////////
sealed class SCVal(val type: ScValType): XdrElement {

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    data class Bool(val b: Boolean): SCVal(ScValType.SCV_BOOL){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(1)
        }
    }

    data object Void: SCVal(ScValType.SCV_VOID)

    data class Error(val scError: ScError): SCVal(ScValType.SCV_ERROR){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            scError.encode(stream)
        }
    }

    data class U32(val value: UInt): SCVal(ScValType.SCV_U32){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(value.toInt())
        }
    }

    data class I32(val value: Int): SCVal(ScValType.SCV_I32){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(value)
        }
    }

    data class U64(val value: ULong): SCVal(ScValType.SCV_U64){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeULong(value)
        }
    }

    data class I64(val value: Long): SCVal(ScValType.SCV_I64){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeLong(value)
        }
    }

    data class Timepoint(val value: TimePoint): SCVal(ScValType.SCV_TIMEPOINT){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeULong(value)
        }
    }

    data class U128(val u128: UInt128Parts): SCVal(ScValType.SCV_U128){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            u128.encode(stream)
        }
    }

    data class I128(val i128: Int128Parts): SCVal(ScValType.SCV_I128){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            i128.encode(stream)
        }
    }

    data class U256(val u256: UInt256Parts): SCVal(ScValType.SCV_U256){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            u256.encode(stream)
        }
    }

    data class I256(val i256: UInt256Parts): SCVal(ScValType.SCV_I256){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            i256.encode(stream)
        }
    }

    data class Bytes(val bytes: SCBytes): SCVal(ScValType.SCV_BYTES){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            bytes.encode(stream)
        }
    }

    data class String(val str: SCString): SCVal(ScValType.SCV_STRING){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            str.encode(stream)
        }
    }

    data class Symbol(val symbol: SCSymbol): SCVal(ScValType.SCV_SYMBOL){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            symbol.encode(stream)
        }
    }

    data class Vec(val vec: SCVec?): SCVal(ScValType.SCV_VEC){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            vec.encodeNullable(stream)
        }
    }

    data class Map(val map: SCMap?): SCVal(ScValType.SCV_MAP){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            map.encodeNullable(stream)
        }
    }

    data class Address(val address: ScAddress): SCVal(ScValType.SCV_ADDRESS){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            address.encode(stream)
        }
    }

    data object LedgerKeyContractInstance: SCVal(ScValType.SCV_LEDGER_KEY_CONTRACT_INSTANCE)

    data class ContractInstance(val instance: SCContractInstance): SCVal(ScValType.SCV_CONTRACT_INSTANCE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            instance.encode(stream)
        }
    }

    data class LedgerKeyNonce(val nonceKey: SCNonceKey): SCVal(ScValType.SCV_LEDGER_KEY_NONCE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            nonceKey.encode(stream)
        }
    }

    companion object: XdrElementDecoder<SCVal> {
        override fun decode(stream: XdrStream): SCVal {
            return when(val type = ScValType.decode(stream)){
                 ScValType.SCV_BOOL -> Bool(stream.readInt() == 1)
                 ScValType.SCV_VOID -> Void
                 ScValType.SCV_ERROR -> SCVal.Error(ScError.decode(stream))
                 ScValType.SCV_U32 ->U32(stream.readInt().toUInt())
                 ScValType.SCV_I32 ->I32(stream.readInt())
                 ScValType.SCV_U64 ->U64(stream.readULong())
                 ScValType.SCV_I64 ->I64(stream.readLong())
                 ScValType.SCV_TIMEPOINT -> Timepoint(stream.readULong())
                 ScValType.SCV_DURATION -> error("Not suppported yet?")
                 ScValType.SCV_U128 -> U128(UInt128Parts.decode(stream))
                 ScValType.SCV_I128 -> I128(Int128Parts.decode(stream))
                 ScValType.SCV_U256 -> U256(UInt256Parts.decode(stream))
                 ScValType.SCV_I256 -> I256(UInt256Parts.decode(stream))
                 ScValType.SCV_BYTES -> Bytes(SCBytes.decode(stream))
                 ScValType.SCV_STRING -> String(SCString.decode(stream))
                 ScValType.SCV_SYMBOL -> Symbol(SCSymbol.decode(stream))
                 ScValType.SCV_VEC -> Vec(SCVec.decodeNullable(stream))
                 ScValType.SCV_MAP -> Map(SCMap.decodeNullable(stream))
                 ScValType.SCV_ADDRESS -> Address(ScAddress.decode(stream))
                 ScValType.SCV_CONTRACT_INSTANCE -> ContractInstance(SCContractInstance.decode(stream))
                 ScValType.SCV_LEDGER_KEY_CONTRACT_INSTANCE -> LedgerKeyContractInstance
                 ScValType.SCV_LEDGER_KEY_NONCE -> LedgerKeyNonce(SCNonceKey.decode(stream))
            }
        }
    }
}
