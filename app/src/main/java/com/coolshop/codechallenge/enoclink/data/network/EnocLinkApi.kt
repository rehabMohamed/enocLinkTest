package com.coolshop.codechallenge.enoclink.data.network

import com.coolshop.codechallenge.enoclink.data.network.model.AvatarRequest
import com.coolshop.codechallenge.enoclink.data.network.model.AvatarResponse
import com.coolshop.codechallenge.enoclink.data.network.model.UserResponse
import com.coolshop.codechallenge.enoclink.data.network.model.LoginRequest
import com.coolshop.codechallenge.enoclink.data.network.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EnocLinkApi {

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: String?): UserResponse

    @POST("sessions/new")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("users/{userId}/avatar")
    suspend fun uploadProfilePhoto(@Path("userId") userId: String?,
                                   @Body avatarRequest: AvatarRequest): AvatarResponse
}