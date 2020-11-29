package com.paulo.myapplication.comics.repository

import retrofit2.http.Query

class ComicsRepository {
    private val client = ComicsEndpoint.endpoint

    suspend fun obterLista(
        ts: String,
        apikey: String,
        hash: String
    ) = client.obterLista(ts, apikey, hash)
}