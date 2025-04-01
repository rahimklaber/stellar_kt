// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * MessageType's original definition in the XDR file is:
 * ```
 * enum MessageType
{
ERROR_MSG = 0,
AUTH = 2,
DONT_HAVE = 3,
// GET_PEERS (4) is deprecated

PEERS = 5,

GET_TX_SET = 6, // gets a particular txset by hash
TX_SET = 7,
GENERALIZED_TX_SET = 17,

TRANSACTION = 8, // pass on a tx you have heard about

// SCP
GET_SCP_QUORUMSET = 9,
SCP_QUORUMSET = 10,
SCP_MESSAGE = 11,
GET_SCP_STATE = 12,

// new messages
HELLO = 13,

// SURVEY_REQUEST (14) removed and replaced by TIME_SLICED_SURVEY_REQUEST
// SURVEY_RESPONSE (15) removed and replaced by TIME_SLICED_SURVEY_RESPONSE

SEND_MORE = 16,
SEND_MORE_EXTENDED = 20,

FLOOD_ADVERT = 18,
FLOOD_DEMAND = 19,

TIME_SLICED_SURVEY_REQUEST = 21,
TIME_SLICED_SURVEY_RESPONSE = 22,
TIME_SLICED_SURVEY_START_COLLECTING = 23,
TIME_SLICED_SURVEY_STOP_COLLECTING = 24
};
 * ```
 */
enum class MessageType(val value: Int) : XdrElement {
    ERROR_MSG(0),
    AUTH(2),
    DONT_HAVE(3),
    PEERS(5),
    GET_TX_SET(6),
    TX_SET(7),
    GENERALIZED_TX_SET(17),
    TRANSACTION(8),
    GET_SCP_QUORUMSET(9),
    SCP_QUORUMSET(10),
    SCP_MESSAGE(11),
    GET_SCP_STATE(12),
    HELLO(13),
    SEND_MORE(16),
    SEND_MORE_EXTENDED(20),
    FLOOD_ADVERT(18),
    FLOOD_DEMAND(19),
    TIME_SLICED_SURVEY_REQUEST(21),
    TIME_SLICED_SURVEY_RESPONSE(22),
    TIME_SLICED_SURVEY_START_COLLECTING(23),
    TIME_SLICED_SURVEY_STOP_COLLECTING(24);

    companion object : XdrElementDecoder<MessageType> {
        override fun decode(stream: XdrInputStream): MessageType {
            return when (val value = stream.readInt()) {
                0 -> ERROR_MSG
                2 -> AUTH
                3 -> DONT_HAVE
                5 -> PEERS
                6 -> GET_TX_SET
                7 -> TX_SET
                17 -> GENERALIZED_TX_SET
                8 -> TRANSACTION
                9 -> GET_SCP_QUORUMSET
                10 -> SCP_QUORUMSET
                11 -> SCP_MESSAGE
                12 -> GET_SCP_STATE
                13 -> HELLO
                16 -> SEND_MORE
                20 -> SEND_MORE_EXTENDED
                18 -> FLOOD_ADVERT
                19 -> FLOOD_DEMAND
                21 -> TIME_SLICED_SURVEY_REQUEST
                22 -> TIME_SLICED_SURVEY_RESPONSE
                23 -> TIME_SLICED_SURVEY_START_COLLECTING
                24 -> TIME_SLICED_SURVEY_STOP_COLLECTING
                else -> throw IllegalArgumentException("Unknown enum value: " + value)
            }
        }
    }

    override fun encode(stream: XdrOutputStream) {
        stream.writeInt(value)
    }
}
