package com.coolshop.codechallenge.enoclink.profile

import android.graphics.Bitmap
import com.coolshop.codechallenge.enoclink.base.presenter.BasePresenter

interface ProfilePresenter<V: ProfileView> : BasePresenter<V> {
    fun getUserProfile()
    fun uploadAvatar(bitmap: Bitmap)
    fun applyGrayScaleFilter(original: Bitmap): Bitmap
}