package com.milovanjakovljevic.networklayer.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("X-RapidAPI-Host", "shoes-collections.p.rapidapi.com")
                .header("X-RapidApi-Key", "7d647b9184msh5b3e1f1fbae0e80p1591a6jsnb869ef4a2d1c")
                .build()
            chain.proceed(request)
        }
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://shoes-collections.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val shoesApi: ShoesApi by lazy {
        retrofit.create(ShoesApi::class.java)
    }


}