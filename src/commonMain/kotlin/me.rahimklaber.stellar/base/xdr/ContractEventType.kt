package me.rahimklaber.stellar.base.xdr

enum class ContractEventType(val value: Int): XdrElement {
    SYSTEM(0),
    CONTRACT(1),
    DIAGNOSTIC(2);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }


    companion object: XdrElementDecoder<ContractEventType> {
        override fun decode(stream: XdrStream): ContractEventType {
            val value = stream.readInt()

            return entries
                .find { it.value == value } ?: throw IllegalArgumentException("Could not decode ContractEventType for value $value")
        }
    }

}
