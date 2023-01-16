package com.dev.gifapp.data.network

import com.dev.gifapp.data.models.GifsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface RestApiService {
    @Headers("Content-Type: application/json")
    @GET("v1/gifs/search")
    suspend fun getGifs(@QueryMap params: Map<String, String>): Response<GifsModel>
}