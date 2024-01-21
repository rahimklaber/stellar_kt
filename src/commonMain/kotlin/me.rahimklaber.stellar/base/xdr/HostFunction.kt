package me.rahimklaber.stellar.base.xdr

sealed class HostFunction(val type: HostFunctionType): XdrElement {

    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    data class InvokeContract(val invokeContract: InvokeContractArgs): HostFunction(HostFunctionType.HOST_FUNCTION_TYPE_INVOKE_CONTRACT){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            invokeContract.encode(stream)
        }
    }
    data class CreateContract(val createContract: CreateContractArgs): HostFunction(HostFunctionType.HOST_FUNCTION_TYPE_CREATE_CONTRACT){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            createContract.encode(stream)
        }
    }
    data class UploadWasm(val wasm: ByteArray): HostFunction(HostFunctionType.HOST_FUNCTION_TYPE_CREATE_CONTRACT) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeInt(wasm.size)
            stream.writeBytes(wasm)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as UploadWasm

            return wasm.contentEquals(other.wasm)
        }

        override fun hashCode(): Int {
            return wasm.contentHashCode()
        }
    }

    companion object: XdrElementDecoder<HostFunction> {
        override fun decode(stream: XdrStream): HostFunction {
            return when(val type = HostFunctionType.decode(stream)){
                HostFunctionType.HOST_FUNCTION_TYPE_INVOKE_CONTRACT -> InvokeContract(InvokeContractArgs.decode(stream))
                HostFunctionType.HOST_FUNCTION_TYPE_CREATE_CONTRACT -> CreateContract(CreateContractArgs.decode(stream))
                HostFunctionType.HOST_FUNCTION_TYPE_UPLOAD_CONTRACT_WASM -> UploadWasm(stream.readBytes(stream.readInt()))
            }
        }
    }

}