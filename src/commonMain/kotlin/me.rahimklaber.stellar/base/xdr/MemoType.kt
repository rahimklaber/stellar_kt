package me.rahimklaber.stellar.base.xdr

enum class MemoType(val value: Int): XdrElement {
    MEMO_NONE(0),
    MEMO_TEXT(1),
    MEMO_ID(2),
    MEMO_HASH(3),
    MEMO_RETURN(4);

    override fun encode(stream: XdrStream) {
        stream.writeInt(value)
    }

    companion object: XdrElementDecoder<MemoType> {
        override fun decode(stream: XdrStream): MemoType {
            return when(val value = stream.readInt()){
                MEMO_NONE.value -> MEMO_NONE
                MEMO_TEXT.value -> MEMO_TEXT
                MEMO_ID.value -> MEMO_ID
                MEMO_HASH.value -> MEMO_HASH
                MEMO_RETURN.value -> MEMO_RETURN
                else -> throw IllegalArgumentException("Could not decode MemoType for value: $value")
            }
        }
    }
}