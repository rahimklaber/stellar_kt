// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCVal's original definition in the XDR file is:
 * ```
 * union SCVal switch (SCValType type)
{

case SCV_BOOL:
bool b;
case SCV_VOID:
void;
case SCV_ERROR:
SCError error;

case SCV_U32:
uint32 u32;
case SCV_I32:
int32 i32;

case SCV_U64:
uint64 u64;
case SCV_I64:
int64 i64;
case SCV_TIMEPOINT:
TimePoint timepoint;
case SCV_DURATION:
Duration duration;

case SCV_U128:
UInt128Parts u128;
case SCV_I128:
Int128Parts i128;

case SCV_U256:
UInt256Parts u256;
case SCV_I256:
Int256Parts i256;

case SCV_BYTES:
SCBytes bytes;
case SCV_STRING:
SCString str;
case SCV_SYMBOL:
SCSymbol sym;

// Vec and Map are recursive so need to live
// behind an option, due to xdrpp limitations.
case SCV_VEC:
SCVec *vec;
case SCV_MAP:
SCMap *map;

case SCV_ADDRESS:
SCAddress address;

// Special SCVals reserved for system-constructed contract-data
// ledger keys, not generally usable elsewhere.
case SCV_LEDGER_KEY_CONTRACT_INSTANCE:
void;
case SCV_LEDGER_KEY_NONCE:
SCNonceKey nonce_key;

case SCV_CONTRACT_INSTANCE:
SCContractInstance instance;
};
 * ```
 */
sealed class SCVal(val type: SCValType) : XdrElement {
    fun bOrNull(): Bool? = if (this is Bool) this else null
    data class Bool(
        val b: Boolean,
    ) : SCVal(SCValType.SCV_BOOL) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            stream.writeBoolean(b)
        }
    }

    data object Void : SCVal(SCValType.SCV_VOID) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    fun errorOrNull(): Error? = if (this is Error) this else null
    data class Error(
        val error: SCError,
    ) : SCVal(SCValType.SCV_ERROR) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            error.encode(stream)
        }
    }

    fun u32OrNull(): U32? = if (this is U32) this else null
    data class U32(
        val u32: Uint32,
    ) : SCVal(SCValType.SCV_U32) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            u32.encode(stream)
        }
    }

    fun i32OrNull(): I32? = if (this is I32) this else null
    data class I32(
        val i32: Int32,
    ) : SCVal(SCValType.SCV_I32) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            i32.encode(stream)
        }
    }

    fun u64OrNull(): U64? = if (this is U64) this else null
    data class U64(
        val u64: Uint64,
    ) : SCVal(SCValType.SCV_U64) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            u64.encode(stream)
        }
    }

    fun i64OrNull(): I64? = if (this is I64) this else null
    data class I64(
        val i64: Int64,
    ) : SCVal(SCValType.SCV_I64) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            i64.encode(stream)
        }
    }

    fun timepointOrNull(): Timepoint? = if (this is Timepoint) this else null
    data class Timepoint(
        val timepoint: TimePoint,
    ) : SCVal(SCValType.SCV_TIMEPOINT) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            timepoint.encode(stream)
        }
    }

    fun durationOrNull(): Duration? = if (this is Duration) this else null
    data class Duration(
        val duration: me.rahimklaber.stellar.base.xdr.Duration,
    ) : SCVal(SCValType.SCV_DURATION) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            duration.encode(stream)
        }
    }

    fun u128OrNull(): U128? = if (this is U128) this else null
    data class U128(
        val u128: UInt128Parts,
    ) : SCVal(SCValType.SCV_U128) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            u128.encode(stream)
        }
    }

    fun i128OrNull(): I128? = if (this is I128) this else null
    data class I128(
        val i128: Int128Parts,
    ) : SCVal(SCValType.SCV_I128) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            i128.encode(stream)
        }
    }

    fun u256OrNull(): U256? = if (this is U256) this else null
    data class U256(
        val u256: UInt256Parts,
    ) : SCVal(SCValType.SCV_U256) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            u256.encode(stream)
        }
    }

    fun i256OrNull(): I256? = if (this is I256) this else null
    data class I256(
        val i256: Int256Parts,
    ) : SCVal(SCValType.SCV_I256) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            i256.encode(stream)
        }
    }

    fun bytesOrNull(): Bytes? = if (this is Bytes) this else null
    data class Bytes(
        val bytes: SCBytes,
    ) : SCVal(SCValType.SCV_BYTES) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            bytes.encode(stream)
        }
    }

    fun strOrNull(): String? = if (this is String) this else null
    data class String(
        val str: SCString,
    ) : SCVal(SCValType.SCV_STRING) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            str.encode(stream)
        }
    }

    fun symOrNull(): Symbol? = if (this is Symbol) this else null
    data class Symbol(
        val sym: SCSymbol,
    ) : SCVal(SCValType.SCV_SYMBOL) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            sym.encode(stream)
        }
    }

    fun vecOrNull(): Vec? = if (this is Vec) this else null
    data class Vec(
        val vec: SCVec?,
    ) : SCVal(SCValType.SCV_VEC) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            if (vec != null) {
                stream.writeInt(1)
                vec.encode(stream)
            } else {
                stream.writeInt(0)
            }
        }
    }

    fun mapOrNull(): Map? = if (this is Map) this else null
    data class Map(
        val map: SCMap?,
    ) : SCVal(SCValType.SCV_MAP) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            if (map != null) {
                stream.writeInt(1)
                map.encode(stream)
            } else {
                stream.writeInt(0)
            }
        }
    }

    fun addressOrNull(): Address? = if (this is Address) this else null
    data class Address(
        val address: SCAddress,
    ) : SCVal(SCValType.SCV_ADDRESS) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            address.encode(stream)
        }
    }

    data object LedgerKeyContractInstance : SCVal(SCValType.SCV_LEDGER_KEY_CONTRACT_INSTANCE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    fun nonceKeyOrNull(): LedgerKeyNonce? = if (this is LedgerKeyNonce) this else null
    data class LedgerKeyNonce(
        val nonceKey: SCNonceKey,
    ) : SCVal(SCValType.SCV_LEDGER_KEY_NONCE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            nonceKey.encode(stream)
        }
    }

    fun instanceOrNull(): ContractInstance? = if (this is ContractInstance) this else null
    data class ContractInstance(
        val instance: SCContractInstance,
    ) : SCVal(SCValType.SCV_CONTRACT_INSTANCE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            instance.encode(stream)
        }
    }

    companion object : XdrElementDecoder<SCVal> {
        override fun decode(stream: XdrInputStream): SCVal {
            return when (val type = SCValType.decode(stream)) {
                SCValType.SCV_BOOL -> {
                    val b = stream.readBoolean()
                    Bool(b)
                }

                SCValType.SCV_VOID -> Void
                SCValType.SCV_ERROR -> {
                    val error = SCError.decode(stream)
                    Error(error)
                }

                SCValType.SCV_U32 -> {
                    val u32 = Uint32.decode(stream)
                    U32(u32)
                }

                SCValType.SCV_I32 -> {
                    val i32 = Int32.decode(stream)
                    I32(i32)
                }

                SCValType.SCV_U64 -> {
                    val u64 = Uint64.decode(stream)
                    U64(u64)
                }

                SCValType.SCV_I64 -> {
                    val i64 = Int64.decode(stream)
                    I64(i64)
                }

                SCValType.SCV_TIMEPOINT -> {
                    val timepoint = TimePoint.decode(stream)
                    Timepoint(timepoint)
                }

                SCValType.SCV_DURATION -> {
                    val duration = me.rahimklaber.stellar.base.xdr.Duration.decode(stream)
                    Duration(duration)
                }

                SCValType.SCV_U128 -> {
                    val u128 = UInt128Parts.decode(stream)
                    U128(u128)
                }

                SCValType.SCV_I128 -> {
                    val i128 = Int128Parts.decode(stream)
                    I128(i128)
                }

                SCValType.SCV_U256 -> {
                    val u256 = UInt256Parts.decode(stream)
                    U256(u256)
                }

                SCValType.SCV_I256 -> {
                    val i256 = Int256Parts.decode(stream)
                    I256(i256)
                }

                SCValType.SCV_BYTES -> {
                    val bytes = SCBytes.decode(stream)
                    Bytes(bytes)
                }

                SCValType.SCV_STRING -> {
                    val str = SCString.decode(stream)
                    String(str)
                }

                SCValType.SCV_SYMBOL -> {
                    val sym = SCSymbol.decode(stream)
                    Symbol(sym)
                }

                SCValType.SCV_VEC -> {
                    val vecPresent = stream.readInt()
                    val vec = if (vecPresent != 0) {
                        SCVec.decode(stream)
                    } else {
                        null
                    }
                    Vec(vec)
                }

                SCValType.SCV_MAP -> {
                    val mapPresent = stream.readInt()
                    val map = if (mapPresent != 0) {
                        SCMap.decode(stream)
                    } else {
                        null
                    }
                    Map(map)
                }

                SCValType.SCV_ADDRESS -> {
                    val address = SCAddress.decode(stream)
                    Address(address)
                }

                SCValType.SCV_LEDGER_KEY_CONTRACT_INSTANCE -> LedgerKeyContractInstance
                SCValType.SCV_LEDGER_KEY_NONCE -> {
                    val nonce_key = SCNonceKey.decode(stream)
                    LedgerKeyNonce(nonce_key)
                }

                SCValType.SCV_CONTRACT_INSTANCE -> {
                    val instance = SCContractInstance.decode(stream)
                    ContractInstance(instance)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
