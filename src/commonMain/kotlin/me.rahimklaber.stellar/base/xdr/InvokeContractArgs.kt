package me.rahimklaber.stellar.base.xdr

import me.rahimklaber.stellar.base.xdr.soroban.SCSymbol
import me.rahimklaber.stellar.base.xdr.soroban.SCVal
import me.rahimklaber.stellar.base.xdr.soroban.ScAddress

data class InvokeContractArgs(
    val contractAddress: ScAddress,
    val functionName: SCSymbol,
    val args: List<SCVal>
): XdrElement {
    override fun encode(stream: XdrStream) {
        contractAddress.encode(stream)
        functionName.encode(stream)
        args.encode(stream)
    }

    companion object: XdrElementDecoder<InvokeContractArgs> {
        override fun decode(stream: XdrStream): InvokeContractArgs {
            return InvokeContractArgs(
                ScAddress.decode(stream),
                SCSymbol.decode(stream),
                decodeXdrElementList(stream, SCVal::decode)
            )
        }
    }
}
