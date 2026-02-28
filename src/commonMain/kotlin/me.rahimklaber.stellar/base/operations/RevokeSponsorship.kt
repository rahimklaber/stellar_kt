package me.rahimklaber.stellar.base.operations

sealed interface RevokeSponsorship : Operation {
    data class RevokeSponsorShipLedgerKey(override val sourceAccount: String? = null) : RevokeSponsorship {
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
            TODO("Not yet implemented")
        }
    }

    data class RevokeSponsorShipSigner(override val sourceAccount: String? = null) : RevokeSponsorship {
        override fun toXdr(): me.rahimklaber.stellar.base.xdr.Operation {
            TODO("Not yet implemented")
        }
    }
}

