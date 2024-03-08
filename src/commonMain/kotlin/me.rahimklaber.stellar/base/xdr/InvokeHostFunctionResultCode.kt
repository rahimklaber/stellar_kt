package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// enum InvokeHostFunctionResultCode
//{
//    // codes considered as "success" for the operation
//    INVOKE_HOST_FUNCTION_SUCCESS = 0,
//
//    // codes considered as "failure" for the operation
//    INVOKE_HOST_FUNCTION_MALFORMED = -1,
//    INVOKE_HOST_FUNCTION_TRAPPED = -2,
//    INVOKE_HOST_FUNCTION_RESOURCE_LIMIT_EXCEEDED = -3,
//    INVOKE_HOST_FUNCTION_ENTRY_ARCHIVED = -4,
//    INVOKE_HOST_FUNCTION_INSUFFICIENT_REFUNDABLE_FEE = -5
//};
///////////////////////////////////////////////////////////////////////////
enum class InvokeHostFunctionResultCode(val value: Int) : XdrElement {
    INVOKE_HOST_FUNCTION_SUCCESS(0),
    INVOKE_HOST_FUNCTION_MALFORMED(-1),
    INVOKE_HOST_FUNCTION_TRAPPED(-2),
    INVOKE_HOST_FUNCTION_RESOURCE_LIMIT_EXCEEDED(-3),
    INVOKE_HOST_FUNCTION_ENTRY_ARCHIVED(-4),
    INVOKE_HOST_FUNCTION_INSUFFICIENT_REFUNDABLE_FEE(-5);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object : XdrElementDecoder<InvokeHostFunctionResultCode> {
        override fun decode(stream: XdrStream): InvokeHostFunctionResultCode {
            val value = stream.readInt()

            return entries
                .find { it.value == value }
                ?: throw IllegalArgumentException("Could not decode InvokeHostFunctionResultCode for value $value")
        }
    }
}
