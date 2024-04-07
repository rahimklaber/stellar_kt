package me.rahimklaber.stellar.base.xdr

data class DiagnosticEvent(
    val inSuccessfulContractCall: Boolean,
    val event: ContractEvent
): XdrElement {
    override fun encode(stream: XdrStream) {
        if(inSuccessfulContractCall){
            stream.writeInt(1)
        }else{
            stream.writeInt(0)
        }

        event.encode(stream)
    }

    companion object: XdrElementDecoder<DiagnosticEvent> {
        override fun decode(stream: XdrStream): DiagnosticEvent {
            return DiagnosticEvent(
                stream.readInt() == 1,
                ContractEvent.decode(stream)
            )
        }
    }

}
