package me.rahimklaber.stellar.base.xdr

sealed class SorobanAuthorizedFunction(val type: SorobanAuthorizedFunctionType): XdrElement {
    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }
    data class ContractFn(val contractFn: InvokeContractArgs): SorobanAuthorizedFunction(SorobanAuthorizedFunctionType.SOROBAN_AUTHORIZED_FUNCTION_TYPE_CONTRACT_FN){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            contractFn.encode(stream)
        }
    }
    data class CreateContractHostFn(val createContractHostFn: CreateContractArgs): SorobanAuthorizedFunction(SorobanAuthorizedFunctionType.SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_HOST_FN){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            createContractHostFn.encode(stream)
        }
    }

    companion object: XdrElementDecoder<SorobanAuthorizedFunction> {
        override fun decode(stream: XdrStream): SorobanAuthorizedFunction {
            val type = SorobanAuthorizedFunctionType.decode(stream)
            return when(type){
                SorobanAuthorizedFunctionType.SOROBAN_AUTHORIZED_FUNCTION_TYPE_CONTRACT_FN -> ContractFn(InvokeContractArgs.decode(stream))
                SorobanAuthorizedFunctionType.SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_HOST_FN -> CreateContractHostFn(CreateContractArgs.decode(stream))
            }
        }
    }

}