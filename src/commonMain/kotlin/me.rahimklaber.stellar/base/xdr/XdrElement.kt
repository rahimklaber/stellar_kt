package me.rahimklaber.stellar.base.xdr

interface XdrElement {
    fun encode(stream: XdrStream)
}

interface XdrElementDecoder<T: XdrElement>{
    fun decode(stream: XdrStream) : T
}