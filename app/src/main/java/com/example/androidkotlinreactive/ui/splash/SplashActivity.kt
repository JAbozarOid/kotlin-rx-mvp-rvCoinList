package com.example.androidkotlinreactive.ui.splash

import android.os.Bundle
import android.os.Handler
import com.example.androidkotlinreactive.MainActivity
import com.example.androidkotlinreactive.R
import com.example.androidkotlinreactive.base.BaseActivity
import com.example.androidkotlinreactive.extensions.showSnackBar
import com.example.androidkotlinreactive.network.NetworkUtils
import com.example.androidkotlinreactive.ui.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.splash_layout.*
import org.jetbrains.anko.act
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var networkUtils: NetworkUtils

    private val splashPresenter by lazy { SplashPresenter(this, networkUtils) }

    // inject splash activity into our main activity
    override fun initInjection() {
        (application as MainActivity).cryptoDeps.inject(this)
    }

    override fun navigateToHome() {
        Handler().postDelayed({
            startActivity<HomeActivity>()
            this@SplashActivity.finish()
        }, 3000)
    }

    override fun showInternetError() {
        rootSplash.showSnackBar(R.string.error_no_internet, timeLength = Snackbar.LENGTH_INDEFINITE) {
            this.setAction(resources.getString(R.string.text_check)) {
                this.dismiss()
                act.recreate()
            }
        }
    }

    override fun setupLayout() {
        setContentView(R.layout.splash_layout)
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        splashPresenter.checkRequirement()
    }


}