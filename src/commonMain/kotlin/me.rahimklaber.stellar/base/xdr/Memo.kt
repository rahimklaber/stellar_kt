package me.rahimklaber.stellar.base.xdr

///////////////////////////////////////////////////////////////////////////
// union Memo switch (MemoType type)
//{
//case MEMO_NONE:
//    void;
//case MEMO_TEXT:
//    string text<28>;
//case MEMO_ID:
//    uint64 id;
//case MEMO_HASH:
//    Hash hash; // the hash of what to pull from the content server
//case MEMO_RETURN:
//    Hash retHash; // the hash of the tx you are rejecting
//};
///////////////////////////////////////////////////////////////////////////
sealed class Memo(val type: MemoType) : XdrElement {
    override fun encode(stream: XdrStream) {
        type.encode(stream)
    }

    object None : Memo(MemoType.MEMO_NONE)
    data class Text(val text: String32/*todo: its actually 28 and not 32 chars*/) : Memo(MemoType.MEMO_TEXT) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            text.encode(stream)
        }
    }

    data class Id(val id: ULong) : Memo(MemoType.MEMO_ID) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            stream.writeLong(id.toLong())
        }
    }

    data class Hash(val hash: me.rahimklaber.stellar.base.xdr.Hash) : Memo(MemoType.MEMO_HASH) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            hash.encode(stream)
        }
    }

    data class Return(val retHash: me.rahimklaber.stellar.base.xdr.Hash) : Memo(MemoType.MEMO_RETURN) {
        override fun encode(stream: XdrStream) {
            super.encode(stream)
            retHash.encode(stream)
        }
    }

    companion object : XdrElementDecoder<Memo> {
        override fun decode(stream: XdrStream): Memo {
            return when (val type = MemoType.decode(stream)) {
                MemoType.MEMO_NONE -> None
                MemoType.MEMO_TEXT -> Text(String32.decode(stream))
                MemoType.MEMO_ID -> Id(stream.readLong().toULong())
                MemoType.MEMO_HASH -> Hash(me.rahimklaber.stellar.base.xdr.Hash.decode(stream))
                MemoType.MEMO_RETURN -> Return(me.rahimklaber.stellar.base.xdr.Hash.decode(stream))
            }
        }
    }
}
