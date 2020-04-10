package com.androidexercise.repository


import com.androidexercise.model.ResponseFacts
import com.androidexercise.retrofit.ApiService
import retrofit2.Call


class repositoryRepository constructor(private val apiService: ApiService) {

    fun getFacts(): Call<ResponseFacts> {
        return apiService.getFacts()
    }

}