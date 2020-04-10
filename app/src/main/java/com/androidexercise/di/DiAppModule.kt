package com.androidexercise.di

import android.content.Context
import com.androidexercise.application.Application
import com.androidexercise.repository.repositoryRepository
import com.androidexercise.retrofit.ApiService


import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class, NetworkModule::class])
class DiAppModule {

    @Provides
    @Singleton
    fun provideAppContext(application: Application): Context {
        return application.applicationContext
    }


    @Provides
    @Singleton
    fun provideRepo(apiService: ApiService): repositoryRepository {
        return repositoryRepository(apiService)
    }

}

