package com.saralpe.app.retrofit

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiRepository {

    var apiInterface: ApiServices = RetrofitClientApi.createService(ApiServices::class.java)

    companion object {
        private var apiRepository: ApiRepository? = null
        fun getInstance(mContext: Context): ApiRepository {
            if (apiRepository == null) {
                apiRepository = ApiRepository()
            }
            return apiRepository!!
        }
    }


    // GET ALL THE ACTIVE LANGUAGES FROM SERVER
    fun aepsRegister(cus_id: String,
                     adhaarNumber: String,
                     mobileNumber: String,txtPidData: String): MutableLiveData<JsonObject?> {
        val res = MutableLiveData<JsonObject?>()
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("cus_id", cus_id)
            .addFormDataPart("adhaarnumber", adhaarNumber)
            .addFormDataPart("mobilenumber", mobileNumber)
            .addFormDataPart("txtPidData", txtPidData)
            .build()
        apiInterface.aepsRegister(requestBody).enqueue(object : Callback<JsonObject> {
            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                res.postValue(response.body())
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                res.postValue(null)
            }
        })
        return res
    }

    fun aepsAuthentication(cus_id: String,
                     adhaarNumber: String,
                     mobileNumber: String,txtPidData: String): MutableLiveData<JsonObject?> {
        val res = MutableLiveData<JsonObject?>()
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("cus_id", cus_id)
            .addFormDataPart("adhaarnumber", adhaarNumber)
            .addFormDataPart("mobilenumber", mobileNumber)
            .addFormDataPart("txtPidData", txtPidData)
            .build()
        apiInterface.aepsAuthentication(requestBody).enqueue(object : Callback<JsonObject> {
            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                res.postValue(response.body())
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                res.postValue(null)
            }
        })
        return res
    }
}