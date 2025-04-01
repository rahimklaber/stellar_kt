// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * HostFunction's original definition in the XDR file is:
 * ```
 * union HostFunction switch (HostFunctionType type)
{
case HOST_FUNCTION_TYPE_INVOKE_CONTRACT:
InvokeContractArgs invokeContract;
case HOST_FUNCTION_TYPE_CREATE_CONTRACT:
CreateContractArgs createContract;
case HOST_FUNCTION_TYPE_UPLOAD_CONTRACT_WASM:
opaque wasm<>;
case HOST_FUNCTION_TYPE_CREATE_CONTRACT_V2:
CreateContractArgsV2 createContractV2;
};
 * ```
 */
sealed class HostFunction(val type: HostFunctionType) : XdrElement {
    fun invokeContractOrNull(): InvokeContract? = if (this is InvokeContract) this else null
    data class InvokeContract(
        val invokeContract: InvokeContractArgs,
    ) : HostFunction(HostFunctionType.HOST_FUNCTION_TYPE_INVOKE_CONTRACT) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            invokeContract.encode(stream)
        }
    }

    fun createContractOrNull(): CreateContract? = if (this is CreateContract) this else null
    data class CreateContract(
        val createContract: CreateContractArgs,
    ) : HostFunction(HostFunctionType.HOST_FUNCTION_TYPE_CREATE_CONTRACT) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            createContract.encode(stream)
        }
    }

    fun wasmOrNull(): UploadContractWasm? = if (this is UploadContractWasm) this else null
    data class UploadContractWasm(
        val wasm: ByteArray,
    ) : HostFunction(HostFunctionType.HOST_FUNCTION_TYPE_UPLOAD_CONTRACT_WASM) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            val wasmSize = wasm.size
            stream.writeInt(wasmSize)
            stream.writeBytes(wasm)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as UploadContractWasm

            return wasm.contentEquals(other.wasm)
        }

        override fun hashCode(): Int {
            return wasm.contentHashCode()
        }
    }

    fun createContractV2OrNull(): CreateContractV2? = if (this is CreateContractV2) this else null
    data class CreateContractV2(
        val createContractV2: CreateContractArgsV2,
    ) : HostFunction(HostFunctionType.HOST_FUNCTION_TYPE_CREATE_CONTRACT_V2) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            createContractV2.encode(stream)
        }
    }

    companion object : XdrElementDecoder<HostFunction> {
        override fun decode(stream: XdrInputStream): HostFunction {
            return when (val type = HostFunctionType.decode(stream)) {
                HostFunctionType.HOST_FUNCTION_TYPE_INVOKE_CONTRACT -> {
                    val invokeContract = InvokeContractArgs.decode(stream)
                    InvokeContract(invokeContract)
                }

                HostFunctionType.HOST_FUNCTION_TYPE_CREATE_CONTRACT -> {
                    val createContract = CreateContractArgs.decode(stream)
                    CreateContract(createContract)
                }

                HostFunctionType.HOST_FUNCTION_TYPE_UPLOAD_CONTRACT_WASM -> {
                    val wasmSize = stream.readInt()
                    val wasm = stream.readBytes(wasmSize)
                    UploadContractWasm(wasm)
                }

                HostFunctionType.HOST_FUNCTION_TYPE_CREATE_CONTRACT_V2 -> {
                    val createContractV2 = CreateContractArgsV2.decode(stream)
                    CreateContractV2(createContractV2)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
