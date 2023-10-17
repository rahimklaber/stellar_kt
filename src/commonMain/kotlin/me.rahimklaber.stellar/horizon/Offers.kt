package me.rahimklaber.stellar.horizon

import io.ktor.client.*

data class OfferResponse(val a :String )
class OffersRequestBuilder(client: HttpClient, horizonUrl: String) :
    RequestBuilder<OfferResponse>(client, horizonUrl, "offers") {


    override suspend fun callAsync(): RequestResult<Page<OfferResponse>> {
        TODO("Not yet implemented")
    }


}