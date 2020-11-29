package com.paulo.myapplication.data.repository

import com.paulo.myapplication.data.utils.NetworkUtils
import com.paulo.myapplication.data.model.ResponseModel
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
