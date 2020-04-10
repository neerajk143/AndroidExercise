package com.androidexercise.application

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import androidx.appcompat.app.AppCompatDelegate
import com.androidexercise.di.DaggerDiAppComponent


class Application : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerDiAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {
        private var instance: Application? = null
        val appContext: Context
            get() = instance!!.applicationContext
    }
}
