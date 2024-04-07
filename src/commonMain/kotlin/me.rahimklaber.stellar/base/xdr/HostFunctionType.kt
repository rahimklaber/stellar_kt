package me.rahimklaber.stellar.base.xdr

enum class HostFunctionType(val value: Int) : XdrElement {
    HOST_FUNCTION_TYPE_INVOKE_CONTRACT(0),
    HOST_FUNCTION_TYPE_CREATE_CONTRACT(1),
    HOST_FUNCTION_TYPE_UPLOAD_CONTRACT_WASM(2);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object : XdrElementDecoder<HostFunctionType> {
        override fun decode(stream: XdrStream): HostFunctionType {
            val value = stream.readInt()

            return entries
                .firstOrNull {
                    it.value == value
                } ?: throw IllegalArgumentException("Could not decode HostFunctionType for value: $value")
        }
    }

}