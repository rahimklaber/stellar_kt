// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * Memo's original definition in the XDR file is:
 * ```
 * union Memo switch (MemoType type)
{
case MEMO_NONE:
void;
case MEMO_TEXT:
string text<28>;
case MEMO_ID:
uint64 id;
case MEMO_HASH:
Hash hash; // the hash of what to pull from the content server
case MEMO_RETURN:
Hash retHash; // the hash of the tx you are rejecting
};
 * ```
 */
sealed class Memo(val type: MemoType) : XdrElement {
    data object None : Memo(MemoType.MEMO_NONE) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
        }
    }

    fun textOrNull(): Text? = if (this is Text) this else null
    data class Text(
        val text: String,
    ) : Memo(MemoType.MEMO_TEXT) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            val textSize = text.length
            stream.writeInt(textSize)
            stream.writeBytes(text.encodeToByteArray())
        }
    }

    fun idOrNull(): Id? = if (this is Id) this else null
    data class Id(
        val id: Uint64,
    ) : Memo(MemoType.MEMO_ID) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            id.encode(stream)
        }
    }

    fun hashOrNull(): Hash? = if (this is Hash) this else null
    data class Hash(
        val hash: me.rahimklaber.stellar.base.xdr.Hash,
    ) : Memo(MemoType.MEMO_HASH) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            hash.encode(stream)
        }
    }

    fun retHashOrNull(): Return? = if (this is Return) this else null
    data class Return(
        val retHash: me.rahimklaber.stellar.base.xdr.Hash,
    ) : Memo(MemoType.MEMO_RETURN) {
        override fun encode(stream: XdrOutputStream) {
            type.encode(stream)
            retHash.encode(stream)
        }
    }

    companion object : XdrElementDecoder<Memo> {
        override fun decode(stream: XdrInputStream): Memo {
            return when (val type = MemoType.decode(stream)) {
                MemoType.MEMO_NONE -> None
                MemoType.MEMO_TEXT -> {
                    val textSize = stream.readInt()
                    val text = decodeString(textSize, stream)
                    Text(text)
                }

                MemoType.MEMO_ID -> {
                    val id = Uint64.decode(stream)
                    Id(id)
                }

                MemoType.MEMO_HASH -> {
                    val hash = me.rahimklaber.stellar.base.xdr.Hash.decode(stream)
                    Hash(hash)
                }

                MemoType.MEMO_RETURN -> {
                    val retHash = me.rahimklaber.stellar.base.xdr.Hash.decode(stream)
                    Return(retHash)
                }

                else -> throw IllegalArgumentException("unknown type: $type")
            }
        }
    }
}
