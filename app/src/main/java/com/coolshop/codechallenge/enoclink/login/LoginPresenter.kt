package com.coolshop.codechallenge.enoclink.login

import com.coolshop.codechallenge.enoclink.base.presenter.BasePresenter

interface LoginPresenter<V: LoginView> : BasePresenter<V> {
    fun emailChanged(email: String)
    fun passwordChanged(password: String)
    fun login(email: String, password: String)
}