package me.rahimklaber.stellar.base.xdr

interface XdrElement {
    fun encode(stream: XdrStream)
}

fun XdrElement?.encode(stream: XdrStream){
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