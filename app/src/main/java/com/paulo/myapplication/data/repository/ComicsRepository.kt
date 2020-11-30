package com.paulo.myapplication.data.repository

import com.paulo.myapplication.data.api.ServiceRetrofit
import com.paulo.myapplication.data.api.getTimeStamp

class ComicsRepository {
    private val client = ComicsEndpoint.endpoint

    private val publickey = "6eb7e8896ec5850c52515a8a23ee97f0"

    suspend fun obterLista(
        format: String? = "comic",
        formatType: String? = "comic",
        noVariants: Boolean? = true,
        characters: Int? = 1010801,
        ts: String = getTimeStamp(),
        apikey: String = publickey,
        hash: String = ServiceRetrofit.getHash()
    ) = client.obterLista(
        format,
        formatType,
        noVariants,
        characters,
        ts,
        apikey,
        hash
    )
}