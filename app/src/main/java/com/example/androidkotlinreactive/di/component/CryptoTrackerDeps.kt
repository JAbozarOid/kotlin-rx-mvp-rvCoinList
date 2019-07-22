package com.example.androidkotlinreactive.di.component

import com.example.androidkotlinreactive.MainActivity
import com.example.androidkotlinreactive.di.module.AppModule
import com.example.androidkotlinreactive.di.module.CryptoNetworkModule
import com.example.androidkotlinreactive.ui.home.HomeActivity
import com.example.androidkotlinreactive.ui.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

/**
 * this component is an interface that dagger will use to generate code that will do the dependency injection for us
 */
@Singleton
@Component(modules = [(AppModule::class), (CryptoNetworkModule::class)])
interface CryptoTrackerDeps {
    fun inject(mainActivity: MainActivity) // this method allows us to inject all of the things we have in modules into in main activity
    fun inject(splashActivity: SplashActivity)
    fun inject(homeActivity: HomeActivity)
}