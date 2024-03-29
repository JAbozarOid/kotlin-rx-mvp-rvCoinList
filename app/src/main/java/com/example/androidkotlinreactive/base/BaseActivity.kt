package com.example.androidkotlinreactive.base

import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLayout()
        initInjection()
        onViewReady(savedInstanceState)
    }

    protected abstract fun initInjection()
    protected abstract fun setupLayout()
    protected abstract fun onViewReady(savedInstanceState: Bundle?)

    protected open fun getTransparentBackgroundDialog(): Dialog = Dialog(this).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.let {
            it.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            it.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}