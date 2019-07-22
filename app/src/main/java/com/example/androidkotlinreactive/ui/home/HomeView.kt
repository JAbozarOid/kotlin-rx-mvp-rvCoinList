package com.example.androidkotlinreactive.ui.home

import com.example.androidkotlinreactive.model.CurrencyModel

interface HomeView {
    fun updateProgressVisibility(visible: Boolean)
    fun showInternetError()
    fun updateData(response: List<CurrencyModel>)
    fun showError(error: Throwable)
    fun disablePullRefreshProgress()
    fun refreshList()
}