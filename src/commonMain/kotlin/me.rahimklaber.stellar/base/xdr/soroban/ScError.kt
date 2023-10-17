package me.rahimklaber.stellar.base.xdr.soroban

import me.rahimklaber.stellar.base.xdr.XdrElement
import me.rahimklaber.stellar.base.xdr.XdrElementDecoder
import me.rahimklaber.stellar.base.xdr.XdrStream

///////////////////////////////////////////////////////////////////////////
// // Smart contract errors are split into a type (SCErrorType) and a code. When an
//// error is of type SCE_CONTRACT it carries a user-defined uint32 code that
//// Soroban assigns no specific meaning to. In all other cases, the type
//// specifies a subsystem of the Soroban host where the error originated, and the
//// accompanying code is an SCErrorCode, each of which specifies a slightly more
//// precise class of errors within that subsystem.
////
//// Error types and codes are not maximally precise; there is a tradeoff between
//// precision and flexibility in the implementation, and the granularity here is
//// chosen to be adequate for most purposes while not placing a burden on future
//// system evolution and maintenance. When additional precision is needed for
//// debugging, Soroban can be run with diagnostic events enabled.
//
//union SCError switch (SCErrorType type)
//{
//case SCE_CONTRACT:
//    uint32 contractCode;
//case SCE_WASM_VM:
//case SCE_CONTEXT:
//case SCE_STORAGE:
//case SCE_OBJECT:
//case SCE_CRYPTO:
//case SCE_EVENTS:
//case SCE_BUDGET:
//case SCE_VALUE:
//case SCE_AUTH:
//    SCErrorCode code;
//};
///////////////////////////////////////////////////////////////////////////
sealed class ScError(val type: ScErrorType) : XdrElement{

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    data class Contract(val contractCode: UInt) : ScError(ScErrorType.SCE_CONTRACT) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(contractCode.toInt())
        }
    }

    //non contract error
    sealed class SystemScError(type: ScErrorType) : ScError(type) {
        abstract val code: ScErrorCode
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            code.encode(stream)
        }
    }

    data class WasmVm(override val code: ScErrorCode): SystemScError(ScErrorType.SCE_WASM_VM)
    data class Context(override val code: ScErrorCode): SystemScError(ScErrorType.SCE_CONTEXT)
    data class Storage(override val code: ScErrorCode): SystemScError(ScErrorType.SCE_STORAGE)
    data class Object(override val code: ScErrorCode): SystemScError(ScErrorType.SCE_OBJECT)
    data class Crypto(override val code: ScErrorCode): SystemScError(ScErrorType.SCE_CRYPTO)
    data class Events(override val code: ScErrorCode): SystemScError(ScErrorType.SCE_EVENTS)
    data class Budget(override val code: ScErrorCode): SystemScError(ScErrorType.SCE_BUDGET)
    data class Value(override val code: ScErrorCode): SystemScError(ScErrorType.SCE_VALUE)
    data class Auth(override val code: ScErrorCode): SystemScError(ScErrorType.SCE_AUTH)


    companion object : XdrElementDecoder<ScError> {
        override fun decode(stream: XdrStream): ScError {
            TODO()
        }
    }

}