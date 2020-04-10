package com.androidexercise.ui.facts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.androidexercise.model.ResponseFacts
import com.androidexercise.repository.repositoryRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FactsViewModel @Inject constructor(private var repository: repositoryRepository) :
    ViewModel() {

    private var factsResponse: MediatorLiveData<ResponseFacts> = MediatorLiveData()

    fun getFacts() {
        repository.getFacts().enqueue(object : Callback<ResponseFacts> {
            override fun onFailure(call: Call<ResponseFacts>, t: Throwable) {
                factsResponse.value = null
            }

            override fun onResponse(call: Call<ResponseFacts>, response: Response<ResponseFacts>) {
                factsResponse.value = response.body()
            }

        })

    }

    fun factsObserver(): LiveData<ResponseFacts> = factsResponse
}