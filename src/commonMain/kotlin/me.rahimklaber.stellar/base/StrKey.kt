package me.rahimklaber.stellar.base

import io.matthewnelson.encoding.builders.Base32Default
import io.matthewnelson.encoding.core.Decoder.Companion.decodeToByteArray
import io.matthewnelson.encoding.core.Encoder.Companion.encodeToCharArray
import me.rahimklaber.stellar.base.xdr.*
import me.rahimklaber.stellar.base.xdr.MuxedAccount
import okio.Buffer

//This file was ported from the java sdk
enum class VersionByte(// C
    private val value: Byte
) {
    ACCOUNT_ID((6 shl 3).toByte()),// G
    MUXED((12 shl 3).toByte()),// M
    SEED((18 shl 3).toByte()),// S
    PRE_AUTH_TX((19 shl 3).toByte()),// T
    SHA256_HASH((23 shl 3).toByte()),// X
    SIGNED_PAYLOAD((15 shl 3).toByte()),// P
    CONTRACT((2 shl 3).toByte()); //C

    fun getValue(): Int {
        return value.toInt()
    }

    companion object {
        fun findByValue(value: Byte): VersionByte? {
            for (versionByte in values()) {
                if (value == versionByte.value) {
                    return versionByte
                }
            }
            return null
        }
    }
}

private val b32Table by lazy {
    val table = ByteArray(256)
    for (i in 0..255) {
        table[i] = 0xff.toByte()
    }
    val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567"
    for (i in alphabet.indices) {
        table[alphabet[i].code] = i.toByte()
    }
    table
}

private val base32Encoding = Base32Default {
    lineBreakInterval = 64
    encodeToLowercase = false
    padEncoded = false
}
//doing it like this to add some context
object StrKey

fun StrKey.encodeToAccountIDXDR(account: String) : AccountID {
    return AccountID(PublicKey.PublicKeyEd25519(decodeAccountId(account).toUint256()))
}

/**
 * Encode either a AccountId or MuxedAccountId to an XDR object
 */
fun StrKey.encodeToMuxedAccountXDR(account: String): MuxedAccount {

    return when(decodeVersionByte(account)){
        VersionByte.ACCOUNT_ID -> MuxedAccount.Ed25519(decodeAccountId(account).toUint256())
        VersionByte.MUXED -> {
            val stream = XdrStream()

            stream.writeBytes(decodeMuxedAccountId(account))

            val muxedAccount = MuxedAccount.decode(stream)
            require(muxedAccount is MuxedAccount.MuxedEd25519){"should be muxed"}
            muxedAccount
        }
        else -> throw IllegalArgumentException("could not decode $account as MuxedAccountXDR")

    }
}
fun StrKey.decodeAccountId(account: String) : ByteArray{
    return decodeCheck(VersionByte.ACCOUNT_ID, account.toCharArray())
}

fun StrKey.decodeMuxedAccountId(account: String) : ByteArray{
    return decodeCheck(VersionByte.MUXED, account.toCharArray())
}

fun StrKey.decodeSecretSeed(secretSeed: String): ByteArray{
    return decodeCheck(VersionByte.SEED, secretSeed.toCharArray())
}

fun StrKey.encodeAccountId(pubkey: ByteArray): String {
    return encodeCheck(VersionByte.ACCOUNT_ID, pubkey)
}

fun StrKey.decodeVersionByte(data: String): VersionByte? {
    val byte = data.decodeToByteArray(base32Encoding)[0]

    return VersionByte.findByValue(byte)
}

fun StrKey.decodeCheck(versionByte: VersionByte, encoded: CharArray): ByteArray {
    val bytes = ByteArray(encoded.size)
    for (i in encoded.indices) {
        bytes[i] = encoded[i].code.toByte()
    }

    // The minimal binary decoded length is 3 bytes (version byte and 2-byte CRC) which,
    // in unpadded base32 (since each character provides 5 bits) corresponds to ceiling(8*3/5) = 5
    if (bytes.size < 5) {
        throw IllegalArgumentException("Encoded char array must have a length of at least 5.")
    }
    val leftoverBits = bytes.size * 5 % 8
    // 1. Make sure there is no full unused leftover byte at the end
    //   (i.e. there shouldn't be 5 or more leftover bits)
    if (leftoverBits >= 5) {
        throw IllegalArgumentException("Encoded char array has leftover character.")
    }
    if (leftoverBits > 0) {
        val lastChar = bytes[bytes.size - 1]
        val decodedLastChar: Byte = b32Table[lastChar.toInt()]
        val leftoverBitsMask = (0x0f shr 4 - leftoverBits).toByte()
        if (decodedLastChar.toInt() and leftoverBitsMask.toInt() != 0) {
            throw IllegalArgumentException("Unused bits should be set to 0.")
        }
    }
    val decoded: ByteArray = encoded.decodeToByteArray(base32Encoding)
    val decodedVersionByte = decoded[0]
    val payload: ByteArray = decoded.copyOfRange(0, decoded.size - 2)
    val data: ByteArray = payload.copyOfRange(1, payload.size)
    val checksum: ByteArray = decoded.copyOfRange(decoded.size - 2, decoded.size)
    if (decodedVersionByte.toInt() != versionByte.getValue()) {
        throw Exception("Version byte is invalid")
    }
    val expectedChecksum: ByteArray = calculateChecksum(payload)
    if (!expectedChecksum.contentEquals(checksum)) {
        throw Exception("Checksum invalid")
    }
    if (VersionByte.SEED.getValue() == decodedVersionByte.toInt()) {
//        java.util.Arrays.fill(bytes, 0.toByte())
//        java.util.Arrays.fill(decoded, 0.toByte())
//        java.util.Arrays.fill(payload, 0.toByte())
        //todo
    }
    return data
}

fun StrKey.encodeCheck(versionByte: VersionByte, data: ByteArray): String {
        val outputStream = Buffer()
        outputStream.writeByte(versionByte.getValue())
        outputStream.write(data)
        val payload: ByteArray = outputStream.readByteArray()
        val checksum: ByteArray = calculateChecksum(payload)
        outputStream.write(payload)
        outputStream.write(checksum)
        val unencoded: ByteArray = outputStream.readByteArray()
//        if (VersionByte.SEED !== versionByte) {
            return unencoded.encodeToCharArray(base32Encoding).concatToString()
//            return base32Encoding.encode(unencoded).toCharArray()
//        }
                //todo
//        // Why not use base32Encoding.encode here?
//        // We don't want secret seed to be stored as String in memory because of security reasons. It's impossible
//        // to erase it from memory when we want it to be erased (ASAP).
//        val charArrayWriter = CharArrayWriter(unencoded.size)
//        val charOutputStream: java.io.OutputStream = base32Encoding.encodingStream(charArrayWriter)
//        charOutputStream.write(unencoded)
//        val charsEncoded: CharArray = charArrayWriter.toCharArray()
//        java.util.Arrays.fill(unencoded, 0.toByte())
//        java.util.Arrays.fill(payload, 0.toByte())
//        java.util.Arrays.fill(checksum, 0.toByte())
//
//        // Clean charArrayWriter internal buffer
//        val bufferSize: Int = charArrayWriter.size()
//        val zeros = CharArray(bufferSize)
//        java.util.Arrays.fill(zeros, '0')
//        charArrayWriter.reset()
//        charArrayWriter.write(zeros)
//        charsEncoded
}

private fun calculateChecksum(bytes: ByteArray): ByteArray {
    // This code calculates CRC16-XModem checksum
    // Ported from https://github.com/alexgorbatchev/node-crc
    var crc = 0x0000
    var count = bytes.size
    var i = 0
    var code: Int
    while (count > 0) {
        code = crc ushr 8 and 0xFF
        code = code xor (bytes[i++].toInt() and 0xFF)
        code = code xor (code ushr 4)
        crc = crc shl 8 and 0xFFFF
        crc = crc xor code
        code = code shl 5 and 0xFFFF
        crc = crc xor code
        code = code shl 7 and 0xFFFF
        crc = crc xor code
        count--
    }

    // little-endian
    return byteArrayOf(crc.toByte(), (crc ushr 8).toByte())
}