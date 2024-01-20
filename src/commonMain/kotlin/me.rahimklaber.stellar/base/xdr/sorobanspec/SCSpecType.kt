package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// enum SCSpecType
//{
//    SC_SPEC_TYPE_VAL = 0,
//
//    // Types with no parameters.
//    SC_SPEC_TYPE_BOOL = 1,
//    SC_SPEC_TYPE_VOID = 2,
//    SC_SPEC_TYPE_ERROR = 3,
//    SC_SPEC_TYPE_U32 = 4,
//    SC_SPEC_TYPE_I32 = 5,
//    SC_SPEC_TYPE_U64 = 6,
//    SC_SPEC_TYPE_I64 = 7,
//    SC_SPEC_TYPE_TIMEPOINT = 8,
//    SC_SPEC_TYPE_DURATION = 9,
//    SC_SPEC_TYPE_U128 = 10,
//    SC_SPEC_TYPE_I128 = 11,
//    SC_SPEC_TYPE_U256 = 12,
//    SC_SPEC_TYPE_I256 = 13,
//    SC_SPEC_TYPE_BYTES = 14,
//    SC_SPEC_TYPE_STRING = 16,
//    SC_SPEC_TYPE_SYMBOL = 17,
//    SC_SPEC_TYPE_ADDRESS = 19,
//
//    // Types with parameters.
//    SC_SPEC_TYPE_OPTION = 1000,
//    SC_SPEC_TYPE_RESULT = 1001,
//    SC_SPEC_TYPE_VEC = 1002,
//    SC_SPEC_TYPE_MAP = 1004,
//    SC_SPEC_TYPE_TUPLE = 1005,
//    SC_SPEC_TYPE_BYTES_N = 1006,
//
//    // User defined types.
//    SC_SPEC_TYPE_UDT = 2000
//};
///////////////////////////////////////////////////////////////////////////
enum class SCSpecType(val value: Int) : XdrElement {
    SC_SPEC_TYPE_VAL(0),

    // Types with no parameters.
    SC_SPEC_TYPE_BOOL(1),
    SC_SPEC_TYPE_VOID(2),
    SC_SPEC_TYPE_ERROR(3),
    SC_SPEC_TYPE_U32(4),
    SC_SPEC_TYPE_I32(5),
    SC_SPEC_TYPE_U64(6),
    SC_SPEC_TYPE_I64(7),
    SC_SPEC_TYPE_TIMEPOINT(8),
    SC_SPEC_TYPE_DURATION(9),
    SC_SPEC_TYPE_U128(10),
    SC_SPEC_TYPE_I128(11),
    SC_SPEC_TYPE_U256(12),
    SC_SPEC_TYPE_I256(13),
    SC_SPEC_TYPE_BYTES(14),
    SC_SPEC_TYPE_STRING(16),
    SC_SPEC_TYPE_SYMBOL(17),
    SC_SPEC_TYPE_ADDRESS(19),

    // Types with parameters.
    SC_SPEC_TYPE_OPTION(1000),
    SC_SPEC_TYPE_RESULT(1001),
    SC_SPEC_TYPE_VEC(1002),
    SC_SPEC_TYPE_MAP(1004),
    SC_SPEC_TYPE_TUPLE(1005),
    SC_SPEC_TYPE_BYTES_N(1006),

    // User defined types.
    SC_SPEC_TYPE_UDT(2000);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object : XdrElementDecoder<SCSpecType> {
        override fun decode(stream: XdrStream): SCSpecType {
            return when (val value = stream.readInt()) {
                SC_SPEC_TYPE_VAL.value -> SC_SPEC_TYPE_VAL
                SC_SPEC_TYPE_BOOL.value -> SC_SPEC_TYPE_BOOL
                SC_SPEC_TYPE_VOID.value -> SC_SPEC_TYPE_VOID
                SC_SPEC_TYPE_ERROR.value -> SC_SPEC_TYPE_ERROR
                SC_SPEC_TYPE_U32.value -> SC_SPEC_TYPE_U32
                SC_SPEC_TYPE_I32.value -> SC_SPEC_TYPE_I32
                SC_SPEC_TYPE_U64.value -> SC_SPEC_TYPE_U64
                SC_SPEC_TYPE_I64.value -> SC_SPEC_TYPE_I64
                SC_SPEC_TYPE_TIMEPOINT.value -> SC_SPEC_TYPE_TIMEPOINT
                SC_SPEC_TYPE_DURATION.value -> SC_SPEC_TYPE_DURATION
                SC_SPEC_TYPE_U128.value -> SC_SPEC_TYPE_U128
                SC_SPEC_TYPE_I128.value -> SC_SPEC_TYPE_I128
                SC_SPEC_TYPE_U256.value -> SC_SPEC_TYPE_U256
                SC_SPEC_TYPE_I256.value -> SC_SPEC_TYPE_I256
                SC_SPEC_TYPE_BYTES.value -> SC_SPEC_TYPE_BYTES
                SC_SPEC_TYPE_STRING.value -> SC_SPEC_TYPE_STRING
                SC_SPEC_TYPE_SYMBOL.value -> SC_SPEC_TYPE_SYMBOL
                SC_SPEC_TYPE_ADDRESS.value -> SC_SPEC_TYPE_ADDRESS
                SC_SPEC_TYPE_OPTION.value -> SC_SPEC_TYPE_OPTION
                SC_SPEC_TYPE_RESULT.value -> SC_SPEC_TYPE_RESULT
                SC_SPEC_TYPE_VEC.value -> SC_SPEC_TYPE_VEC
                SC_SPEC_TYPE_MAP.value -> SC_SPEC_TYPE_MAP
                SC_SPEC_TYPE_TUPLE.value -> SC_SPEC_TYPE_TUPLE
                SC_SPEC_TYPE_BYTES_N.value -> SC_SPEC_TYPE_BYTES_N
                SC_SPEC_TYPE_UDT.value -> SC_SPEC_TYPE_UDT
                else -> throw IllegalArgumentException("Cannot decode SCSpecType for value $value")
            }
        }
    }
}