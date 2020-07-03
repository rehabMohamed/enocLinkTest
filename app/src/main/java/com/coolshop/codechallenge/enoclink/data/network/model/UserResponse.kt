package com.coolshop.codechallenge.enoclink.data.network.model

import com.google.gson.annotations.SerializedName

data class UserResponse(val email: String, @SerializedName("avatar_url") val avatarUrl: String?)