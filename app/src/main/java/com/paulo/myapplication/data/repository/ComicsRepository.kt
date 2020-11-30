package com.paulo.myapplication.data.repository

import com.paulo.myapplication.data.api.ServiceRetrofit
import com.paulo.myapplication.data.api.getPublicKey
import com.paulo.myapplication.data.api.getTimeStamp

class ComicsRepository {
    private val client = ComicsEndpoint.endpoint

    suspend fun getAllComics(
        format: String? = "comic",
        formatType: String? = "comic",
        noVariants: Boolean? = true,
        characters: Int? = 1010801,
        ts: String = getTimeStamp(),
        apikey: String = getPublicKey(),
        hash: String = ServiceRetrofit.getHash()
    ) = client.getAllComics(
        format,
        formatType,
        noVariants,
        characters,
        ts,
        apikey,
        hash
    )
}