package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
//
//enum ScValType
//{
//    SCV_BOOL = 0,
//    SCV_VOID = 1,
//    SCV_ERROR = 2,
//
//    // 32 bits is the smallest type in WASM or XDR; no need for u8/u16.
//    SCV_U32 = 3,
//    SCV_I32 = 4,
//
//    // 64 bits is naturally supported by both WASM and XDR also.
//    SCV_U64 = 5,
//    SCV_I64 = 6,
//
//    // Time-related u64 subtypes with their own functions and formatting.
//    SCV_TIMEPOINT = 7,
//    SCV_DURATION = 8,
//
//    // 128 bits is naturally supported by Rust and we use it for Soroban
//    // fixed-point arithmetic prices / balances / similar "quantities". These
//    // are represented in XDR as a pair of 2 u64s.
//    SCV_U128 = 9,
//    SCV_I128 = 10,
//
//    // 256 bits is the size of sha256 output, ed25519 keys, and the EVM machine
//    // word, so for interop use we include this even though it requires a small
//    // amount of Rust guest and/or host library code.
//    SCV_U256 = 11,
//    SCV_I256 = 12,
//
//    // Bytes come in 3 flavors, 2 of which have meaningfully different
//    // formatting and validity-checking / domain-restriction.
//    SCV_BYTES = 13,
//    SCV_STRING = 14,
//    SCV_SYMBOL = 15,
//
//    // Vecs and maps are just polymorphic containers of other ScVals.
//    SCV_VEC = 16,
//    SCV_MAP = 17,
//
//    // Address is the universal identifier for contracts and classic
//    // accounts.
//    SCV_ADDRESS = 18,
//
//    // The following are the internal SCVal variants that are not
//    // exposed to the contracts.
//    SCV_CONTRACT_INSTANCE = 19,
//
//    // SCV_LEDGER_KEY_CONTRACT_INSTANCE and SCV_LEDGER_KEY_NONCE are unique
//    // symbolic SCVals used as the key for ledger entries for a contract's
//    // instance and an address' nonce, respectively.
//    SCV_LEDGER_KEY_CONTRACT_INSTANCE = 20,
//    SCV_LEDGER_KEY_NONCE = 21
//};
///////////////////////////////////////////////////////////////////////////
enum class ScValType(val value: Int) : XdrElement {
    SCV_BOOL(0),
    SCV_VOID(1),
    SCV_ERROR(2),
    SCV_U32(3),
    SCV_I32(4),
    SCV_U64(5),
    SCV_I64(6),
    SCV_TIMEPOINT(7),
    SCV_DURATION(8),
    SCV_U128(9),
    SCV_I128(10),
    SCV_U256(11),
    SCV_I256(12),
    SCV_BYTES(13),
    SCV_STRING(14),
    SCV_SYMBOL(15),
    SCV_VEC(16),
    SCV_MAP(17),
    SCV_ADDRESS(18),
    SCV_CONTRACT_INSTANCE(19),
    SCV_LEDGER_KEY_CONTRACT_INSTANCE(20),
    SCV_LEDGER_KEY_NONCE(21);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<ScValType> {
        override fun decode(stream: XdrStream): ScValType {
            return when(val value = stream.readInt()){
                SCV_BOOL.value -> SCV_BOOL
                SCV_VOID.value -> SCV_VOID
                SCV_ERROR.value -> SCV_ERROR
                SCV_U32.value -> SCV_U32
                SCV_I32.value -> SCV_I32
                SCV_U64.value -> SCV_U64
                SCV_I64.value -> SCV_I64
                SCV_TIMEPOINT.value -> SCV_TIMEPOINT
                SCV_DURATION.value -> SCV_DURATION
                SCV_U128.value -> SCV_U128
                SCV_I128.value -> SCV_I128
                SCV_U256.value -> SCV_U256
                SCV_I256.value -> SCV_I256
                SCV_BYTES.value -> SCV_BYTES
                SCV_STRING.value -> SCV_STRING
                SCV_SYMBOL.value -> SCV_SYMBOL
                SCV_VEC.value -> SCV_VEC
                SCV_MAP.value -> SCV_MAP
                SCV_ADDRESS.value -> SCV_ADDRESS
                SCV_CONTRACT_INSTANCE.value -> SCV_CONTRACT_INSTANCE
                SCV_LEDGER_KEY_CONTRACT_INSTANCE.value -> SCV_LEDGER_KEY_CONTRACT_INSTANCE
                SCV_LEDGER_KEY_NONCE.value -> SCV_LEDGER_KEY_NONCE
                else -> throw IllegalArgumentException("could not decode $value to ScValType")

            }
        }

    }

}