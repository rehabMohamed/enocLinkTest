package com.coolshop.codechallenge.enoclink.profile

import com.coolshop.codechallenge.enoclink.base.view.BaseView
import com.coolshop.codechallenge.enoclink.data.LoggedInUser

interface ProfileView : BaseView {
    fun updateProfile(user: LoggedInUser?)
    fun showAvatarUpdated()
    fun showAvatarUploadFailed()
    fun showError(error: String?)
}