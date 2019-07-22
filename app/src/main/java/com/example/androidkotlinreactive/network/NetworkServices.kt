package com.example.androidkotlinreactive.network

import com.example.androidkotlinreactive.model.CurrencyModel
import io.reactivex.Flowable
import retrofit2.http.GET

interface NetworkServices {

    @GET("v1/ticker/?convert=IDR&limit=15")
    fun getAllCurrency(): Flowable<List<CurrencyModel>>
}