package com.coolshop.codechallenge.enoclink.data.network

import com.coolshop.codechallenge.enoclink.data.LoggedInUser
import com.coolshop.codechallenge.enoclink.data.UserRepository
import com.coolshop.codechallenge.enoclink.data.UserManager
import com.coolshop.codechallenge.enoclink.data.network.model.AvatarRequest
import com.coolshop.codechallenge.enoclink.data.network.model.LoginRequest
import com.coolshop.codechallenge.enoclink.data.network.model.Response
import com.coolshop.codechallenge.enoclink.utils.md5
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
        private val enocLinkApi: EnocLinkApi,
        private val userManager: UserManager
) : UserRepository {

    override suspend fun isLoggedIn(): Boolean =
        userManager.isLoggedIn

    override suspend fun login(email: String, password: String): Response<String> {
        return try {
            val response = enocLinkApi.login(LoginRequest(email, password))
            userManager.updateUser(response.userId, response.token, email, password)
            Response.Success(response.userId)
        } catch (e: Throwable) {
            Response.Error(e)
        }
    }

    override suspend fun fetchUserProfile(): Response<LoggedInUser> {
        return try {
            val response = enocLinkApi.getUser(userManager.getUserId())
            val avatarUrl = response.avatarUrl ?: getGravatarUrl(response.email)
            userManager.updateAvatar(avatarUrl)
            Response.Success(userManager.loggedInUser)
        } catch (e: Throwable) {
            Response.Error(e)
        }
    }

    override suspend fun uploadProfilePhoto(encodedImage: String): Response<String> {
        return try {
            val response = enocLinkApi.uploadProfilePhoto(userManager.getUserId(), AvatarRequest(encodedImage))
            userManager.updateAvatar(response.avatarUrl)
            Response.Success(response.avatarUrl)
        } catch (e: Throwable) {
            Response.Error(e)
        }
    }

    override fun getGravatarUrl(email: String?) : String? {
        return email?.let {
            val hash = it.md5()
            "http://www.gravatar.com/avatar/$hash?s=100&d=404"
        }
    }
}