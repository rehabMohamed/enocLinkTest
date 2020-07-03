package com.coolshop.codechallenge.enoclink.data

import com.coolshop.codechallenge.enoclink.data.network.model.Response

interface UserRepository {

    suspend fun isLoggedIn(): Boolean

    suspend fun login(email: String, password: String): Response<String>

    suspend fun fetchUserProfile(): Response<LoggedInUser>

    suspend fun uploadProfilePhoto(encodedImage: String): Response<String>
}
