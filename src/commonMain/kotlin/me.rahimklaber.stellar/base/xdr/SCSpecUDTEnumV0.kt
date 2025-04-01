// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * SCSpecUDTEnumV0's original definition in the XDR file is:
 * ```
 * struct SCSpecUDTEnumV0
{
string doc<SC_SPEC_DOC_LIMIT>;
string lib<80>;
string name<60>;
SCSpecUDTEnumCaseV0 cases<50>;
};
 * ```
 */
data class SCSpecUDTEnumV0(
    val doc: String,
    val lib: String,
    val name: String,
    val cases: List<SCSpecUDTEnumCaseV0>,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        val docSize = doc.length
        stream.writeInt(docSize)
        stream.writeBytes(doc.encodeToByteArray())
        val libSize = lib.length
        stream.writeInt(libSize)
        stream.writeBytes(lib.encodeToByteArray())
        val nameSize = name.length
        stream.writeInt(nameSize)
        stream.writeBytes(name.encodeToByteArray())
        val casesSize = cases.size
        stream.writeInt(casesSize)
        cases.encodeXdrElements(stream)
    }

    companion object : XdrElementDecoder<SCSpecUDTEnumV0> {
        override fun decode(stream: XdrInputStream): SCSpecUDTEnumV0 {
            val docSize = stream.readInt()
            val doc = decodeString(docSize, stream)
            val libSize = stream.readInt()
            val lib = decodeString(libSize, stream)
            val nameSize = stream.readInt()
            val name = decodeString(nameSize, stream)
            val casesSize = stream.readInt()
            val cases: List<SCSpecUDTEnumCaseV0> = decodeXdrElementsList(casesSize, stream, SCSpecUDTEnumCaseV0.decoder())
            return SCSpecUDTEnumV0(
                doc,
                lib,
                name,
                cases,
            )
        }
    }
}
