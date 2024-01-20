package me.rahimklaber.stellar.base.xdr.sorobanspec

import me.rahimklaber.stellar.base.xdr.*

data class SCSpecUDTEnumCaseV0(
    val doc: String,
    val name: String,
    val value: UInt,
): XdrElement{
    init {
        require(doc.length <= SC_SPEC_DOC_LIMIT){"doc has size limit"}
        require(name.length <= 60){"name has size limit"}
    }

    override fun encode(stream: XdrStream) {
        doc.encode(stream)
        name.encode(stream)
        stream.writeInt(value.toInt())
    }

    companion object: XdrElementDecoder<SCSpecUDTEnumCaseV0> {
        override fun decode(stream: XdrStream): SCSpecUDTEnumCaseV0 {
            val doc = String.decode(stream)
            val name = String.decode(stream)
            val value = stream.readInt().toUInt()

            return SCSpecUDTEnumCaseV0(doc, name, value)
        }
    }
}
