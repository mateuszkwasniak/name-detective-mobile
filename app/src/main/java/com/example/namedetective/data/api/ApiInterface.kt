package com.example.namedetective.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET(".")
    fun getData(@Query("name") name: String): Call<NameData>
}