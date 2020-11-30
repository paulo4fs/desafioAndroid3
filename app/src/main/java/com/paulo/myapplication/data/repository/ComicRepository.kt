package com.paulo.myapplication.data.repository

import com.paulo.myapplication.data.api.ServiceRetrofit
import com.paulo.myapplication.data.api.getTimeStamp

class ComicRepository {
    private val client = ComicEndpoint.endpoint

    private val publickey = "6eb7e8896ec5850c52515a8a23ee97f0"

    suspend fun obterItem(
        id: Int,
        ts: String = getTimeStamp(),
        apikey: String = publickey,
        hash: String = ServiceRetrofit.getHash()
    ) = client.obterItem(id, ts, apikey, hash)
}