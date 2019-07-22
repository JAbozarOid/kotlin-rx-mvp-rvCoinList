package com.example.androidkotlinreactive.ui.splash

import com.example.androidkotlinreactive.network.NetworkUtils

class SplashPresenter(private val splashView: SplashView, private val networkUtils: NetworkUtils) {
    fun checkRequirement() {
        when {
            networkUtils.isConnected -> {
                splashView.navigateToHome()
            }
            else -> splashView.showInternetError()
        }
    }
}