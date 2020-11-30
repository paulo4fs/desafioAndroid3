package com.paulo.myapplication.data.repository

import com.paulo.myapplication.data.api.NetworkUtils
import com.paulo.myapplication.data.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsEndpoint {
    @GET("v1/public/comics")
    suspend fun obterLista(
        @Query("format") format: String?,
        @Query("formatType") formatType: String?,
        @Query("noVariants") noVariants: Boolean?,
        @Query("characters") characters: Int?,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): ResponseModel

    companion object {
        val endpoint: ComicsEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(
                ComicsEndpoint::class.java
            )
        }
    }
}