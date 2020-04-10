package com.androidexercise.retrofit

import com.androidexercise.model.ResponseFacts
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET(ApiEndPoint.FACTS)
    fun getFacts(): Call<ResponseFacts>
}