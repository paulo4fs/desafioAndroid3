package com.paulo.myapplication.comic.repository

import com.paulo.myapplication.comics.api.NetworkUtils
import com.paulo.myapplication.comics.model.ResponseModel
import com.paulo.myapplication.comics.repository.ComicsEndpoint
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicEndpoint {
    @GET("v1/public/comics/{id}")
    suspend fun obterItem(
        @Path("id") id: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): ResponseModel

    companion object {
        val endpoint: ComicEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(
                ComicEndpoint::class.java
            )
        }
    }
}
