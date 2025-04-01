package me.rahimklaber.stellar.base.xdr

typealias Uint64 = ULong
typealias Int64 = Long
typealias Int32 = Int
typealias Uint32 = UInt

fun ULong.encode(stream: XdrOutputStream) = stream.writeLong(toLong())
fun Long.encode(stream: XdrOutputStream) = stream.writeLong(this)
fun UInt.encode(stream: XdrOutputStream) = stream.writeInt(toInt())
fun Int.encode(stream: XdrOutputStream) = stream.writeInt(this)


fun ULong.Companion.decode(stream: XdrInputStream) = stream.readLong().toULong()
fun Long.Companion.decode(stream: XdrInputStream) = stream.readLong()
fun UInt.Companion.decode(stream: XdrInputStream) = stream.readInt().toUInt()
fun Int.Companion.decode(stream: XdrInputStream) = stream.readInt()

fun Array<Uint64>.encodeXdrElements(stream: XdrOutputStream) {
    forEach { stream.writeLong(it.toLong()) }
}