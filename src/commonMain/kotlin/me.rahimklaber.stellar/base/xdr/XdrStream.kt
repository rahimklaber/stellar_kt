package me.rahimklaber.stellar.base.xdr

import kotlinx.io.Buffer
import kotlinx.io.readByteArray

interface XdrOutputStream {
    fun writeBytes(bytes: ByteArray)
    fun writeInt(value: Int)
    fun writeLong(value: Long)
}

interface XdrInputStream {
    fun readBytes(count: Int): ByteArray
    fun readInt(): Int
    fun readLong(): Long
    fun readAllBytes(): ByteArray
}

inline fun <T> decodeXdrElementsList(count: Int, stream: XdrInputStream, decoder: (XdrInputStream) -> T): List<T> {
    return MutableList(count) {
        decoder(stream)
    }
}

fun <T : XdrElement> XdrElementDecoder<T>.decoder(): (XdrInputStream) -> T = this::decode
fun decodeULong(stream: XdrInputStream): ULong = stream.readLong().toULong()
fun ULong.Companion.decoder(): (XdrInputStream) -> ULong = ::decodeULong

fun decodeString(count: Int, stream: XdrInputStream): String {
    return stream.readBytes(count).decodeToString()
}

fun <T : XdrElement> List<T>.encodeXdrElements(stream: XdrOutputStream) {
    forEach { it.encode(stream) }
}

fun List<ULong>.encodeXdrElementsULong(stream: XdrOutputStream) {
    forEach { stream.writeLong(it.toLong()) }
}

fun XdrOutputStream.writeBoolean(value: Boolean) = if (value) writeInt(1) else writeInt(0)
fun XdrInputStream.readBoolean() = readInt() == 1

fun xdrStream() = DefaultXdrStream(Buffer())

class DefaultXdrStream(val buffer: Buffer) : XdrOutputStream, XdrInputStream {
    override fun writeBytes(bytes: ByteArray) {
        buffer.write(bytes)

        val padAmount = (4 - (bytes.size % 4)) % 4
        pad(padAmount)
    }

    override fun writeInt(value: Int) {
        buffer.writeInt(value)
    }

    override fun writeLong(value: Long) {
        buffer.writeLong(value)
    }

    override fun readBytes(count: Int): ByteArray {
        val bytes = buffer.readByteArray(count)

        val padAmount = (4 - (count % 4)) % 4
        readPad(padAmount)
        return bytes
    }

    override fun readInt(): Int {
        return buffer.readInt()
    }

    override fun readLong(): Long {
        return buffer.readLong()
    }

    override fun readAllBytes(): ByteArray {
        return buffer.readByteArray()
    }

    private fun pad(amount: Int) {
        for (i in 0 until amount) {
            buffer.writeByte(0)
        }
    }

    private fun readPad(amount: Int) {
        buffer.skip(amount.toLong())
    }
}