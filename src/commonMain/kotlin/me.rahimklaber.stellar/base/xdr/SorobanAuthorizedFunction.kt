// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SorobanAuthorizedFunction's original definition in the XDR file is:
 * ```
 * union SorobanAuthorizedFunction switch (SorobanAuthorizedFunctionType type)
{
case SOROBAN_AUTHORIZED_FUNCTION_TYPE_CONTRACT_FN:
InvokeContractArgs contractFn;
// This variant of auth payload for creating new contract instances
// doesn't allow specifying the constructor arguments, creating contracts
// with constructors that take arguments is only possible by authorizing
// `SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_V2_HOST_FN`
// (protocol 22+).
case SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_HOST_FN:
CreateContractArgs createContractHostFn;
// This variant of auth payload for creating new contract instances
// is only accepted in and after protocol 22. It allows authorizing the
// contract constructor arguments.
case SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_V2_HOST_FN:
CreateContractArgsV2 createContractV2HostFn;
};
 * ```
 */
sealed class SorobanAuthorizedFunction(val type: SorobanAuthorizedFunctionType) : XdrElement {
    fun contractFnOrNull(): ContractFn? = if (this is ContractFn) this else null
    data class ContractFn(
        val contractFn: InvokeContractArgs,
    ) : SorobanAuthorizedFunction(SorobanAuthorizedFunctionType.SOROBAN_AUTHORIZED_FUNCTION_TYPE_CONTRACT_FN) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            contractFn.encode(stream)
        }
    }

    fun createContractHostFnOrNull(): CreateContractHostFn? = if (this is CreateContractHostFn) this else null
    data class CreateContractHostFn(
        val createContractHostFn: CreateContractArgs,
    ) : SorobanAuthorizedFunction(SorobanAuthorizedFunctionType.SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_HOST_FN) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            createContractHostFn.encode(stream)
        }
    }

    fun createContractV2HostFnOrNull(): CreateContractV2HostFn? = if (this is CreateContractV2HostFn) this else null
    data class CreateContractV2HostFn(
        val createContractV2HostFn: CreateContractArgsV2,
    ) : SorobanAuthorizedFunction(SorobanAuthorizedFunctionType.SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_V2_HOST_FN) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            createContractV2HostFn.encode(stream)
        }
    }

    companion object : XdrElementDecoder<SorobanAuthorizedFunction> {
        override fun decode(stream: XdrInputStream): SorobanAuthorizedFunction {
            return when (val type = SorobanAuthorizedFunctionType.decode(stream)) {
                SorobanAuthorizedFunctionType.SOROBAN_AUTHORIZED_FUNCTION_TYPE_CONTRACT_FN -> {
                    val contractFn = InvokeContractArgs.decode(stream)
                    ContractFn(contractFn)
                }

                SorobanAuthorizedFunctionType.SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_HOST_FN -> {
                    val createContractHostFn = CreateContractArgs.decode(stream)
                    CreateContractHostFn(createContractHostFn)
                }

                SorobanAuthorizedFunctionType.SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_V2_HOST_FN -> {
                    val createContractV2HostFn = CreateContractArgsV2.decode(stream)
                    CreateContractV2HostFn(createContractV2HostFn)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
