package com.saralpe.app.retrofit

import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    @POST("apes_register")
    fun aepsRegister(@Body body: RequestBody): Call<JsonObject>

    @POST("apes_authentication")
    fun aepsAuthentication(@Body body: RequestBody): Call<JsonObject>
}