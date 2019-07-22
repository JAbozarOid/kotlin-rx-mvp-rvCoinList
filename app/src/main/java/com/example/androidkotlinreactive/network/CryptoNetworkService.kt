package com.example.androidkotlinreactive.network

import com.example.androidkotlinreactive.extensions.uiSubscribe
import com.example.androidkotlinreactive.model.CurrencyModel
import io.reactivex.disposables.Disposable

class CryptoNetworkService(private val networkServices: NetworkServices) {

    fun getAllCurrency(
        onSuccess: (List<CurrencyModel>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return networkServices.getAllCurrency().uiSubscribe(onNext = { onSuccess(it) }, onError = { onError(it) })
    }
}