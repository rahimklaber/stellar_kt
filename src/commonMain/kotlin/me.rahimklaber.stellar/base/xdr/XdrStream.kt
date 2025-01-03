package me.rahimklaber.stellar.base.xdr

import kotlinx.io.Buffer
import kotlinx.io.readByteArray

class XdrDecodeException(message: String): Exception(message)

fun xdrDecodeError(message: String): Nothing = throw XdrDecodeException(message)

interface IXdrStream{
    //only write the lower 8 bits
//    fun writeByte(value: Int)
//    fun writeByte(value: Byte)

    fun writeInt(value: Int)
    fun writeLong(value:Long)
    fun writeULong(value: ULong)

    fun writeBytes(bytes: ByteArray)

    fun readLong(): Long
    fun readULong(): ULong
    fun readInt() : Int
    fun readByte(): Byte

    fun readBytes(length: Int) : ByteArray
    fun readAllBytes() : ByteArray
}

fun IXdrStream.readIntNullable(): Int? {
    return if (readInt() == 1){
        readInt()
    }else{
        null
    }
}

fun IXdrStream.readLongNullable(): Long? {
    return if (readInt() == 1){
        readLong()
    }else{
        null
    }
}

fun IXdrStream.writeIntNullable(value: Int?){
    if(value == null){
        writeInt(0)
    }else{
        writeInt(1)
        writeInt(value)
    }
}

fun IXdrStream.writeLongNullable(value: Long?){
    if(value == null){
        writeInt(0)
    }else{
        writeInt(1)
        writeLong(value)
    }
}


class XdrStream : IXdrStream{
    val buffer = Buffer()
//    override fun writeByte(value: Int) {
//        buffer.writeByte(value)
//    }
//
//    override fun writeByte(value: Byte) {
//        buffer.writeByte(value.toInt())
//    }

    override fun writeInt(value: Int) {
        buffer.writeInt(value)
    }

    override fun writeLong(value: Long) {
        buffer.writeLong(value)
    }

    override fun writeULong(value: ULong) {
        writeLong(value.toLong())
    }

    override fun writeBytes(bytes: ByteArray) {
        buffer.write(bytes)

        val padAmount = (4 -( bytes.size % 4)) % 4
        pad(padAmount)
    }

    override fun readLong(): Long = buffer.readLong()

    override fun readULong(): ULong = buffer.readLong().toULong()

    override fun readInt(): Int = buffer.readInt()

    override fun readByte(): Byte = buffer.readByte()

    override fun readBytes(length: Int): ByteArray {
        val byteBuffer = buffer.readByteArray(length)

        val padAmount = (4 -( length % 4)) % 4
        readPad(padAmount)
        return byteBuffer
    }

    override fun readAllBytes(): ByteArray {
        return buffer.readByteArray()
    }

    private fun pad(amount: Int){
        for (i in 0 until amount){
            buffer.writeByte(0)
        }
    }

    private fun readPad(amount: Int){
        for (i in 0 until amount){
            buffer.readByte()
        }
    }
}

