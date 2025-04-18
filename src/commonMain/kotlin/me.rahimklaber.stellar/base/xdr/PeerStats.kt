// Automatically generated by xdrgen
// DO NOT EDIT or your changes may be overwritten

package me.rahimklaber.stellar.base.xdr


/**
 * PeerStats's original definition in the XDR file is:
 * ```
 * struct PeerStats
{
NodeID id;
string versionStr<100>;
uint64 messagesRead;
uint64 messagesWritten;
uint64 bytesRead;
uint64 bytesWritten;
uint64 secondsConnected;

uint64 uniqueFloodBytesRecv;
uint64 duplicateFloodBytesRecv;
uint64 uniqueFetchBytesRecv;
uint64 duplicateFetchBytesRecv;

uint64 uniqueFloodMessageRecv;
uint64 duplicateFloodMessageRecv;
uint64 uniqueFetchMessageRecv;
uint64 duplicateFetchMessageRecv;
};
 * ```
 */
data class PeerStats(
    val id: NodeID,
    val versionStr: String,
    val messagesRead: Uint64,
    val messagesWritten: Uint64,
    val bytesRead: Uint64,
    val bytesWritten: Uint64,
    val secondsConnected: Uint64,
    val uniqueFloodBytesRecv: Uint64,
    val duplicateFloodBytesRecv: Uint64,
    val uniqueFetchBytesRecv: Uint64,
    val duplicateFetchBytesRecv: Uint64,
    val uniqueFloodMessageRecv: Uint64,
    val duplicateFloodMessageRecv: Uint64,
    val uniqueFetchMessageRecv: Uint64,
    val duplicateFetchMessageRecv: Uint64,
) : XdrElement {
    override fun encode(stream: XdrOutputStream) {
        id.encode(stream)
        val versionStrSize = versionStr.length
        stream.writeInt(versionStrSize)
        stream.writeBytes(versionStr.encodeToByteArray())
        messagesRead.encode(stream)
        messagesWritten.encode(stream)
        bytesRead.encode(stream)
        bytesWritten.encode(stream)
        secondsConnected.encode(stream)
        uniqueFloodBytesRecv.encode(stream)
        duplicateFloodBytesRecv.encode(stream)
        uniqueFetchBytesRecv.encode(stream)
        duplicateFetchBytesRecv.encode(stream)
        uniqueFloodMessageRecv.encode(stream)
        duplicateFloodMessageRecv.encode(stream)
        uniqueFetchMessageRecv.encode(stream)
        duplicateFetchMessageRecv.encode(stream)
    }

    companion object : XdrElementDecoder<PeerStats> {
        override fun decode(stream: XdrInputStream): PeerStats {
            val id = NodeID.decode(stream)
            val versionStrSize = stream.readInt()
            val versionStr = decodeString(versionStrSize, stream)
            val messagesRead = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val messagesWritten = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val bytesRead = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val bytesWritten = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val secondsConnected = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val uniqueFloodBytesRecv = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val duplicateFloodBytesRecv = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val uniqueFetchBytesRecv = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val duplicateFetchBytesRecv = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val uniqueFloodMessageRecv = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val duplicateFloodMessageRecv = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val uniqueFetchMessageRecv = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            val duplicateFetchMessageRecv = me.rahimklaber.stellar.base.xdr.Uint64.decode(stream)
            return PeerStats(
                id,
                versionStr,
                messagesRead,
                messagesWritten,
                bytesRead,
                bytesWritten,
                secondsConnected,
                uniqueFloodBytesRecv,
                duplicateFloodBytesRecv,
                uniqueFetchBytesRecv,
                duplicateFetchBytesRecv,
                uniqueFloodMessageRecv,
                duplicateFloodMessageRecv,
                uniqueFetchMessageRecv,
                duplicateFetchMessageRecv,
            )
        }
    }
}
