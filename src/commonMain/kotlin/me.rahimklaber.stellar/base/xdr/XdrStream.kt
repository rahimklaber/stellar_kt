package me.rahimklaber.stellar.base.xdr

import okio.Buffer
import okio.BufferedSink


interface XdrOutputStream{
    //only write the lower 8 bits
//    fun writeByte(value: Int)
//    fun writeByte(value: Byte)

    fun writeInt(value: Int)

    fun writeBytes(bytes: ByteArray)

    fun readInt() : Int
    fun readByte(): Byte

    fun readBytes(length: Int) : ByteArray
}

class XdrStream : XdrOutputStream{
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

    override fun writeBytes(bytes: ByteArray) {
        buffer.write(bytes)
        pad(bytes.size % 4)
    }

    override fun readInt(): Int = buffer.readInt()

    override fun readByte(): Byte {
        TODO("Not yet implemented")
    }

    override fun readBytes(length: Int): ByteArray {
        val byteBuffer = ByteArray(length)
        buffer.read(byteBuffer,0,length)
        return byteBuffer
    }

    private fun pad(amount: Int){
        for (i in 0 until amount){
            buffer.writeByte(0)
        }
    }
}

