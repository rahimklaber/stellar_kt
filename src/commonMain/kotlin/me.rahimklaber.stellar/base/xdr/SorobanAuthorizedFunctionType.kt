// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SorobanAuthorizedFunctionType's original definition in the XDR file is:
 * ```
 * enum SorobanAuthorizedFunctionType
{
SOROBAN_AUTHORIZED_FUNCTION_TYPE_CONTRACT_FN = 0,
SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_HOST_FN = 1,
SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_V2_HOST_FN = 2
};
 * ```
 */
enum class SorobanAuthorizedFunctionType(val value: Int) : XdrElement {
    SOROBAN_AUTHORIZED_FUNCTION_TYPE_CONTRACT_FN(0),
    SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_HOST_FN(1),
    SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_V2_HOST_FN(2);

    companion object : XdrElementDecoder<SorobanAuthorizedFunctionType> {
        override fun decode(stream: XdrInputStream): SorobanAuthorizedFunctionType {
            return when (val value = stream.readInt()) {
                0 -> SOROBAN_AUTHORIZED_FUNCTION_TYPE_CONTRACT_FN
                1 -> SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_HOST_FN
                2 -> SOROBAN_AUTHORIZED_FUNCTION_TYPE_CREATE_CONTRACT_V2_HOST_FN
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
