package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// enum SCErrorType
//{
//    SCE_CONTRACT = 0,          // Contract-specific, user-defined codes.
//    SCE_WASM_VM = 1,           // Errors while interpreting WASM bytecode.
//    SCE_CONTEXT = 2,           // Errors in the contract's host context.
//    SCE_STORAGE = 3,           // Errors accessing host storage.
//    SCE_OBJECT = 4,            // Errors working with host objects.
//    SCE_CRYPTO = 5,            // Errors in cryptographic operations.
//    SCE_EVENTS = 6,            // Errors while emitting events.
//    SCE_BUDGET = 7,            // Errors relating to budget limits.
//    SCE_VALUE = 8,             // Errors working with host values or SCVals.
//    SCE_AUTH = 9               // Errors from the authentication subsystem.
//};
///////////////////////////////////////////////////////////////////////////
enum class ScErrorType(val value: Int) : XdrElement {
    SCE_CONTRACT(0),
    SCE_WASM_VM(1),
    SCE_CONTEXT(2),
    SCE_STORAGE(3),
    SCE_OBJECT(4),
    SCE_CRYPTO(5),
    SCE_EVENTS(6),
    SCE_BUDGET(7),
    SCE_VALUE(8),
    SCE_AUTH(9);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object : XdrElementDecoder<ScErrorType> {
        override fun decode(stream: XdrStream): ScErrorType {
            val value = stream.readInt()
            return ScErrorType.entries.getOrNull(value)
                ?: throw IllegalArgumentException("could not decode $value to SCErrorType")
        }
    }

}