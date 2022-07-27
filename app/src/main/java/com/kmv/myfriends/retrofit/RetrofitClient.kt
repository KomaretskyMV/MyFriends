package com.kmv.myfriends.retrofit

import com.kmv.myfriends.dataclasses.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://randomuser.me"

object RetrofitServices {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val retrofitClient: RetrofitClient = retrofit.create(
        RetrofitClient::class.java
    )
}

interface RetrofitClient {
    @GET("api")
    suspend fun getUsersList(@Query("results") results: Int): User
}