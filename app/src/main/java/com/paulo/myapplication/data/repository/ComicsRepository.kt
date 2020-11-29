package com.paulo.myapplication.data.repository

class ComicsRepository {
    private val client = ComicsEndpoint.endpoint

    suspend fun obterLista(
        ts: String,
        apikey: String,
        hash: String
    ) = client.obterLista(ts, apikey, hash)
}