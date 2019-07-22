package com.example.androidkotlinreactive.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// all the variable have ? because they are potentially null
// with SerializedName with can set each variable equal to api variable name
@SuppressLint("ParcelCreator")
@Parcelize
data class CurrencyModel(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("symbol") val symbol: String?,
    @SerializedName("rank") val rank: String?,
    @SerializedName("price_usd") val priceUsd: String?,
    @SerializedName("price_btc") val priceBtc: String?,
    @SerializedName("24_Volume_usd") val dailyVolumeUsd: String?,
    @SerializedName("market_cap_usd") val marketCapUsd: String?,
    @SerializedName("available_supply") val availableSupply: String?,
    @SerializedName("total_supply") val totalSupply: String?,
    @SerializedName("max_supply") val maxSupply: String?,
    @SerializedName("percent_change_1h") val hourPercentChange: String?,
    @SerializedName("percent_change_24h") val dayPercentChange: String?,
    @SerializedName("percent_change_7d") val weekPercentChange: String?,
    @SerializedName("last_updated") val lastUpdated: String?,
    @SerializedName("price_idr") val priceIdr: String?,
    @SerializedName("24h_Volume_idr") val dailyVolumeIdr: String?,
    @SerializedName("market_cap_idr") val marketCapIdr: String?
) : Parcelable

