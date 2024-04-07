package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union InvokeHostFunctionResult switch (InvokeHostFunctionResultCode code)
//{
//case INVOKE_HOST_FUNCTION_SUCCESS:
//    Hash success; // sha256(InvokeHostFunctionSuccessPreImage)
//case INVOKE_HOST_FUNCTION_MALFORMED:
//case INVOKE_HOST_FUNCTION_TRAPPED:
//case INVOKE_HOST_FUNCTION_RESOURCE_LIMIT_EXCEEDED:
//case INVOKE_HOST_FUNCTION_ENTRY_ARCHIVED:
//case INVOKE_HOST_FUNCTION_INSUFFICIENT_REFUNDABLE_FEE:
//    void;
//};
///////////////////////////////////////////////////////////////////////////
sealed class InvokeHostFunctionResult(val code: InvokeHostFunctionResultCode): XdrElement {
    override fun encode(stream: XdrStream) {
        code.encode(stream)
    }

    data class Success(val success: Hash): InvokeHostFunctionResult(InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_SUCCESS){
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            success.encode(stream)
        }
    }
    data object Malformed: InvokeHostFunctionResult(InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_MALFORMED)
    data object Trapped: InvokeHostFunctionResult(InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_TRAPPED)
    data object ResourceLimitExceeded: InvokeHostFunctionResult(InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_RESOURCE_LIMIT_EXCEEDED)
    data object EntryArchived: InvokeHostFunctionResult(InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_ENTRY_ARCHIVED)
    data object InsufficientRefundableFee: InvokeHostFunctionResult(InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_INSUFFICIENT_REFUNDABLE_FEE)

    companion object: XdrElementDecoder<InvokeHostFunctionResult> {
        override fun decode(stream: XdrStream): InvokeHostFunctionResult {
            val code = InvokeHostFunctionResultCode.decode(stream)

            return when(code){
                InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_SUCCESS -> Success(Hash.decode(stream))
                InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_MALFORMED -> Malformed
                InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_TRAPPED -> Trapped
                InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_RESOURCE_LIMIT_EXCEEDED -> ResourceLimitExceeded
                InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_ENTRY_ARCHIVED -> EntryArchived
                InvokeHostFunctionResultCode.INVOKE_HOST_FUNCTION_INSUFFICIENT_REFUNDABLE_FEE -> InsufficientRefundableFee
            }
        }
    }
}
