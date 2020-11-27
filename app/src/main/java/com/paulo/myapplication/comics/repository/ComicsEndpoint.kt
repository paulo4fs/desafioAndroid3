package com.paulo.myapplication.comics.repository

import com.paulo.myapplication.comics.api.NetworkUtils
import com.paulo.myapplication.comics.model.DataModel
import com.paulo.myapplication.comics.model.ResponseModel
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsEndpoint {
    @GET("v1/public/comics")
    suspend fun obterLista(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("titleStartsWith") titleStartsWith: String
    ): ResponseModel

    companion object {
        val endpoint: ComicsEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(
                ComicsEndpoint::class.java
            )
        }
    }
}