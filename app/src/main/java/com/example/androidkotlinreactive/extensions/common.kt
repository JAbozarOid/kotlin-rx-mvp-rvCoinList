package com.example.androidkotlinreactive.extensions

import java.text.SimpleDateFormat
import java.util.*

inline fun <reified T : Any> clas() = T::class.java
inline fun <reified T : Any> tag() = T::class.java.simpleName

// this fun convert string to long
fun String.safeToLong(): Long {
    return this.toLongOrNull() ?: 0L
}

// convert date json object to date pattern
fun Long.unixToDate(pattern: String = "dd MMM YYY, HH:mm:ss"): String {
    val date = Date(this * 1000L)
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(date)
}

fun String.trimTrailingZeros(): String? {
    return if (!this.contains(".")) {
        this
    } else {
        this.replace("\\.?0*$".toRegex(), "")
    }
}
