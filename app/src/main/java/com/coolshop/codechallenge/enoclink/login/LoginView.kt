package com.coolshop.codechallenge.enoclink.login

import com.coolshop.codechallenge.enoclink.base.view.BaseView


interface LoginView : BaseView {

    fun showInvalidEmail()

    fun showInvalidPassword()

    fun hideInvalidEmail()

    fun hideInvalidPassword()

    fun enableLogin()

    fun disableLogin()

    fun showLoginFailed()

    fun openProfileScreen()
}