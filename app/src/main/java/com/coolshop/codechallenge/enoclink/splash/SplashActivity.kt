package com.coolshop.codechallenge.enoclink.splash

import android.content.Intent
import android.os.Bundle
import com.coolshop.codechallenge.enoclink.EnocLinkApp.Companion.appComponent
import com.coolshop.codechallenge.enoclink.R
import com.coolshop.codechallenge.enoclink.base.view.BaseActivity
import com.coolshop.codechallenge.enoclink.login.LoginActivity
import com.coolshop.codechallenge.enoclink.profile.ProfileActivity
import com.coolshop.codechallenge.enoclink.splash.di.SplashModule
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var presenter: SplashPresenter<SplashView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter.apply {
            onAttach(this@SplashActivity)
            checkLogin()
        }
    }

    override fun performDI() {
        appComponent.splashComponent(SplashModule()).inject(this)
    }

    override fun openLoginScreen() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun openProfileScreen() {
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}
