package me.rahimklaber.stellar.base.xdr

data object ExtensionPoint : XdrElement, XdrElementDecoder<ExtensionPoint> {
    override fun encode(stream: XdrStream) {
        stream.writeInt(0)
    }

    override fun decode(stream: XdrStream): ExtensionPoint {
        require(stream.readInt() == 0)

        return ExtensionPoint
    }
}