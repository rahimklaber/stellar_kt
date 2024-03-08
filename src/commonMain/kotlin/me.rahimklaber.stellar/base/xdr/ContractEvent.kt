package me.rahimklaber.stellar.base.xdr

import me.rahimklaber.stellar.base.xdr.soroban.SCVal

///////////////////////////////////////////////////////////////////////////
// struct ContractEvent
//{
//    // We can use this to add more fields, or because it
//    // is first, to change ContractEvent into a union.
//    ExtensionPoint ext;
//
//    Hash* contractID;
//    ContractEventType type;
//
//    union switch (int v)
//    {
//    case 0:
//        struct
//        {
//            SCVal topics<>;
//            SCVal data;
//        } v0;
//    }
//    body;
//};
///////////////////////////////////////////////////////////////////////////
data class ContractEvent(
    val contractID: Hash?,
    val type: ContractEventType,
    val topics: List<SCVal>,
    val data: SCVal
): XdrElement {
    override fun encode(stream: XdrStream) {
        // extension point
        stream.writeInt(0)

        contractID.encodeNullable(stream)
        type.encode(stream)

        // union
        stream.writeInt(0)

        topics.encode(stream)
        data.encodeNullable(stream)
    }


    companion object: XdrElementDecoder<ContractEvent> {
        override fun decode(stream: XdrStream): ContractEvent {
            stream.readInt() // extension point

            val contractID = Hash.decodeNullable(stream)
            val type = ContractEventType.decode(stream)

            stream.readInt() // union discriminant

            val topics = decodeXdrElementList(stream, SCVal::decode)
            val data = SCVal.decode(stream)

            return ContractEvent(contractID, type, topics, data)
        }
    }
}
