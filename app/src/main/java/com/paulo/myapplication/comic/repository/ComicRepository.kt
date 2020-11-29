package com.paulo.myapplication.comic.repository

import com.paulo.myapplication.comics.repository.ComicsEndpoint

class ComicRepository {
    private val client = ComicEndpoint.endpoint

    suspend fun obterItem(
        id: Int,
        ts: String,
        apikey: String,
        hash: String
    ) = client.obterItem(id, ts, apikey, hash)
}