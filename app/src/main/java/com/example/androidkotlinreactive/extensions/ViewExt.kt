package com.example.androidkotlinreactive.extensions

import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

/**
 * 1
 *  high order functions which lets you pass functions as parameters as well as return functions.
 *  But the fact that they are stored as objects may make the use disadvantageous at times because of the memory overhead.
 *  each object is allocated space in memory heap and the methods that call this method are also allocated space.
 */

/**
 * 2
 * A way to get around the memory overhead issue is, by declaring the function inline.
 * inline helps reduce call overhead.
 * Basically inline tells the compiler to copy these functions and parameters to call site.
 * So basically inline can be used when we wish to reduce memory overhead.
 * But inline also increases the resulting bytecode. Which is why, inline usage
 * should be avoided with large functions or accessors with large code logic.
 */

/**
 * 3
 * To access type passed as parameter we use reified type parameter.
 */

/**
 * 4
 * how to declare inline function
 * inline fun <T> lock(lock: Lock, body: () -> T): T { ... }
 */

inline fun View.showSnackBar(
    snackbarText: String,
    timeLength: Int = Snackbar.LENGTH_LONG,
    listener: Snackbar.() -> Unit = {}
) =

    Snackbar.make(this, snackbarText, timeLength).apply {
        listener()
        show()
    }

inline fun View.showSnackBar(
    @StringRes snackbarTextRes: Int,
    timeLength: Int = Snackbar.LENGTH_LONG,
    listener: Snackbar.() -> Unit = {}
) =
    Snackbar.make(this, snackbarTextRes, timeLength).apply {
        listener()
        show()

    }

fun Window.blockTouchScreen() {
    this.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Window.unblockTouchScreen() {
    this.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}