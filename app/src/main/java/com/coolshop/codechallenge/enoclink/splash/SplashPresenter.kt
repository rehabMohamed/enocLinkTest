package com.coolshop.codechallenge.enoclink.splash

import com.coolshop.codechallenge.enoclink.base.presenter.BasePresenter

interface SplashPresenter<V: SplashView> : BasePresenter<V> {
    fun checkLogin()
}