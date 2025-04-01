// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * HostFunctionType's original definition in the XDR file is:
 * ```
 * enum HostFunctionType
{
HOST_FUNCTION_TYPE_INVOKE_CONTRACT = 0,
HOST_FUNCTION_TYPE_CREATE_CONTRACT = 1,
HOST_FUNCTION_TYPE_UPLOAD_CONTRACT_WASM = 2,
HOST_FUNCTION_TYPE_CREATE_CONTRACT_V2 = 3
};
 * ```
 */
enum class HostFunctionType(val value: Int) : XdrElement {
    HOST_FUNCTION_TYPE_INVOKE_CONTRACT(0),
    HOST_FUNCTION_TYPE_CREATE_CONTRACT(1),
    HOST_FUNCTION_TYPE_UPLOAD_CONTRACT_WASM(2),
    HOST_FUNCTION_TYPE_CREATE_CONTRACT_V2(3);

    companion object : XdrElementDecoder<HostFunctionType> {
        override fun decode(stream: XdrInputStream): HostFunctionType {
            return when (val value = stream.readInt()) {
                0 -> HOST_FUNCTION_TYPE_INVOKE_CONTRACT
                1 -> HOST_FUNCTION_TYPE_CREATE_CONTRACT
                2 -> HOST_FUNCTION_TYPE_UPLOAD_CONTRACT_WASM
                3 -> HOST_FUNCTION_TYPE_CREATE_CONTRACT_V2
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
