package com.androidexercise.di


import com.androidexercise.ui.facts.FactsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeFactsActivity(): FactsActivity

}