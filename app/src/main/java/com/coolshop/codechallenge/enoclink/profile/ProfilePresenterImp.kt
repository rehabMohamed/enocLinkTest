package com.coolshop.codechallenge.enoclink.profile

import android.graphics.Bitmap
import com.coolshop.codechallenge.enoclink.base.presenter.BasePresenterImp
import com.coolshop.codechallenge.enoclink.data.LoggedInUser
import com.coolshop.codechallenge.enoclink.data.UserRepository
import com.coolshop.codechallenge.enoclink.data.network.model.Response
import com.coolshop.codechallenge.enoclink.utils.applyGrayScaleFilter
import com.coolshop.codechallenge.enoclink.utils.encodeImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfilePresenterImp<V: ProfileView> @Inject constructor(override val userRepository: UserRepository)
    : BasePresenterImp<V>(userRepository), ProfilePresenter<V> {

    override fun getUserProfile() {
        launch(coroutineContext) {
            val response = withContext(Dispatchers.IO){
                userRepository.fetchUserProfile()
            }
            when(response) {
                is Response.Success<LoggedInUser> -> view?.updateProfile(response.data)
                is Response.Error ->  view?.showError(response.throwable.message)
            }
        }
    }

    override fun uploadAvatar(bitmap: Bitmap) {
        val encodedImage = bitmap.encodeImage()

        launch(coroutineContext) {
            val response = withContext(Dispatchers.IO) {
                userRepository.uploadProfilePhoto(encodedImage)
            }
            when(response) {
                is Response.Success<String> -> view?.showAvatarUpdated()
                is Response.Error ->  view?.showAvatarUploadFailed()
            }
        }

    }

    override fun applyGrayScaleFilter(original: Bitmap): Bitmap {
        return original.applyGrayScaleFilter()
    }

}