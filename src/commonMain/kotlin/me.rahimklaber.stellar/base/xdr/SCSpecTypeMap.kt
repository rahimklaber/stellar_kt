// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCSpecTypeMap's original definition in the XDR file is:
 * ```
 * struct SCSpecTypeMap
{
SCSpecTypeDef keyType;
SCSpecTypeDef valueType;
};
 * ```
 */
data class SCSpecTypeMap(
    val keyType: SCSpecTypeDef,
    val valueType: SCSpecTypeDef,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        keyType.encode(stream)
        valueType.encode(stream)
    }

    companion object : XdrElementDecoder<SCSpecTypeMap> {
        override fun decode(stream: XdrInputStream): SCSpecTypeMap {
            val keyType = SCSpecTypeDef.decode(stream)
            val valueType = SCSpecTypeDef.decode(stream)
            return SCSpecTypeMap(
                keyType,
                valueType,
            )
        }
    }
}
