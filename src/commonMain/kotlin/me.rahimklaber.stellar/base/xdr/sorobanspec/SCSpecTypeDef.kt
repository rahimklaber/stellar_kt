package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// union SCSpecTypeDef switch (SCSpecType type)
//{
//case SC_SPEC_TYPE_VAL:
//case SC_SPEC_TYPE_BOOL:
//case SC_SPEC_TYPE_VOID:
//case SC_SPEC_TYPE_ERROR:
//case SC_SPEC_TYPE_U32:
//case SC_SPEC_TYPE_I32:
//case SC_SPEC_TYPE_U64:
//case SC_SPEC_TYPE_I64:
//case SC_SPEC_TYPE_TIMEPOINT:
//case SC_SPEC_TYPE_DURATION:
//case SC_SPEC_TYPE_U128:
//case SC_SPEC_TYPE_I128:
//case SC_SPEC_TYPE_U256:
//case SC_SPEC_TYPE_I256:
//case SC_SPEC_TYPE_BYTES:
//case SC_SPEC_TYPE_STRING:
//case SC_SPEC_TYPE_SYMBOL:
//case SC_SPEC_TYPE_ADDRESS:
//    void;
//case SC_SPEC_TYPE_OPTION:
//    SCSpecTypeOption option;
//case SC_SPEC_TYPE_RESULT:
//    SCSpecTypeResult result;
//case SC_SPEC_TYPE_VEC:
//    SCSpecTypeVec vec;
//case SC_SPEC_TYPE_MAP:
//    SCSpecTypeMap map;
//case SC_SPEC_TYPE_TUPLE:
//    SCSpecTypeTuple tuple;
//case SC_SPEC_TYPE_BYTES_N:
//    SCSpecTypeBytesN bytesN;
//case SC_SPEC_TYPE_UDT:
//    SCSpecTypeUDT udt;
//};
///////////////////////////////////////////////////////////////////////////
sealed class SCSpecTypeDef(val type: SCSpecType) : XdrElement {
    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    data object Val : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_VAL)
    data object Bool : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_BOOL)
    data object Void : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_VOID)
    data object Error : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_ERROR)
    data object U32 : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_U32)
    data object I32 : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_I32)
    data object U64 : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_U64)
    data object I64 : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_I64)
    data object Timepoint : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_TIMEPOINT)
    data object Duration : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_DURATION)
    data object U128 : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_U128)
    data object I128 : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_I128)
    data object U256 : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_U256)
    data object I256 : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_I256)
    data object Bytes : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_BYTES)
    data object String : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_STRING)
    data object Symbol : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_SYMBOL)
    data object Address : SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_ADDRESS)
    data class Option(val option: SCSpecTypeOption): SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_OPTION){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            option.encode(stream)
        }
    }

    data class Result(val result: SCSpecTypeResult): SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_RESULT){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            result.encode(stream)
        }
    }

    data class Vec(val vec: SCSpecTypeVec): SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_VEC){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            vec.encode(stream)
        }
    }

    data class Map(val map: SCSpecTypeMap): SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_MAP){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            map.encode(stream)
        }
    }

    data class Tuple(val tuple: SCSpecTypeTuple): SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_TUPLE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            tuple.encode(stream)
        }
    }

    data class BytesN(val bytesN: SCSpecTypeBytesN): SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_TUPLE){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            bytesN.encode(stream)
        }
    }

    data class Udt(val udt: SCSpecTypeUDT): SCSpecTypeDef(SCSpecType.SC_SPEC_TYPE_UDT){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            udt.encode(stream)
        }
    }


    companion object: XdrElementDecoder<SCSpecTypeDef> {
        override fun decode(stream: XdrStream): SCSpecTypeDef {
            return when(val type = SCSpecType.decode(stream)){
                SCSpecType.SC_SPEC_TYPE_VAL -> Val
                SCSpecType.SC_SPEC_TYPE_BOOL -> Bool
                SCSpecType.SC_SPEC_TYPE_VOID -> Void
                SCSpecType.SC_SPEC_TYPE_ERROR -> Error
                SCSpecType.SC_SPEC_TYPE_U32 -> U32
                SCSpecType.SC_SPEC_TYPE_I32 -> I32
                SCSpecType.SC_SPEC_TYPE_U64 -> U64
                SCSpecType.SC_SPEC_TYPE_I64 -> I64
                SCSpecType.SC_SPEC_TYPE_TIMEPOINT -> Timepoint
                SCSpecType.SC_SPEC_TYPE_DURATION -> Duration
                SCSpecType.SC_SPEC_TYPE_U128 -> U128
                SCSpecType.SC_SPEC_TYPE_I128 -> I128
                SCSpecType.SC_SPEC_TYPE_U256 -> U256
                SCSpecType.SC_SPEC_TYPE_I256 -> I256
                SCSpecType.SC_SPEC_TYPE_BYTES -> Bytes
                SCSpecType.SC_SPEC_TYPE_STRING -> String
                SCSpecType.SC_SPEC_TYPE_SYMBOL -> Symbol
                SCSpecType.SC_SPEC_TYPE_ADDRESS -> Address
                SCSpecType.SC_SPEC_TYPE_OPTION -> Option(SCSpecTypeOption.decode(stream))
                SCSpecType.SC_SPEC_TYPE_RESULT -> Result(SCSpecTypeResult.decode(stream))
                SCSpecType.SC_SPEC_TYPE_VEC -> Vec(SCSpecTypeVec.decode(stream))
                SCSpecType.SC_SPEC_TYPE_MAP -> Map(SCSpecTypeMap.decode(stream))
                SCSpecType.SC_SPEC_TYPE_TUPLE -> Tuple(SCSpecTypeTuple.decode(stream))
                SCSpecType.SC_SPEC_TYPE_BYTES_N -> BytesN(SCSpecTypeBytesN.decode(stream))
                SCSpecType.SC_SPEC_TYPE_UDT -> Udt(SCSpecTypeUDT.decode(stream))
            }
        }
    }
}