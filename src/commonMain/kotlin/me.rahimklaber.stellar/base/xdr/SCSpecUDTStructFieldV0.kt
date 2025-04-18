// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCSpecUDTStructFieldV0's original definition in the XDR file is:
 * ```
 * struct SCSpecUDTStructFieldV0
{
string doc<SC_SPEC_DOC_LIMIT>;
string name<30>;
SCSpecTypeDef type;
};
 * ```
 */
data class SCSpecUDTStructFieldV0(
    val doc: String,
    val name: String,
    val type: SCSpecTypeDef,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        val docSize = doc.length
        stream.writeInt(docSize)
        stream.writeBytes(doc.encodeToByteArray())
        val nameSize = name.length
        stream.writeInt(nameSize)
        stream.writeBytes(name.encodeToByteArray())
        type.encode(stream)
    }

    companion object : XdrElementDecoder<SCSpecUDTStructFieldV0> {
        override fun decode(stream: XdrInputStream): SCSpecUDTStructFieldV0 {
            val docSize = stream.readInt()
            val doc = decodeString(docSize, stream)
            val nameSize = stream.readInt()
            val name = decodeString(nameSize, stream)
            val type = SCSpecTypeDef.decode(stream)
            return SCSpecUDTStructFieldV0(
                doc,
                name,
                type,
            )
        }
    }
}
