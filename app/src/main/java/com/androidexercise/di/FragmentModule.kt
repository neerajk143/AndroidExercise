package com.androidexercise.di


import com.androidexercise.ui.facts.FactsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeFactsFragmentFragment(): FactsFragment

}