package me.rahimklaber.stellar.base.xdr

interface XdrElement {
    fun encode(stream: XdrStream)
}

//fun XdrElement?.encode(stream: XdrStream){
//    if (this == null){
//        stream.writeInt(0)
//    }else{
//        stream.writeInt(1)
//        this.encode(stream)
//    }
//}

//Note: this is for encoding nullable xdr fields.
//so not for example: `AccountID*`
fun XdrElement?.encodeNullable(stream: XdrStream){
    if (this == null){
        stream.writeInt(0)
    }else{
        stream.writeInt(1)
        this.encode(stream)
    }
}


interface XdrElementDecoder<T: XdrElement>{
    fun decode(stream: XdrStream) : T
}

fun <T: XdrElement> XdrElementDecoder<T>.decodeNullable(stream: XdrStream) : T? {
    return if (stream.readInt() == 1){
        decode(stream)
    }else{
        null
    }
}
