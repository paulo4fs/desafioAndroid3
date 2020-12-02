package com.paulo.myapplication.data.repository

import com.paulo.myapplication.data.api.ServiceRetrofit
import com.paulo.myapplication.data.api.getPublicKey
import com.paulo.myapplication.data.api.getTimeStamp

class ComicRepository {
    private val client = ComicEndpoint.endpoint

    suspend fun getComic(
        id: Int,
        ts: String = getTimeStamp(),
        apikey: String =  getPublicKey(),
        hash: String = ServiceRetrofit.getHash()
    ) = client.getComic(id, ts, apikey, hash)
}