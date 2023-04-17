package com.milovanjakovljevic.networklayer.network

import com.milovanjakovljevic.networklayer.model.ShoesNetworkResponse
import retrofit2.http.GET

interface ShoesApi {
    @GET("/shoes")
    suspend fun getShoes(): List<ShoesNetworkResponse>
}