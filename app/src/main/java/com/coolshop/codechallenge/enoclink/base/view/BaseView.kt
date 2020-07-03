package com.coolshop.codechallenge.enoclink.base.view

import androidx.annotation.StringRes

interface BaseView {

    fun showToast(message: String?)

    fun showToast(@StringRes resId: Int)
}