package com.example.androidkotlinreactive.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// module in dagger is the provider of injection
@Module
class AppModule(private val context: Context) {
    @Provides // this function is a provider
    @Singleton // make sure that when we actually inject this function into something it will not create a new instances for each other injection afterwords
    fun providesContext(): Context {
        return context
    }
}