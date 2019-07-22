package com.example.androidkotlinreactive.ui.home

import com.example.androidkotlinreactive.extensions.safeDispose
import com.example.androidkotlinreactive.network.CryptoNetworkService
import com.example.androidkotlinreactive.network.NetworkUtils
import io.reactivex.disposables.CompositeDisposable

class HomePresenter(
    private val homeView: HomeView,
    private val service: CryptoNetworkService,
    private val networkUtils: NetworkUtils
) {
    private val disposables = CompositeDisposable()

    fun getAllCurrencies() {
        when {
            networkUtils.isConnected -> {
                homeView.updateProgressVisibility(true)
                disposables.add(service.getAllCurrency(onSuccess = { response ->
                    homeView.disablePullRefreshProgress()
                    homeView.updateProgressVisibility(false)
                    if (response.isNotEmpty()) {
                        val sortedList = response.sortedWith(compareBy {
                            it.rank?.toInt()
                        })
                        homeView.updateData(sortedList)
                    }
                }, onError = {
                    homeView.disablePullRefreshProgress()
                    homeView.updateProgressVisibility(false)
                    homeView.showError(it)
                }))
            }
            else -> {
                homeView.disablePullRefreshProgress()
                homeView.showInternetError()
            }

        }
    }

    fun onDestroy() {
        disposables.safeDispose()
    }
}