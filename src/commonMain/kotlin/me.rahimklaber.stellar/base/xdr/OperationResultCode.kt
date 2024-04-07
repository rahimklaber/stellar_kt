package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// /* High level Operation Result */
//enum OperationResultCode
//{
//    opINNER = 0, // inner object result is valid
//
//    opBAD_AUTH = -1,            // too few valid signatures / wrong network
//    opNO_ACCOUNT = -2,          // source account was not found
//    opNOT_SUPPORTED = -3,       // operation not supported at this time
//    opTOO_MANY_SUBENTRIES = -4, // max number of subentries already reached
//    opEXCEEDED_WORK_LIMIT = -5, // operation did too much work
//    opTOO_MANY_SPONSORING = -6  // account is sponsoring too many entries
//};
///////////////////////////////////////////////////////////////////////////
enum class OperationResultCode(val value: Int): XdrElement {
    opINNER ( 0),
    opBAD_AUTH ( -1),
    opNO_ACCOUNT ( -2),
    opNOT_SUPPORTED ( -3),
    opTOO_MANY_SUBENTRIES ( -4),
    opEXCEEDED_WORK_LIMIT ( -5),
    opTOO_MANY_SPONSORING ( -6);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<OperationResultCode>{
        override fun decode(stream: XdrStream): OperationResultCode {
            val value = stream.readInt()

            return entries
                .find { it.value == value} ?: throw IllegalArgumentException("Could not decode OperationResultcode for value: $value")
        }

    }
}