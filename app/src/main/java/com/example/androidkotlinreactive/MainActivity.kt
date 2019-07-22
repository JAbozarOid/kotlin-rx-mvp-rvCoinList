package com.example.androidkotlinreactive

import android.annotation.SuppressLint
import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.androidkotlinreactive.di.component.CryptoTrackerDeps
import com.example.androidkotlinreactive.di.component.DaggerCryptoTrackerDeps
import com.example.androidkotlinreactive.di.module.AppModule

@SuppressLint("Registered")
class MainActivity : Application() {

    /**
     * dagger uses our modules and the providers inside of the modules to find basically instances where those providers are needed
     */

    // create a variable for injection
    lateinit var cryptoDeps: CryptoTrackerDeps

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        cryptoDeps = DaggerCryptoTrackerDeps.builder()
            .appModule(AppModule(this))
            .build()
        cryptoDeps.inject(this)
    }
}
