package com.paulo.myapplication.data.repository

import com.paulo.myapplication.data.utils.NetworkUtils
import com.paulo.myapplication.data.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsEndpoint {
    @GET("v1/public/comics")
    suspend fun obterLista(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): ResponseModel
//        @Query("characters") characters: String

    companion object {
        val endpoint: ComicsEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(
                ComicsEndpoint::class.java
            )
        }
    }
}